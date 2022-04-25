package web.charmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import web.charmi.dao.WebFileDao;
import web.charmi.entity.WebFile;
import web.charmi.util.Tool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WebFileServiceImp implements WebFileService {
    @Autowired
    private WebFileDao webFileDao;
    @Value("${webcharmi.app.filePath}")
    private String root;
    @Override
    public List<String> save(String Module, String UUID, MultipartFile[] files) {
        boolean hasPath=true;
        int fileSort=0;
        List<WebFile> webFileList=new ArrayList<>();
        List<String> resultList=new ArrayList<>();
        Path filePath;

        try {
            filePath=Paths.get(this.root, Module, UUID);
            Files.createDirectories(filePath);
        } catch (Exception e) {
            hasPath=false;
            throw new RuntimeException("建立路徑異常！");
        }

        if (hasPath) {
            resultList=Arrays.asList(files).stream().map((file)->{
                String fileUid=Tool.getUUID();
                try {
                    Files.copy(file.getInputStream(), filePath.resolve(fileUid));
                    WebFile webFile=new WebFile();
                    webFile.setFMoudle(Module);
                    webFile.setFFromUUID(UUID);
                    webFile.setFFileName(file.getOriginalFilename());
                    webFile.setFUUIDName(fileUid);
                    webFile.setFSize(file.getSize());
                    webFileList.add(webFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return file.getOriginalFilename();
            }).collect(Collectors.toList());
            if (webFileList.size()>0) {
                webFileDao.save(webFileList);
            }
        }
        return resultList;
    }
}
