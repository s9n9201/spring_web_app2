package web.charmi.payload.response;

import org.springframework.http.HttpStatus;

public class Message {
    private Integer status;
    private String message;
    private Integer recId;

    public Message(String message, HttpStatus status) {
        this.message=message;
        this.status=status.value();
    }
    public Message(String message, HttpStatus status, Integer recId) {
        this.message=message;
        this.status=status.value();
        this.recId=recId;
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

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId=recId;
    }
}
