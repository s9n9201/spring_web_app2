package web.charmi.service;

import web.charmi.entity.Item;
import web.charmi.entity.Pagination;

import java.util.List;

public interface ItemService {
    Item geyByIRecId(Integer I_RecId);

    Integer insertItem(Item item);

    String updateItem(Item item);

    String deleteItem(Integer I_RecId);

    Pagination getByAllPage(Integer page, String searchText);
}
