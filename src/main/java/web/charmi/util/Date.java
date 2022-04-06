package web.charmi.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class Date {

    SimpleDateFormat dateFormat_1;
    DateTimeFormatter DTF;

    public Date() {
        this.dateFormat_1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.DTF=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").withZone(ZoneId.of("Asia/Taipei"));


    }
    public String Now() {
        return this.DTF.format(LocalDateTime.now());
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

    public String DateStr(Instant date) {
        return this.DTF.format(date);
    }
}
