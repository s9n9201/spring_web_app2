package web.charmi.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import web.charmi.entity.WebFile;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WebFileRowMapper implements RowMapper<WebFile> {
    @Override
    public WebFile mapRow(ResultSet rs, int rowNum) throws SQLException {
        WebFile webFile=new WebFile();
        webFile.setFMoudle(rs.getString("F_Module"));
        webFile.setFFromUUID(rs.getString("F_FromUUID"));
        webFile.setFFileName(rs.getString("F_FileName"));
        webFile.setFUUIDName(rs.getString("F_UUIDName"));
        webFile.setFSize(rs.getLong("F_Size"));
        webFile.setFSort(rs.getInt("F_Sort"));

        return webFile;
    }
}
