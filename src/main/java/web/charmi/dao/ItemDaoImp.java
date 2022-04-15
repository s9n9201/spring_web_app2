package web.charmi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import web.charmi.rowmapper.ItemRowMapper;
import web.charmi.entity.Item;
import web.charmi.util.Date;
import web.charmi.util.SqlMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ItemDaoImp implements ItemDao {

    @Autowired
    private NamedParameterJdbcTemplate JdbcTemplate;

    @Autowired
    private SqlMap sqlMap;

    @Autowired
    private Date date;

    @Override
    public Integer insertItem(Item item) {
        String SqlStr="";
        int I_RecId=0;
        Map<String, Object> map=new HashMap<>();
        Map<String, String> TableMap=new HashMap<>();
        KeyHolder keyHolder=new GeneratedKeyHolder();

        TableMap.put("I_TRecId",":I_TRecId");
        TableMap.put("I_Name",":I_Name");
        TableMap.put("I_Source",":I_Source");
        TableMap.put("I_MadeIn",":I_MadeIn");
        TableMap.put("I_Amount",":I_Amount");
        TableMap.put("I_Cost",":I_Cost");
        TableMap.put("I_Price",":I_Price");
        TableMap.put("I_Total",":I_Total");
        TableMap.put("I_RecOrg",":I_RecOrg");
        SqlStr=sqlMap.insert("Item",TableMap);

        map.put("I_TRecId",item.getITRecId());
        map.put("I_Name",item.getIName());
        map.put("I_Source",item.getISource());
        map.put("I_MadeIn",item.getIMadeIn());
        map.put("I_Amount",item.getIAmount()+"");
        map.put("I_Cost",item.getICost()+"");
        map.put("I_Price",item.getIPrice()+"");
        map.put("I_Total",item.getITotal()+"");
        map.put("I_RecOrg",item.getIRecOrg());
        JdbcTemplate.update(SqlStr,new MapSqlParameterSource(map),keyHolder);
        I_RecId=keyHolder.getKey().intValue();
        return I_RecId;
    }

    @Override
    public String updateItem(Item item) {
        String SqlStr="";
        Map<String, Object> map=new HashMap<>();
        Map<String, String> TableMap=new HashMap<>();

        TableMap.put("I_TRecId",":I_TRecId");
        TableMap.put("I_Name",":I_Name");
        TableMap.put("I_Source",":I_Source");
        TableMap.put("I_MadeIn",":I_MadeIn");
        TableMap.put("I_Amount",":I_Amount");
        TableMap.put("I_Cost",":I_Cost");
        TableMap.put("I_Price",":I_Price");
        TableMap.put("I_Total",":I_Total");
        TableMap.put("I_UpdateOrg",":I_UpdateOrg");
        TableMap.put("I_UpdateDate",":I_UpdateDate");
        SqlStr=sqlMap.update("Item",TableMap,"I_RecId=:I_RecId");

        map.put("I_RecId",item.getIRecId());
        map.put("I_TRecId",item.getITRecId());
        map.put("I_Name",item.getIName());
        map.put("I_Source",item.getISource());
        map.put("I_MadeIn",item.getIMadeIn());
        map.put("I_Amount",item.getIAmount()+"");
        map.put("I_Cost",item.getICost()+"");
        map.put("I_Price",item.getIPrice()+"");
        map.put("I_Total",item.getITotal()+"");
        map.put("I_UpdateOrg",item.getIUpdateOrg());
        map.put("I_UpdateDate",date.Now());
        JdbcTemplate.update(SqlStr,map);
        return "執行 Update Sql";
    }

    @Override
    public String deleteItem(Integer I_RecId) {
        String SqlStr="";
        Map<String, Object> map=new HashMap<>();
        SqlStr="update Item set I_isDelete=1, I_DeleteOrg=3, I_DeleteDate=:I_DeleteDate where I_RecId=:I_RecId ";

        map.put("I_RecId",I_RecId);
        map.put("I_DeleteDate",date.Now());
        JdbcTemplate.update(SqlStr,map);
        return "執行 Delete Sql";
    }

    @Override
    public Item getByIRecId(Integer I_RecId) {
        String SqlStr="";
        List<Item> itemList;
        Map<String, Object> map=new HashMap<>();
        SqlStr=SQL("", "where I_RecId=:I_RecId and I_isDelete=0 and I_TRecId=T_RecId", "");
        map.put("I_RecId",I_RecId);
        itemList=JdbcTemplate.query(SqlStr, map, new ItemRowMapper());
        if (itemList.size()>0) {
            return itemList.get(0);
        }
        return null;
    }

    @Override
    public List<Item> getByAllPage(Integer start, Integer end, String searchText) {
        String SqlStr="", tmpSql="";
        List<Item> itemList;
        Map<String, Object> map=new HashMap<>();

        tmpSql=SQL("", "", searchText);
        SqlStr="select * from ("+tmpSql+") ItemhasRow where Row_Num>:start and Row_Num<:end ";

        if (!searchText.equals("")) {
            map.put("searchText", "%"+searchText+"%");
        }
        map.put("start", start);
        map.put("end", end);
        itemList=JdbcTemplate.query(SqlStr, map, new ItemRowMapper());
        return itemList;
    }

    @Override
    public Long getByAllCount(String searchText) {
        String SqlStr="";
        long Count=0;
        Map<String, Object> map=new HashMap<>();
        SqlStr=SQL("count(I_RecId)", "", searchText);
        if (!searchText.equals("")) {
            map.put("searchText", "%"+searchText+"%");
        }
        Count=JdbcTemplate.queryForObject(SqlStr, map, Long.class);
        return Count;
    }

    public String SQL(String Column, String Where, String searchText) {
        String SqlStr="";
        if (Column.equals("")) {
            Column="ROW_NUMBER() OVER(order by I_RecId desc) as 'Row_Num', I_RecId, I_TRecId, I_Name, I_Source, I_MadeIn, I_Amount, T_RecId, T_Name, T_SubName, "
                    +"I_Cost, I_Price, I_Total, I_RecDate, I_UpdateOrg";
        }
        if (Where.equals("")) {
            Where="where I_isDelete=0 and I_TRecId=T_RecId ";
        }
        SqlStr="select "+Column+" from Item, ItemType "+Where;
        if (!searchText.equals("")) {
            SqlStr+="and ("
                    +"I_Name like :searchText or "
                    +"I_Source like :searchText or "
                    +"I_MadeIn like :searchText or "
                    +"I_Amount like :searchText or "
                    +"I_Cost like :searchText or "
                    +"I_Price like :searchText "
                    +") ";
        }
        return SqlStr;
    }
}
