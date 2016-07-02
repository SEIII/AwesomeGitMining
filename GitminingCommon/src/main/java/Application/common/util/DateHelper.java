package Application.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateHelper {
    
    public static Map<String, Integer> dateBetween(Date beforeDate,Date now){
        
        int days = daysBetween(beforeDate,now);
        int year = days/365;
        days = days%365;
        
        int month = days/30;
        int day = days%30;
        
        Map<String, Integer> dateMap = new HashMap<>();
        dateMap.put("year", year);
        dateMap.put("month", month);
        dateMap.put("day", day);
        
        return dateMap;
        
    }
    
    public static int daysBetween(Date beforeDate,Date now) {
        Calendar cNow = Calendar.getInstance();
        Calendar cReturnDate = Calendar.getInstance();
        cNow.setTime(now);
        cReturnDate.setTime(beforeDate);
        setTimeToMidnight(cNow);
        setTimeToMidnight(cReturnDate);
        long todayMs = cNow.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        long intervalMs = todayMs - returnMs;
        return millisecondsToDays(intervalMs);
      }

      private static int millisecondsToDays(long intervalMs) {
        return (int) (intervalMs / (1000 * 86400));
      }

      private static void setTimeToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
      }
      
      public static void main(String[] args) throws ParseException {
          SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
          java.util.Date date= myFormatter.parse("2013-05-1"); 
          java.util.Date mydate= myFormatter.parse("2000-12-30");
          
//          dateBetween(date, mydate);
          
          System.out.println(dateBetween(mydate,date));
    }
      
}
