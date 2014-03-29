package com.app.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5不可逆加密,主要作用于加密用户密码
 * @author XinYi
 * @version 1.0
 * @since access 2.0
 * @date 2013-03-27
 *
 */ 
public class MD5Encrypter {

    private MD5Encrypter(){}
    
	/**
	 * MD5加密
	 * @param source
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encrypt(String source){
		String s = null;
		char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte[] tmp = md.digest();
            char[] str = new char[32];
            int k = 0;
            for(int i = 0; i < 16; i++) {
                byte b = tmp[i];
                str[k++] = hexDigits[b >>> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            s = new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
	
		return s;	
	}
	
	 /**
	 * Test MD5 Encrypt
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) {
		System.out.println(MD5Encrypter.encrypt("999"));
		System.out.println(MD5Encrypter.encrypt("ABC"));
	}
}
