package web.charmi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import web.charmi.rowmapper.PurchassRowMapper;
import web.charmi.entity.Purchass;
import web.charmi.util.Date;
import web.charmi.util.SqlMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PurchassDaoImp implements PurchassDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired 
    private SqlMap sqlMap;

    private Date date;
    
    @Override
    public Purchass getByPRecId(Integer P_RecId) {
        String SqlStr="";
        List<Purchass> purchassList;
        Map<String, Object> map=new HashMap<>();
        SqlStr="select P_RecId, P_Store, P_Cost, P_ItemRow, P_Arrived, P_BuyDate, P_ArriveDate, P_RecDate, P_UpdateDate "
                +"from Purchass where P_RecId=:P_RecId and P_isDelete=0 order by P_BuyDate desc";
        map.put("P_RecId", P_RecId);
        purchassList=jdbcTemplate.query(SqlStr, new PurchassRowMapper());
        if (purchassList.size()>0) {
            return purchassList.get(0);
        }
        return null;
    }

    @Override
    public List<Purchass> getByAll() {
        String SqlStr="";
        List<Purchass> purchassList;
        SqlStr="select P_RecId, P_Store, P_Cost, P_ItemRow, P_Arrived, P_BuyDate, P_ArriveDate, P_RecDate, P_UpdateDate "
                +"from Purchass where P_isDelete=0 order by P_BuyDate desc";
        purchassList=jdbcTemplate.query(SqlStr, new PurchassRowMapper());
        return purchassList;
    }

    @Override
    public Integer insertPurchass(Purchass purchass) {
        int P_RecId=0;
        String SqlStr="";
        Map<String, Object> map=new HashMap<>();
        Map<String, String> TableMap=new HashMap<>();
        KeyHolder keyHolder=new GeneratedKeyHolder();

        TableMap.put("P_Store",":P_Store");
        TableMap.put("P_Cost",":P_Cost");
        TableMap.put("P_ItemRow",":P_ItemRow");
        TableMap.put("P_Arrived",":P_Arrived");
        TableMap.put("P_BuyDate",":P_BuyDate");
        TableMap.put("P_ArriveDate",":P_ArriveDate");
        TableMap.put("P_RecOrg",":P_RecOrg");
        SqlStr=sqlMap.insert("Purchass", TableMap);

        map.put("P_Store",purchass.getPStore());
        map.put("P_Cost",purchass.getPCost());
        map.put("P_ItemRow",purchass.getPItemRow());
        map.put("P_Arrived",purchass.getPArrived());
        map.put("P_BuyDate",purchass.getPBuyDate());
        map.put("P_ArriveDate",purchass.getPArriveDate());
        map.put("P_RecOrg",purchass.getPRecOrg());
        jdbcTemplate.update(SqlStr, new MapSqlParameterSource(map), keyHolder);
        P_RecId=keyHolder.getKey().intValue();
        return P_RecId;
    }

    @Override
    public String updatePurchass(Purchass purchass) {
        String SqlStr="";
        Map<String, Object> map=new HashMap<>();
        Map<String, String> TableMap=new HashMap<>();

        TableMap.put("P_Store",":P_Store");
        TableMap.put("P_Cost",":P_Cost");
        TableMap.put("P_ItemRow",":P_ItemRow");
        TableMap.put("P_Arrived",":P_Arrived");
        TableMap.put("P_BuyDate",":P_BuyDate");
        TableMap.put("P_ArriveDate",":P_ArriveDate");
        TableMap.put("P_UpdateOrg",":P_UpdateOrg");
        TableMap.put("P_UpdateDate",":P_UpdateDate");
        SqlStr=sqlMap.update("Purchass", TableMap, "P_RecId=:P_RecId");

        map.put("P_RecId",purchass.getPRecId());
        map.put("P_Store",purchass.getPStore());
        map.put("P_Cost",purchass.getPCost());
        map.put("P_ItemRow",purchass.getPItemRow());
        map.put("P_Arrived",purchass.getPArrived());
        map.put("P_BuyDate",purchass.getPBuyDate());
        map.put("P_ArriveDate",purchass.getPArriveDate());
        map.put("P_UpdateOrg",purchass.getPUpdateOrg());
        map.put("P_UpdateDate",date.Now());
        jdbcTemplate.update(SqlStr, map);
        return "執行 Update Sql";
    }

    @Override
    public String deletePurchass(Integer P_RecId) {
        String SqlStr="";
        Map<String, Object> map=new HashMap<>();

        SqlStr="update Purchass set P_isDelete=1, P_DeleteOrg=3, P_DeleteDate=:P_DeleteDate"
                +"where P_RecId=:P_RecId";
        map.put("P_RecId",P_RecId);
        map.put("P_DeleteDate",date.Now());
        jdbcTemplate.update(SqlStr, map);

        return null;
    }
}
