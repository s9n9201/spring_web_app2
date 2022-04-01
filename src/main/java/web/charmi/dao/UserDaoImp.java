package web.charmi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import web.charmi.entity.Role;
import web.charmi.entity.User;
import web.charmi.entity.enumRole;
import web.charmi.rowmapper.UserRowMapper;
import web.charmi.util.SqlMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class UserDaoImp implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private SqlMap sqlMap;

    @Override
    public Integer insertUser(User user) {
        String SqlStr="";
        Integer OrgId=0;
        Map<String, Object> map=new HashMap<>();
        Map<String, String> TableMap=new HashMap<>();
        KeyHolder keyHolder=new GeneratedKeyHolder();
        TableMap.put("OrgName", ":OrgName");
        TableMap.put("Password", ":Password");
        TableMap.put("Email", ":Email");
        SqlStr=sqlMap.insert("Org", TableMap);

        map.put("OrgName", user.getOrgName());
        map.put("Password", user.getPassword());
        map.put("Email", user.getEmail());
        jdbcTemplate.update(SqlStr, new MapSqlParameterSource(map), keyHolder);
        OrgId=keyHolder.getKey().intValue();

        TableMap.clear();
        map.clear();

        List<Role> roleList=user.getRoleList();
        MapSqlParameterSource[] parameterSource=new MapSqlParameterSource[roleList.size()];
        TableMap.put("U_URecId", ":U_URecId");
        TableMap.put("U_RRecId", ":U_RRecId");
        SqlStr=sqlMap.insert("UserRole", TableMap);

        for (int i=0; i<roleList.size(); i++) {
            Role role=roleList.get(i);
            parameterSource[i]=new MapSqlParameterSource();
            parameterSource[i].addValue("U_URecId", OrgId);
            parameterSource[i].addValue("U_RRecId", role.getRRecId());
        }
        jdbcTemplate.batchUpdate(SqlStr, parameterSource);
        return OrgId;
    }

    @Override
    public Optional<User> getUser(String Value, String Column) {
        String SqlStr="", OrgId="";
        Map<String, String> map=new HashMap<>();
        SqlStr="select top 1 OrgId, OrgName, Password, Email, RecDate"
                +", isnull(STUFF((select ','+R_Name from Role, UserRole where U_URecId=OrgId and U_RRecId=R_RecId FOR XML PATH('')),1,1,''),'') as RoleName"
                +" from Org where "+Column+"=:"+Column+" order by OrgId ";
        map.put(Column, Value);
        List<User> userList=jdbcTemplate.query(SqlStr, map, new UserRowMapper());
        if (userList.size()>0) {
            map.clear();
            OrgId=userList.get(0).getOrgId().toString();
            map.put("OrgId", OrgId);
            SqlStr="select * from Role, UserRole where U_URecId=:OrgId and U_RRecId=R_RecId ";
            List<Role> roleList=jdbcTemplate.query(SqlStr, map, (rs, rowNum)->{
                Role role=new Role();
                role.setRRecId(rs.getInt("R_RecId"));
                role.setName(enumRole.valueOf(rs.getString("R_Name")));
                return role;
            });
            userList.get(0).setRoleList(roleList);
            return Optional.of(userList.get(0));
        }
        return Optional.empty();
    }

    @Override
    public Boolean existsUser(String Value, String Column) {
        return getUser(Value, Column).isEmpty()?false:true;
    }
}
