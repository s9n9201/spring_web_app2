package web.charmi.dao;

import web.charmi.entity.WebFile;

import java.util.List;
import java.util.Optional;

public interface WebFileDao {
    public List<WebFile> save(List<WebFile> webFileList);
    public List<WebFile> getFileList(String Module, String FromUUID);
    public Optional<WebFile> getFile(String UUIDName);
    public Optional<WebFile> deleteFile(String UUIDName);
}
