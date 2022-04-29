package web.charmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.charmi.dao.ItemDao;
import web.charmi.entity.Item;
import web.charmi.entity.Pagination;
import web.charmi.util.Tool;
import web.charmi.util.UserDitail;

import java.util.List;

@Component
public class ItemServiceImp implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public Item insertItem(Item item) {
        try {
            String uuid=Tool.getUUID();
            item.setIRecOrg(UserDitail.getOrgId());
            item.setIUUID(uuid);
            Integer RecId=itemDao.insertItem(item);

            Item newItem=new Item();
            newItem.setIRecId(RecId);
            newItem.setIUUID(uuid);
            return newItem;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String updateItem(Item item) {
        try {
            item.setIUpdateOrg(UserDitail.getOrgId());
            itemDao.updateItem(item);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "儲存失敗，請重新操作！";
        }
        return "編輯成功！";
    }

    @Override
    public String deleteItem(Integer I_RecId) {
        return itemDao.deleteItem(I_RecId);
    }

    @Override
    public Item geyByIRecId(Integer I_RecId) {
        return itemDao.getByIRecId(I_RecId);
    }

    @Override
    public Pagination getByAllPage(Integer page, String searchText) {
        Pagination pagination=new Pagination();
        List<Item> itemList;
        int pageSize=15, totalPage=1, start=0, end=0;
        long Count=0;
        Count=itemDao.getByAllCount(searchText);
        start=pageSize*(page-1);
        end=pageSize*page+1;
        itemList=itemDao.getByAllPage(start, end, searchText);
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
