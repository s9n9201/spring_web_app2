package web.charmi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import web.charmi.dao.RoleDao;
import web.charmi.dao.UserDao;
import web.charmi.entity.Role;
import web.charmi.entity.User;
import web.charmi.entity.enumRole;
import web.charmi.exception.TokenRefreshException;
import web.charmi.security.jwt.JwtUtils;
import web.charmi.security.service.UserDetailsImp;
import web.charmi.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody @Validated(User.signIn.class) User user) {
        Map<String, Object> map=new HashMap<>();
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getOrgName(),user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImp userDetailsImp=(UserDetailsImp) authentication.getPrincipal();
        String jwt=jwtUtils.generateJwtToken(userDetailsImp);
        List<String> roles=userDetailsImp.getAuthorities().stream()
                            .map(item->item.getAuthority())
                            .collect(Collectors.toList());
        User tmpUser=userService.createRefrshToken(userDetailsImp.getOrgId());

        map.put("Token", jwt);
        map.put("RefreshToken", tmpUser.getRefreshToken());
        map.put("OrgId", userDetailsImp.getOrgId());
        map.put("OrgName", userDetailsImp.getUsername());
        map.put("Role", roles);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Validated(User.signUp.class) User user) {
        String Msg="";
        HttpStatus httpStatus=null;
        Map<String, Object> map=new HashMap<>();
        if (userDao.existsUser(user.getOrgName(), "OrgName")) {
            httpStatus=HttpStatus.BAD_REQUEST;  //400
            Msg="Username is already taken !";
        } else if (userDao.existsUser(user.getEmail(), "Email")) {
            httpStatus=HttpStatus.BAD_REQUEST;
            Msg="Email is already in user !";
        } else {
            httpStatus=HttpStatus.OK;   //200
            Msg="User registered successfully !";
        }

        if (httpStatus.equals(HttpStatus.OK)) {
            user.setPassword(encoder.encode(user.getPassword()));
            List<String> strRoles=user.getStrRoleList();
            List<Role> roles=new ArrayList<>();
            if (strRoles==null) {
                Role userRole=roleDao.findByName(enumRole.ROLE_USER)
                        .orElseThrow(()->new RuntimeException("[ Charmi ] Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role->{
                    enumRole tmpEnumRole=null;
                    switch (role) {
                        case "admin":
                            tmpEnumRole=enumRole.ROLE_ADMIN; break;
                        default:
                            tmpEnumRole=enumRole.ROLE_USER;
                    }
                    Role userRole=roleDao.findByName(tmpEnumRole)
                            .orElseThrow(()->new RuntimeException("[ Charmi ] Role is not found."));
                    roles.add(userRole);
                });
            }
            user.setRoleList(roles);
            userDao.insertUser(user);
        }
        map.put("message", Msg);
        return ResponseEntity.status(httpStatus).body(map);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@RequestBody @Validated(User.refresh.class) User user) {
        String refreshToken=user.getRefreshToken();

        return userService.findByToken(refreshToken, "RefreshToken")
                .map(userService::verifyExpiration)
                .map(tmpUser->{
                    String token=jwtUtils.generateTokenFromUsername(tmpUser.getOrgName());
                    Map<String, Object> map=new HashMap<>();
                    map.put("Token",token );
                    map.put("RefreshToken", tmpUser.getRefreshToken());
                    return ResponseEntity.ok(map);
                })
                .orElseThrow(()->new TokenRefreshException(refreshToken, "Refresh token is not in database!"));
    }
}
