package web.charmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.charmi.entity.ItemType;

import java.util.List;

@Component
public class ItemTypeServiceImp implements ItemTypeService {
    @Autowired
    ItemTypeService itemTypeService;
    @Override
    public List<ItemType> getAllItemType() {
        return itemTypeService.getAllItemType();
    }
}
