package web.charmi.util;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SqlMap {

    //SQL新增語法
    public String insert(String TableName, Map<String, String> insertColValue) {
        String SqlResult = "insert into " + TableName + " ";
        String ColStr = "", ValueStr = "";
        for(Map.Entry<String, String> ColValue : insertColValue.entrySet()) {
            ColStr += ColValue.getKey() + ", ";
            ValueStr += ColValue.getValue() + ", ";
        }
        if(ColStr.endsWith(", ")) {
            ColStr = ColStr.substring(0, ColStr.length() - 2);
        }
        if(ValueStr.endsWith(", ")) {
            ValueStr = ValueStr.substring(0, ValueStr.length() - 2);
        }
        SqlResult += " ( " + ColStr + " ) values ( "  + ValueStr + " ) ";
        return SqlResult;
    }

    //SQL更新語法
    public String update(String TableName, Map<String, String> insertColValue, String key) {
        String SqlResult="update "+TableName+" set ";
        String SqlStr="";
        for(Map.Entry<String, String> ColValue : insertColValue.entrySet()) {
            SqlStr+=(SqlStr.equals("")?"":",")
                    +ColValue.getKey()+"="+ColValue.getValue();
        }
        if (!SqlStr.equals("") && SqlStr.endsWith(",")) {
            SqlStr=SqlStr.substring(0, SqlStr.length()-1);
        }
        SqlResult+=SqlStr+" where "+key;
        return SqlResult;
    }
}
