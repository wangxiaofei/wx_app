package com.app.permission;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import com.app.utils.Constant;

public class AutoLoginAuthentication {

    public static List<Cookie> cookieHandler(String userName, Boolean keepLogin, String password) {
        List<Cookie> cookies = new ArrayList<>();

        Cookie tempCookie = null;

        if (keepLogin) {

            tempCookie = AutoLoginAuthentication.getCookie(Constant.REMEMBERED_USERNAME, userName);
            cookies.add(tempCookie);
            tempCookie = AutoLoginAuthentication.getCookie(Constant.REMEMBERED_PASSWORD, password);
            cookies.add(tempCookie);
            tempCookie = AutoLoginAuthentication.getCookie(Constant.REMEMBERED_STATUS, Constant.REMEMBERED_STATUS_TRUE);
            cookies.add(tempCookie);
        } else {
            tempCookie = AutoLoginAuthentication.getMovedCookie(Constant.REMEMBERED_USERNAME);
            cookies.add(tempCookie);
            tempCookie = AutoLoginAuthentication.getMovedCookie(Constant.REMEMBERED_PASSWORD);
            cookies.add(tempCookie);
            tempCookie = AutoLoginAuthentication.getCookie(Constant.REMEMBERED_STATUS, Constant.REMEMBERED_STATUS_FALSE);
            cookies.add(tempCookie);
        }

        return cookies;
    }

    /**
     * 获取 cookie maxAge 100000 Path:/
     * 
     * @param name
     * @param value
     * @return
     */
    private static Cookie getCookie(String name, String value) {
        Cookie tempCookie = new Cookie(name, BaseAuthentication.getDesencrypter().encrypt(value));
        tempCookie.setPath("/");
        tempCookie.setMaxAge(100000);

        return tempCookie;
    }

    /**
     * cookie for clear value
     * 
     * @param name
     * @return
     */
    private static Cookie getMovedCookie(String name) {
        Cookie tempCookie = new Cookie(name, null);
        tempCookie.setPath("/");
        tempCookie.setMaxAge(0);

        return tempCookie;
    }
}
