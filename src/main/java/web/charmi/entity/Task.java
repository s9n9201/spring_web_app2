package web.charmi.entity;

import org.springframework.stereotype.Component;

@Component
public class Task {
    private Integer id;
    private String text;
    private String day;
    private Integer reminder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id=id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text=text;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day=day;
    }

    public Integer getReminder() {
        return reminder;
    }

    public void setReminder(Integer reminder) {
        this.reminder=reminder;
    }
}
