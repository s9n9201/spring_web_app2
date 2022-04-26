package web.charmi.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface WebFileService {
    public List<String> save(String Module, String UUID, MultipartFile[] files);
    public Stream<Path> getFileList(String Module, String UUID);
    public Resource loadFile(String UUIDName);
    public String deleteFile(String UUIDName);
}
