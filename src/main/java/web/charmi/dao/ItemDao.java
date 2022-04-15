package web.charmi.dao;

import web.charmi.entity.Item;

import java.util.List;

public interface ItemDao {

    Item getByIRecId(Integer I_RecId);

    Integer insertItem(Item item);

    String updateItem(Item item);

    String deleteItem(Integer I_RecId);

    List<Item> getByAllPage(Integer start, Integer end, String searchText);

    Long getByAllCount(String searchText);
}
