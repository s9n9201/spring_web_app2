package web.charmi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.charmi.entity.ItemType;
import web.charmi.service.ItemTypeService;

import java.util.List;

@RestController
@CrossOrigin(value="http://localhost:8080")
public class ItemTypeRestController {
    @Autowired
    private ItemTypeService itemTypeService;

    @GetMapping("/itemtypes")
    public List<ItemType> getAllItmeType() {
        return itemTypeService.getAllItemType();
    }
}
