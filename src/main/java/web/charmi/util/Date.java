package web.charmi.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Date {

    SimpleDateFormat dateFormat_1;
    public Date() {
        this.dateFormat_1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    }
    public String Now() {
        DateTimeFormatter DTF=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return DTF.format(LocalDateTime.now());
    }

    public java.util.Date CDate(String str) throws Exception {
        String DateStr="", TimeStr="", DateArr[]=null;
        DateArr=str.split(" ");
        DateStr=DateArr[0];
        if (DateStr.contains("/")) {
            DateStr=DateStr.replaceAll("/","-");
        }
        if (DateArr.length==2) {
            TimeStr=DateArr[1];
        } else {
            TimeStr="00:00:00";
        }

        java.util.Date date=dateFormat_1.parse(DateStr+" "+TimeStr);
        return date;
    }
}
