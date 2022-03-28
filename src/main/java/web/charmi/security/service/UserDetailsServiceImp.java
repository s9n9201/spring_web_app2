package web.charmi.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.charmi.dao.UserDao;
import web.charmi.entity.User;
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userDao.getUser(username, "OrgName")
                .orElseThrow(()->new UsernameNotFoundException("[ Charmi ] User Not Found with username: "+username));
        return UserDetailsImp.build(user);
    }
}
