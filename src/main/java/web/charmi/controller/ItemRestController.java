package web.charmi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import web.charmi.rowmapper.TaskRowMapper;
import web.charmi.entity.Item;
import web.charmi.entity.Pagination;
import web.charmi.entity.Task;
import web.charmi.service.ItemService;
import web.charmi.util.SqlMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value="http://localhost:8080")
public class ItemRestController {

    private final static Logger log=LoggerFactory.getLogger(ItemRestController.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private NamedParameterJdbcTemplate JdbcTemplate;

    @Autowired
    private SqlMap sqlMap;

    @PostMapping("/item/insert")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String insertItem(@RequestBody @Validated(Item.Insert.class) Item item) {
        item.setIRecOrg(3);
        return itemService.insertItem(item);
    }

    @PostMapping("/item/update")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String updateItem(@RequestBody @Validated(Item.Update.class) Item item) {
        return itemService.updateItem(item);
    }

    @DeleteMapping("/item/delete/{I_RecId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String deleteItem(@PathVariable Integer I_RecId) {
        return itemService.deleteItem(I_RecId);
    }

    @GetMapping("/items")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Pagination selectPage(@RequestParam(required=false) Integer page) {
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {}

        if (page==null || page<=0) {
            page=1;
        }
        log.info("Page > {}", page);    //The Page input a mustache
        //log.warn();
        //log.error();
        return itemService.getByAllPage(page);
    }

    @GetMapping("/items/{I_RecId}")
    public Item selectOne(@PathVariable Integer I_RecId) {
        return itemService.geyByIRecId(I_RecId);
    }


    @GetMapping("/tasks")
    public List<Task> selectTasks() {
        String SqlStr="";
        List<Task> taskList;
        SqlStr="select * from Task order by id desc ";
        taskList=JdbcTemplate.query(SqlStr, new TaskRowMapper());
        return taskList;
    }

    @GetMapping("/tasks/{id}")
    public Task selectOneTask(@PathVariable Integer id) {
        List<Task> taskList;
        Map<String, Object> map=new HashMap<>();
        String SqlStr="select * from Task where id=:id";
        map.put("id", id);
        taskList=JdbcTemplate.query(SqlStr, map, new TaskRowMapper());
        if (taskList.size()>0) {
            return taskList.get(0);
        }
        return null;
    }

    @PostMapping("/tasks/insert")
    public String insertTask(@RequestBody Task task) {
        Map<String, Object> map=new HashMap<>();
        Map<String, String> TableMap=new HashMap<>();

        TableMap.put("text",":text");
        TableMap.put("day",":day");
        TableMap.put("reminder",":reminder");
        String SqlStr=sqlMap.insert("Task",TableMap);

        map.put("text",task.getText());
        map.put("day",task.getDay());
        map.put("reminder",task.getReminder());
        JdbcTemplate.update(SqlStr, map);
        return "OK";
    }

    @PostMapping("/tasks/updateReminder/{id}")
    public String updateReminder(@PathVariable Integer id) {
        String SqlStr="";
        List<Task> taskList;
        Task task=new Task();
        Map<String, Object> map=new HashMap<>();
        SqlStr="select * from Task where id=:id";
        map.put("id", id);
        taskList=JdbcTemplate.query(SqlStr, map, new TaskRowMapper());
        if (taskList.size()>0) {
            task=taskList.get(0);
            SqlStr="update task set reminder=:reminder where id=:id";
            map.put("reminder", task.getReminder() == 0 ? 1 : 0 );
            JdbcTemplate.update(SqlStr,map);
            return "OK";
        }
        return "Error";
    }

    @DeleteMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Integer id) {
        Map<String, Object> map=new HashMap<>();
        String SqlStr="delete from task where id=:id";
        map.put("id",id);
        JdbcTemplate.update(SqlStr, map);
        return "OK";
    }
}
