package web.charmi.payload.response;

public class Message {
    private Integer status;
    private String message;

    Message(String message, Integer status) {
        this.message=message;
        this.status=status;
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
