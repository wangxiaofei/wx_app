package com.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author wanghaitao
 * 
 */
public final class DateUtils {
    public static void main(String[] args) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start =  sdf.parse("2013-03-30");
        Date end =  sdf.parse("2013-04-09");
        
        List<Date> dates =  DateUtils.getDateArrays(start, end, Calendar.DAY_OF_YEAR);
    }
    
    public static List<Date> getDateArrays(Date start, Date end, int calendarType) {  
        List<Date> ret = new ArrayList<>();  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(start);  
        Date tmpDate = calendar.getTime();  
        long endTime = end.getTime();  
        while (tmpDate.before(end) || tmpDate.getTime() == endTime) {  
          ret.add(calendar.getTime());  
          calendar.add(calendarType, 1);  
          tmpDate = calendar.getTime();  
        }  
        
        return ret;
      }  
}