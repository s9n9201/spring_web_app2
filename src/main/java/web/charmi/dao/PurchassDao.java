package web.charmi.dao;

import web.charmi.entity.Purchass;

import java.util.List;

public interface PurchassDao {

    Purchass getByPRecId(Integer P_RecId);

    List<Purchass> getByAll();

    Integer insertPurchass(Purchass purchass);

    String updatePurchass(Purchass purchass);

    String deletePurchass(Integer P_RecId);

}
