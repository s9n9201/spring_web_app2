package web.charmi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import web.charmi.entity.User;
import web.charmi.rowmapper.UserRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImp implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public User getUser(String Value, String Column) {
        String SqlStr="";
        Map<String, String> map=new HashMap<>();
        SqlStr="select top 1 * from Org where "+Column+"=:"+Column+" order by OrgId ";
        map.put(Column, Value);

        List<User> userList=jdbcTemplate.query(SqlStr, map, new UserRowMapper());
        if (userList.size()>0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public Boolean existsUser(String Value, String Column) {

        return getUser(Value, Column)!=null?true:false;
    }
}
