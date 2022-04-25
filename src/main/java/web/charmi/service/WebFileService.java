package web.charmi.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WebFileService {
    public List<String> save(String Module, String UUID, MultipartFile[] files);
}
