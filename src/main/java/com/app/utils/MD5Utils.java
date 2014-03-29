package com.app.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;

public class MD5Utils {

    private MessageDigest md5 = null;
    private static MD5Utils instance = null;

    /**
     * Constructor is private so you must use the getInstance method
     */
    private MD5Utils() throws NoSuchAlgorithmException {
        // 得到一个md5的消息摘要 
        md5 = MessageDigest.getInstance("MD5");
    }

    /**
     * This returns the singleton instance
     */
    public static MD5Utils getInstance() {
        if (instance == null) {
            try {
                instance = new MD5Utils();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /** 
     * 进行MD5加密 
     *  
     * @param info 
     *            要加密的信息 
     * @return String 加密后的字符串 
     */  
    public String encryptToMD5(String info) {  
        byte[] digesta = null;  
        // 
        if (StringUtils.isBlank(info)) {
            info = "";
        }
        // 添加要进行计算摘要的信息  
        md5.update(info.getBytes());  
        // 得到该摘要  
        digesta = md5.digest();  
        // 将摘要转为字符串  
        String rs = byte2hex(digesta);  
        return rs;  
    }  
      
    public String encryptToMD52(String info) {  
        byte[] digesta = null;  
        try {  
            MessageDigest alga = MessageDigest.getInstance("MD5");  
            alga.update(info.getBytes());  
            digesta = alga.digest();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        String rs = digesta.toString();  
        return rs;  
    }  
    /** 
     * 将二进制转化为16进制字符串 
     *  
     * @param b 
     *            二进制字节数组 
     * @return String UpperCase
     */  
    private String byte2hex(byte[] b) {  
        String hs = "";  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
            if (stmp.length() == 1) {  
                hs = hs + "0" + stmp;  
            } else {  
                hs = hs + stmp;  
            }  
        }  
        return hs.toUpperCase();  
    }
    
}