package web.charmi.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import web.charmi.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user=new User();

        user.setOrgId(rs.getInt("OrgId"));
        user.setOrgName(rs.getString("OrgName"));
        user.setEmail(rs.getString("Email"));
        user.setPassword(rs.getString("Password"));

        return user;
    }
}
