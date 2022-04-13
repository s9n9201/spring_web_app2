package web.charmi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import web.charmi.entity.ItemType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ItemTypeDaoImp implements ItemTypeDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<ItemType> getAllItemType() {
        Map<String, String> map=new HashMap<>();
        String SqlStr="select T_RecId, T_Name, T_SubName, T_Sort from ItemType order by T_Sort";

        List<ItemType> itemTypes=jdbcTemplate.query(SqlStr, map, (rs, rowNum)->{
            ItemType tmp=new ItemType();
            tmp.setTRecId(rs.getInt("T_RecId"));
            tmp.setTName(rs.getString("T_Name"));
            tmp.setTSubName(rs.getString("T_SubName"));
            return tmp;
        });
        return itemTypes;
    }
}
