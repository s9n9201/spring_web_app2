package web.charmi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import web.charmi.payload.response.Message;
import web.charmi.service.WebFileService;

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
    public ResponseEntity<?> upload(@RequestParam String module, @RequestParam String fromuuid, @RequestParam MultipartFile[] files) {
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
                message=resultList.size()+"個檔案上傳成功！";
                httpStatus=HttpStatus.OK;
            } catch (Exception e) {
                message="上傳檔案失敗！";
                httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
        return ResponseEntity.status(httpStatus).body(new Message(message, httpStatus));
    }



}
