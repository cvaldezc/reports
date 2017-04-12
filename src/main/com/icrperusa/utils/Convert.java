/**
 * 
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author christian
 *
 */
public class Convert {

    
    public static Date toDate(String date, String format){
        Date _date = null;
        try {
           SimpleDateFormat sformat = new SimpleDateFormat(format);
           _date = sformat.parse(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return _date;
    }
    
    public static Date toDate(String date){
        return toDate(date, "yyyy-mm-dd");
    }
    
    public static String toDString(Date _xdate, String format){
        String _date = "";
        try {
            SimpleDateFormat sformat = new SimpleDateFormat(format);
            _date = sformat.format(_xdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return _date;
    }
    
    public static String toDString(Date xdate){
        return toDString(xdate, "yyy-mm-dd");
    }
    
    public static String toDString(String xdate, String format){
        return toDString(toDate(xdate), format);
    }
    
}
