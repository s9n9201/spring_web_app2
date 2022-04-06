package web.charmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.charmi.dao.UserDao;
import web.charmi.entity.User;
import web.charmi.exception.TokenRefreshException;

import java.time.Instant;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public Optional<User> findByToken(String Value, String Column) {
        return userDao.findByToken(Value, Column);
    }

    @Override
    public User createRefrshToken(Integer OrgId) {
        return userDao.updateRefrshToken(OrgId, "create");
    }

    @Override
    public User verifyExpiration(User user) {
        if (user.getExpiryDate().compareTo(Instant.now())<0) {
            userDao.updateRefrshToken(user.getOrgId(), "reset");
            throw new TokenRefreshException(user.getRefreshToken(), "Refresh token was expired. Please make a new signin request");
        }
        return user;
    }
}
