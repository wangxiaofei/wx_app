package com.app.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringBeanFactory {

    public static Object getBean(HttpServletRequest request, String name) {

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession()
                .getServletContext());
        return context.getBean(name);
    }
}