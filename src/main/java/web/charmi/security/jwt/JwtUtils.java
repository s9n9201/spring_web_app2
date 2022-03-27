package web.charmi.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import web.charmi.security.service.UserDetailsImp;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger log=LoggerFactory.getLogger(JwtUtils.class);
    @Value("${webcharmi.app.jwtSecret}")
    private String jwtSecret;
    @Value("${jwtExpiration}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImp userDetailsImp=(UserDetailsImp) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetailsImp.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date( (new Date()).getTime()+jwtExpirationMs ))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();

    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("[ Charmi ] Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("[ Charmi ] Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("[ Charmi ] JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("[ Charmi ] JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("[ Charmi ] claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
