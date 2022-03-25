package web.charmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.charmi.dao.PurchassDao;
import web.charmi.dao.PurchassDetailDao;
import web.charmi.entity.Purchass;

import java.util.List;

@Component
public class PurchassServiceImp implements PurchassService {

    @Autowired
    private PurchassDao purchassDao;

    @Autowired
    private PurchassDetailDao purchassDetailDao;

    @Override
    public Purchass getByPRecId(Integer P_RecId) {
        return purchassDao.getByPRecId(P_RecId);
    }

    @Override
    public List<Purchass> getByAll() {
        return purchassDao.getByAll();
    }

    @Override
    public String insertPurchass(Purchass purchass) {
        int P_RecId=0;
        P_RecId=purchassDao.insertPurchass(purchass);

        return "執行 Insert Purchass 及 "+purchassDetailDao.insertPurchassDetail(purchass, P_RecId);
    }

    @Override
    public String updatePurchass(Purchass purchass) {
        return purchassDao.updatePurchass(purchass);
    }

    @Override
    public String deletePurchass(Integer P_RecId) {
        return purchassDao.deletePurchass(P_RecId);
    }
}
