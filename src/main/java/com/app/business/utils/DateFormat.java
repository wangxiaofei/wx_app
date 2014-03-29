package com.app.business.utils;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 有关日期与字符串之间转换的工具类
 * @author 
 *
 */
public class DateFormat {
	/**
	 * 只有年日期格式，yyyy
	 */
	public static String YEAR_FORMAT = "yyyy";
	/**
	 * 数据库日期格式，yyyy-MM-dd
	 */
	public static String SQL_FORMAT = "yyyy-MM-dd";
	/**
	 * util工具日期格式，yyyy-MM-dd HH:mm:ss
	 */
	public static String UTIL_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * util工具日期格式，yyyy-MM-dd HH:mm:ss.sss
	 */
	public static String UTIL_DETAIL_FORMAT = "yyyy-MM-dd HH:mm:ss.sss";

	/**
	 * 将Date按照指定格式转换成String类型
	 * @param date
	 * @param pattern "yyyy-MM-dd HH:mm:ss"
	 * @return String
	 */
	public static String dateToString(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * String转换成Date
	 * @param str "2012-12-20 06:12:34"
	 * @param pattern "yyyy-MM-dd HH:mm:ss"
	 * pattern范围要比str小
	 * @return  Date
	 */
	public static Date stringToDate(String str,String pattern){
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			date=sdf.parse(str.trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 将日期增加指定月数
	 * @param date
	 * @param month
	 * @return
	 * @author XinYi
	 * @since access 2.0
	 */
	public static Date addMonth(Date date,Integer month){
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    month = null == month ? 0 : month;
	    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+month.intValue());
	    return cal.getTime();
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(DateFormat.dateToString(date, DateFormat.YEAR_FORMAT));
		System.out.println(DateFormat.dateToString(date, DateFormat.SQL_FORMAT));
		System.out.println(DateFormat.dateToString(date, DateFormat.UTIL_DETAIL_FORMAT));
		System.out.println(DateFormat.dateToString(date, DateFormat.UTIL_FORMAT));
		System.out.println(DateFormat.dateToString(date, "yyyy-MM-dd HH"));
		
		System.out.println(DateFormat.stringToDate("2013-01-13", DateFormat.SQL_FORMAT));
		
	}
}
