package web.charmi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import web.charmi.entity.Purchass;
import web.charmi.entity.PurchassDetail;
import web.charmi.util.SqlMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PurchassDetailDaoImp implements PurchassDetailDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private SqlMap sqlMap;

    @Override
    public String insertPurchassDetail(Purchass purchass, Integer P_RecId) {
        String SqlStr="";
        Map<String, String> TableMap=new HashMap<>();
        List<PurchassDetail> purchassList=purchass.getPurchassDetailList();
        MapSqlParameterSource[] parameterSources=new MapSqlParameterSource[purchassList.size()];

        TableMap.put("PD_PRecId", ":PD_PRecId");
        TableMap.put("PD_IRecId", ":PD_IRecId");
        TableMap.put("PD_Amount", ":PD_Amount");
        TableMap.put("PD_Cost", ":PD_Cost");
        TableMap.put("PD_Total", ":PD_Total");
        TableMap.put("PD_RecOrg", ":PD_RecOrg");
        SqlStr=sqlMap.insert("Purchass_Detail", TableMap);

        for (int i=0; i<purchassList.size(); i++) {
            PurchassDetail purchassDetail=purchassList.get(i);
            //System.out.println("cost: "+new BigDecimal(purchassDetail.getPDCost()));  存進資料庫時，會因為十進制的關係，使得值變成很多小數點，目前解法是，存進去前，先轉成字串。
            parameterSources[i]=new MapSqlParameterSource();
            parameterSources[i].addValue("PD_PRecId", P_RecId);
            parameterSources[i].addValue("PD_IRecId", purchassDetail.getPDIRecId());
            parameterSources[i].addValue("PD_Amount", purchassDetail.getPDAmount());
            parameterSources[i].addValue("PD_Cost", purchassDetail.getPDCost()+"");
            parameterSources[i].addValue("PD_Total", purchassDetail.getPDTotal()+"");
            parameterSources[i].addValue("PD_RecOrg", purchassDetail.getPDRecOrg());
        }

        jdbcTemplate.batchUpdate(SqlStr, parameterSources);
        return "執行 Insert PurchassDetail Sql";
    }
}
