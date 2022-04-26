package web.charmi.util;;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import web.charmi.exception.TokenRefreshException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CheckExceptionHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Date date;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException exception) {
        ConstraintViolation<?> constrainViolation=exception.getConstraintViolations().iterator().next();
        String defaultMessage=constrainViolation.getMessage();
        System.out.println("ConstraintViolation: "+defaultMessage);
        JsonNode jsonNode=objectMapper.createObjectNode().put("message",defaultMessage);
        return ResponseEntity.status(400).body(jsonNode);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        String errorField=exception.getBindingResult().getFieldError().getField();
        String errorMessage=exception.getBindingResult().getFieldError().getDefaultMessage();
        System.out.println("MethodArgumentNotValidException: "+errorField+errorMessage);
        JsonNode jsonNode=objectMapper.createObjectNode().put("message",errorField+errorMessage);
        return ResponseEntity.status(400).body(jsonNode);
    }

    @ExceptionHandler(TokenRefreshException.class)
    public ResponseEntity<Object> handleTokenRefreshException(TokenRefreshException exception) {
        JsonNode jsonNode=objectMapper.createObjectNode()
                .put("status", HttpStatus.FORBIDDEN.value())
                .put("timestamp", date.Now())
                .put("message", exception.getMessage());
        return ResponseEntity.status(403).body(jsonNode);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxUploadSizeException(MaxUploadSizeExceededException exception, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*"); //需要加這個，才不會因為檔案超過大小後，前端出現CORS的異常，才能正常噴出Response內容
        JsonNode jsonNode=objectMapper.createObjectNode()
                .put("message", "檔案太大了！")
                .put("status", HttpStatus.EXPECTATION_FAILED.value());
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(jsonNode);
    }
}
