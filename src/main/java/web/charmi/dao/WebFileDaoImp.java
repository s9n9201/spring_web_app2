package web.charmi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import web.charmi.entity.WebFile;
import web.charmi.rowmapper.WebFileRowMapper;
import web.charmi.util.Date;
import web.charmi.util.SqlMap;
import web.charmi.util.UserDitail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class WebFileDaoImp implements WebFileDao {
    @Autowired
    SqlMap sqlMap;
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    Date date;

    @Override
    public List<WebFile> save(List<WebFile> webFileList) {
        WebFile webFile;
        String SqlStr="";
        MapSqlParameterSource[] mapSqlParameterSources=new MapSqlParameterSource[webFileList.size()];
        Map<String, String> TableMap=new HashMap<>();

        TableMap.put("F_Module", ":F_Module");
        TableMap.put("F_FromUUID",":F_FromUUID");
        TableMap.put("F_FileName",":F_FileName");
        TableMap.put("F_UUIDName",":F_UUIDName");
        TableMap.put("F_Size",":F_Size");
        TableMap.put("F_Sort",":F_Sort");
        TableMap.put("F_RecOrg",":F_RecOrg");
        SqlStr=sqlMap.insert("WebFile", TableMap);

        for (int i=0; i<webFileList.size(); i++) {
            webFile=webFileList.get(i);
            mapSqlParameterSources[i]=new MapSqlParameterSource();
            mapSqlParameterSources[i].addValue("F_Module",webFile.getFMoudle());
            mapSqlParameterSources[i].addValue("F_FromUUID",webFile.getFFromUUID());
            mapSqlParameterSources[i].addValue("F_FileName",webFile.getFFileName());
            mapSqlParameterSources[i].addValue("F_UUIDName",webFile.getFUUIDName());
            mapSqlParameterSources[i].addValue("F_Size",webFile.getFSize());
            mapSqlParameterSources[i].addValue("F_Sort",webFile.getFSort());
            mapSqlParameterSources[i].addValue("F_RecOrg", 2);   //
        }
        jdbcTemplate.batchUpdate(SqlStr, mapSqlParameterSources);
        return webFileList;
    }

    @Override
    public List<WebFile> getFileList(String Module, String FromUUID) {
        String SqlStr="select * from WebFile where F_isDelete=0 and F_Module=N'"+Module+"' and F_FromUUID=N'"+FromUUID+"' order by F_Sort ";
        return jdbcTemplate.query(SqlStr, new HashMap<>(), new WebFileRowMapper());
    }

    @Override
    public Optional<WebFile> getFile(String UUIDName) {
        String SqlStr="select * from WebFile where F_isDelete=0 and F_UUIDName=N'"+UUIDName+"' ";
        return jdbcTemplate
                .query(SqlStr, new HashMap<>(), new WebFileRowMapper())
                .stream().findFirst();
    }

    @Override
    public Optional<WebFile> deleteFile(String UUIDName) {
        WebFile webFile=getFile(UUIDName).orElse(null);
        if (webFile!=null) {
            String SqlStr="update WebFile set F_isDelete=1, F_DeleteOrg=2, F_DeleteDate=N'"+date.Now()+"' where F_isDelete=0 and F_UUIDName=N'"+webFile.getFUUIDName()+"' ";
            jdbcTemplate.update(SqlStr, new HashMap<>());
        }
        return Optional.ofNullable(webFile);
    }
}
