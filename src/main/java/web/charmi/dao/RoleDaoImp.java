package web.charmi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import web.charmi.entity.Role;
import web.charmi.entity.enumRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleDaoImp implements RoleDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public Optional<Role> findByName(enumRole name) {
        Map<String, Object> map=new HashMap<>();
        String SqlStr="";
        SqlStr="select * from Role where R_Name=:R_Name";
        map.put("R_Name", name.name());
        List<Role> roleList=jdbcTemplate.query(SqlStr, map, (rs, rowNum)->{
            Role role=new Role();
            enumRole tmpERole=null;
            switch (enumRole.valueOf(rs.getString("R_Name"))) {
                case ROLE_USER:
                    tmpERole=enumRole.ROLE_USER; break;
                case ROLE_ADMIN:
                    tmpERole=enumRole.ROLE_ADMIN; break;
            }
            role.setRRecId(rs.getInt("R_RecId"));
            role.setName(tmpERole);
            return role;
        });
        if (roleList.size()>0) {
            return Optional.of(roleList.get(0));
        }
        return null;
    }
}
