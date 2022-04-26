package web.charmi.payload.response;

import org.springframework.http.HttpStatus;

public class Message {
    private Integer status;
    private String message;

    public Message(String message, Integer status) {
        this.message=message;
        this.status=status;
    }
    public Message(String message, HttpStatus status) {
        this.message=message;
        this.status=status.value();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status=status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message=message;
    }
}
