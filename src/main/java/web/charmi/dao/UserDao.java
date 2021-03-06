package web.charmi.dao;

import web.charmi.entity.User;

import java.util.Optional;

public interface UserDao {

    Integer insertUser(User user);

    Optional<User> getUser(String Value, String Column);

    Boolean existsUser(String Value, String Column);

    Optional<User> findByToken(String Value, String Column);

    User updateRefrshToken(Integer OrgId, String Type);
}
