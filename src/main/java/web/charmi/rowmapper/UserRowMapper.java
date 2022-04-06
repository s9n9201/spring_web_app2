package web.charmi.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import web.charmi.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        String RoleNameStr="", RoleNameArr[]=null;
        List<String> roles=new ArrayList<>();
        User user=new User();
        RoleNameStr=rs.getString("RoleName");
        if (!RoleNameStr.equals("")) {
            RoleNameArr=RoleNameStr.split(",");
            for (int i=0; i<RoleNameArr.length; i++) {
                roles.add(RoleNameArr[i]);
            }
        }
        user.setOrgId(rs.getInt("OrgId"));
        user.setOrgName(rs.getString("OrgName"));
        user.setEmail(rs.getString("Email"));
        user.setPassword(rs.getString("Password"));
        user.setRefreshToken(rs.getString("RefreshToken"));
        user.setExpiryDate(rs.getDate("ExpiryDate").toInstant());
        user.setStrRoleList(roles);
        return user;
    }
}
