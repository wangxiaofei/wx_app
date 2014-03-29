package com.app.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public final class Utils {

    /*
     * 将每一行拆分成一个数组
     */
    public static String[] lineParser(String line, String split) {
        if (line == null) {
            return null;
        } else {
            String[] obj = line.split(split);
            return obj;
        }
    }
    
    public static boolean isEmpty(Object obj) {
        boolean flag = false;
        if (obj == null) {
            flag = true;
        } else if ("".equals(obj)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 将map中value值为"" 空字符串的转换为null
     * 
     * @param map
     */
    public static void validParams(Map<String, Object> map) {
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object obj = entry.getValue();
                if (obj != null && "".equals(obj.toString())) {
                    entry.setValue(null);
                }
            }
        }
    }

    /**
     * 系统中人群id规则，秒针官方定义的集合用“M”开头，后为4位阿拉伯数字 数字从0001开始自增 用户自定义的人群用“C”开头，后为7位阿拉伯数字 数字从0000001开始自增
     * 
     * @param mzid
     * @param id
     * @return
     */
    public static String getIDSign(String mzid, int id) {
        String result = mzid + id;
        if (Constant.OFFICIAL_ID_PREFIX.equals(mzid)) {
            result = mzid + getFullId(id, 4);
        } else if (Constant.FOLK_ID_PREFIX.equals(mzid)) {
            result = mzid + getFullId(id, 7);
        }
        return result;
    }

    private static String getFullId(int id, int num) {
        int length = (id + "").length();
        String fullId = id + "";
        for (int i = 0; i < num - length; i++) {
            fullId = "0" + fullId;
        }
        return fullId;
    }

    public static String dateFormat(Date date, String format) {
        if (null != date) {
            return new SimpleDateFormat(format).format(date);
        } else {
            return "";
        }
    }
    public static Date stringToDate(String str,String format) throws ParseException{
        if(str ==null){
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
         return df.parse(str);
    }
    public static float getPercent(double total, double value, int index) {
        double result = value / total;
        BigDecimal bg = new BigDecimal(result * 100);
        float f = bg.setScale(index, BigDecimal.ROUND_HALF_UP).floatValue();
        return f;
    }

    public static float getFloatFormat(float value, int index) {
        BigDecimal bg = new BigDecimal(value);
        float f = bg.setScale(index, BigDecimal.ROUND_HALF_UP).floatValue();
        return f;
    }

    public static double getDoubleFormat(double value ,int index){
        BigDecimal bg = new BigDecimal(value);
        double f = bg.setScale(index, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f;
    }

    public static String getDecimalFormat(double d , String fmt){
        DecimalFormat format = new DecimalFormat(fmt);
        return format.format(d);
    }
    public static String percentageVal(double percent, int fractionDigits) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(fractionDigits); 
        nf.setMinimumFractionDigits(fractionDigits);
        return nf.format(percent);
    }
    
}
