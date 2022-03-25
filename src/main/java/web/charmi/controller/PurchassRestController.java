package web.charmi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import web.charmi.entity.Purchass;
import web.charmi.service.PurchassService;
import web.charmi.util.Date;

import java.util.List;

@RestController
public class PurchassRestController {

    @Autowired
    private PurchassService purchassService;

    @Autowired
    private Date date;

    @PostMapping("/purchass/insert")
    public String insertPurchass(@RequestBody @Validated(Purchass.Insert.class) Purchass purchass) {
        if (purchass.getPArriveDate()==null || String.valueOf(purchass.getPArriveDate()).equals("")) {
            try {
                purchass.setPArriveDate(date.CDate("1911-01-01"));
            } catch(Exception e) {
                System.out.println("error: "+e.getMessage());
            }
        }
        return purchassService.insertPurchass(purchass);
    }

    @PostMapping("/purchass/update")
    public String updatePurchass(@RequestBody @Validated(Purchass.Update.class) Purchass purchass) {
        return purchassService.updatePurchass(purchass);
    }

    @DeleteMapping("/purchass/delete/{P_RecId}")
    public String deletePurchass(@PathVariable Integer P_RecId) {
        return purchassService.deletePurchass(P_RecId);
    }

    @GetMapping("/purchasses")
    public List<Purchass> select() {
        return purchassService.getByAll();
    }

    @GetMapping("/purchasses/{P_RecId}")
    public Purchass selectOne(@PathVariable Integer P_RecId) {
        return purchassService.getByPRecId(P_RecId);
    }
}
