package web.charmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.charmi.dao.ItemDao;
import web.charmi.entity.Item;
import web.charmi.entity.Pagination;

import java.util.List;

@Component
public class ItemServiceImp implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public String insertItem(Item item) {
        return "新增 物品編號: "+itemDao.insertItem(item);
    }

    @Override
    public String updateItem(Item item) {
        return itemDao.updateItem(item);
    }

    @Override
    public String deleteItem(Integer I_RecId) {
        return itemDao.deleteItem(I_RecId);
    }

    @Override
    public List<Item> getByAll() {
        return itemDao.getByAll();
    }

    @Override
    public Item geyByIRecId(Integer I_RecId) {
        return itemDao.getByIRecId(I_RecId);
    }

    @Override
    public Pagination getByAllPage(Integer page) {
        Pagination pagination=new Pagination();
        List<Item> itemList;
        int pageSize=5, totalPage=1;
        long Count=0;
        Count=itemDao.getByAllCount();
        itemList=itemDao.getByAllPage(page, pageSize);
        if (Count>pageSize) {
            totalPage=(int) Count/pageSize;
            if (Count%pageSize>0) {
                totalPage++;
            }
        }
        pagination.setData(itemList);
        pagination.setDataCount(Count);
        pagination.setTotalPage(totalPage);
        return pagination;
    }
}
