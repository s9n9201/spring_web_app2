package web.charmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import web.charmi.dao.WebFileDao;
import web.charmi.entity.WebFile;
import web.charmi.util.Tool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class WebFileServiceImp implements WebFileService {
    @Autowired
    private WebFileDao webFileDao;
    @Value("${webcharmi.app.filePath}")
    private String root;
    @Override
    public List<String> save(String Module, String UUID, MultipartFile[] files) {
        boolean hasPath=false;
        int fileSort=0;
        List<WebFile> webFileList=new ArrayList<>();
        List<String> resultList=new ArrayList<>();
        Path filePath;

        try {
            filePath=Paths.get(this.root, Module, UUID);
            Files.createDirectories(filePath);
            hasPath=true;
        } catch (Exception e) {
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
                    webFile.setFSort(0);
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

    @Override
    public Stream<Path> getFileList(String Module, String UUID) {
        Map<Path, Path> map=new HashMap<>();
        Path FilePath=Paths.get(this.root, Module, UUID);
        try {
            List<WebFile> webFileList=webFileDao.getFileList(Module, UUID);
            if (webFileList.size()>0) {
                webFileList.forEach((webFile)->{
                    map.put(Paths.get(webFile.getFUUIDName()), Paths.get(webFile.getFFileName()));
                });
                return Files.walk(FilePath, 1)
                        .filter(path -> map.containsKey(path.getFileName()))    //這裡的path.getFileName是Storage路徑下的檔案名稱，已再儲存時被編成UUID，所以直接放進Map當Key使用。
                        .map(path -> path.resolve(map.get(path.getFileName())));    //將路徑兜成「/Storage/Module/UUID/FileUUID/真實檔名」，再交由Controller層處理。
            }
            return Stream.empty();
        } catch (IOException e) {
            throw new RuntimeException("無法讀取檔案！");
        }
    }

    @Override
    public Resource loadFile(String UUIDName) {
        try {
            WebFile webFile=webFileDao.getFile(UUIDName).orElse(null);
            if (webFile!=null) {
                Path file=Paths.get(this.root, webFile.getFMoudle(), webFile.getFFromUUID(), webFile.getFUUIDName());
                Resource resource=new UrlResource(file.toUri());
                if (resource.exists() || resource.isReadable()) {
                    return resource;
                } else {
                    throw new RuntimeException("找不到該檔案！");
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("[ Charmi ] Error: "+e.getMessage());
        }
    }

    @Override
    public String deleteFile(String UUIDName) {
        String Msg="檔案刪除失敗，請重新操作！";
        WebFile webFile=webFileDao.deleteFile(UUIDName).orElse(null);
        if (webFile!=null) {
            Path filePath=Paths.get(this.root, webFile.getFMoudle(), webFile.getFFromUUID(), webFile.getFUUIDName());
            if (FileSystemUtils.deleteRecursively(filePath.toFile())) { //刪除該路徑下的檔案
                Msg="檔案刪除成功";
            }
        }
        return Msg;
    }
}
