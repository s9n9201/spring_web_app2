package web.charmi;

import org.springframework.jdbc.core.RowMapper;
import web.charmi.entity.Purchass;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchassRowMapper implements RowMapper<Purchass> {
    @Override
    public Purchass mapRow(ResultSet rs, int rowNum) throws SQLException {
        Purchass purchass=new Purchass();
        purchass.setPRecId(rs.getInt("P_RecId"));
        purchass.setPStore(rs.getString("P_Store"));
        purchass.setPCost(rs.getFloat("P_Cost"));
        purchass.setPItemRow(rs.getInt("P_ItemRow"));
        purchass.setPArrived(rs.getInt("P_Arrived"));
        purchass.setPBuyDate(rs.getTimestamp("P_BuyDate"));
        purchass.setPArriveDate(rs.getTimestamp("P_ArriveDate"));
        purchass.setPRecDate(rs.getTimestamp("P_RecDate"));
        purchass.setPUpdateDate(rs.getTimestamp("P_UpdateDate"));
        return purchass;
    }
}
