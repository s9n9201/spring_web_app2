package web.charmi.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import web.charmi.entity.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task=new Task();
        task.setId(rs.getInt("id"));
        task.setText(rs.getString("text"));
        task.setDay(rs.getString("day"));
        task.setReminder(rs.getInt("reminder"));
        return task;
    }
}
