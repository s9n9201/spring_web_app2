package web.charmi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import web.charmi.payload.response.FileInfo;
import web.charmi.payload.response.Message;
import web.charmi.service.WebFileService;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value="http://localhost:8080")
public class WebFileController {
    @Autowired
    WebFileService webFileService;

    @PostMapping("/upload")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Message> upload(@RequestParam String module, @RequestParam String fromuuid, @RequestParam MultipartFile[] files) {
        boolean NoFile=false;
        String message="";
        HttpStatus httpStatus=null;

        NoFile=Arrays.asList(files).stream().map((file)->file.isEmpty()).collect(Collectors.toList()).contains(true);
        if (module==null || module.equals("") || fromuuid==null || fromuuid.equals("")) {
            message="上傳資料異常，請重新操作！";
            httpStatus=HttpStatus.BAD_REQUEST;
        } else if (NoFile) {
            message="請選擇一個檔案！";
            httpStatus=HttpStatus.BAD_REQUEST;
        } else {
            try {
                List<String> resultList=webFileService.save(module, fromuuid, files);
                message=resultList.size()+"個檔案";
                httpStatus=HttpStatus.OK;
            } catch (Exception e) {
                message="上傳檔案失敗";
                httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
        return ResponseEntity.status(httpStatus).body(new Message(message, httpStatus));
    }

    @GetMapping("/files/{Module}/{FromUUID}")
    public ResponseEntity<List<FileInfo>> getFileList(@PathVariable String Module, @PathVariable String FromUUID) {
        List<FileInfo> fileInfoList=webFileService.getFileList(Module, FromUUID).map(path -> {
            String FileName=path.getFileName().toString();
            String UUIDName=path.getParent().getFileName().toString();
            String Url=MvcUriComponentsBuilder
                    .fromMethodName(WebFileController.class, "getFile", UUIDName, FileName).build().toString();
            return new FileInfo(FileName, Url, 0);
        }).collect(Collectors.toList());
        return ResponseEntity.ok().body(fileInfoList);
    }

    @GetMapping("/download/{UUIDName}/{FileName:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String UUIDName, @PathVariable String FileName) {
        Resource file=webFileService.loadFile(UUIDName);
        if (file!=null) {
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+new String( FileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1 ) )
                    .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletefile/{UUIDName}")
    public ResponseEntity<Message> deleteFile(@PathVariable String UUIDName) {
        HttpStatus httpStatus=HttpStatus.OK;
        String Result=webFileService.deleteFile(UUIDName);
        if (Result.contains("失敗")) {
            httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(new Message(Result, httpStatus));
    }

}
