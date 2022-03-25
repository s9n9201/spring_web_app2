package web.charmi.util;;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CheckExceptionHandler {

    @Autowired
    ObjectMapper objectMapper;

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
        String defaultMessage=exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        System.out.println("MethodArgumentNotValid: "+defaultMessage);
        JsonNode jsonNode=objectMapper.createObjectNode().put("message",defaultMessage);
        return ResponseEntity.status(400).body(jsonNode);
    }
}
