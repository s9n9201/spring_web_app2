package web.charmi.dao;

import web.charmi.entity.User;

public interface UserDao {

    Integer insertUser(User user);

    User getUser(String Value, String Column);

    Boolean existsUser(String Value, String Column);
}
