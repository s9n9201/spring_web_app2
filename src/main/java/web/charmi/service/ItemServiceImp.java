package web.charmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import web.charmi.dao.ItemDao;
import web.charmi.entity.Item;
import web.charmi.entity.Pagination;
import web.charmi.security.service.UserDetailsImp;
import web.charmi.util.UserDitail;

import java.util.List;

@Component
public class ItemServiceImp implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public String insertItem(Item item) {
        UserDetailsImp userDetailsImp=(UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer OrgId=userDetailsImp.getOrgId();
        try {
            item.setIRecOrg(OrgId);
            item.setITRecId(2);
            itemDao.insertItem(item);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Not OK";
        }
        return "OK";
    }

    @Override
    public String updateItem(Item item) {
        item.setIUpdateOrg(UserDitail.getOrgId());
        return itemDao.updateItem(item);
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
