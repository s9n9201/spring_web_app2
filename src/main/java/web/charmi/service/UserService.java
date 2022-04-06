package web.charmi.service;

import web.charmi.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByToken(String Value, String Column);

    User createRefrshToken(Integer OrgId);

    User verifyExpiration(User user);
}
