package web.charmi;

import org.springframework.jdbc.core.RowMapper;
import web.charmi.entity.Item;
import web.charmi.entity.ItemType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item=new Item();
        ItemType itemType=new ItemType();

        item.setIRecId(rs.getInt("I_RecId"));
        item.setITRecId(rs.getInt("I_TRecId"));
        item.setIName(rs.getString("I_Name"));
        item.setISource(rs.getString("I_Source"));
        item.setIMadeIn(rs.getString("I_MadeIn"));
        item.setIAmount(rs.getFloat("I_Amount"));
        item.setICost(rs.getFloat("I_Cost"));
        item.setIPrice(rs.getFloat("I_Price"));
        item.setITotal(rs.getFloat("I_Total"));
        item.setIRecDate(rs.getTimestamp("I_RecDate"));
        item.setIUpdateOrg(rs.getInt("I_UpdateOrg"));
        item.setItemType(itemType);
        itemType.setTName(rs.getString("T_Name"));
        itemType.setTSubName(rs.getString("T_SubName"));
        return item;
    }
}
