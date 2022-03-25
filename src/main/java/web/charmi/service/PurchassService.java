package web.charmi.service;

import web.charmi.entity.Purchass;

import java.util.List;

public interface PurchassService {
    Purchass getByPRecId(Integer P_RecId);

    List<Purchass> getByAll();

    String insertPurchass(Purchass purchass);

    String updatePurchass(Purchass purchass);

    String deletePurchass(Integer P_RecId);
}
