package web.charmi.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import web.charmi.entity.Item;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemDaoImpTest {

    @Autowired
    ItemDao itemDao;

    @Test
    @Transactional
    public void insertItem() {
        Item item=new Item();

        item.setIName("熱敷眼罩");
        item.setITRecId(1);
        item.setISource("批店");
        item.setIMadeIn("大陸");
        item.setIAmount(Float.parseFloat("10.0"));
        item.setICost(Float.parseFloat("300"));
        item.setIPrice(Float.parseFloat("450"));
        item.setITotal(Float.parseFloat("4500"));
        item.setIRecOrg(3);

        int I_RecId=itemDao.insertItem(item);
        Item result=itemDao.getByIRecId(I_RecId);
        assertNotNull(result);

        assertEquals("熱敷眼罩",result.getIName());
        assertEquals("批店",result.getISource());
        assertEquals(Float.parseFloat("10.0"),result.getIAmount());
        assertNotNull(result.getIRecDate());
    }

    @Test
    @Transactional
    public void updateItem() {
        Item item=itemDao.getByIRecId(1);

        item.setIName("熱敷眼罩");
        itemDao.updateItem(item);

        Item result=itemDao.getByIRecId(1);

        assertNotNull(item);
        assertEquals("熱敷眼罩", result.getIName());

    }

    @Test
    @Transactional
    public void getByIRecId() {
        Item item=itemDao.getByIRecId(1);

        assertNotNull(item);
        assertEquals("WPC紅色雨傘",item.getIName());
    }
}