package web.charmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.charmi.dao.ItemTypeDao;
import web.charmi.entity.ItemType;

import java.util.List;

@Component
public class ItemTypeServiceImp implements ItemTypeService {
    @Autowired
    ItemTypeDao itemTypeDao;
    @Override
    public List<ItemType> getAllItemType() {
        return itemTypeDao.getAllItemType();
    }
}
