package web.charmi.dao;

import web.charmi.entity.WebFile;

import java.util.List;

public interface WebFileDao {
    public List<WebFile> save(List<WebFile> webFileList);
}
