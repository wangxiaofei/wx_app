package com.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class I18NUtil {
    private static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();
    private static Logger logger = Logger.getLogger(I18NUtil.class);

    public static String getI18NMessage(String key) {
        return getI18NMessage(key, "zh_CN");
    }

    public static String getI18NMessage(String key, String locale) {
        if(StringUtils.isBlank(key)) {
            return "";
        }
        if(StringUtils.isBlank(locale)) {
            locale = "zh_CN";
        }
        if (propertiesMap.get(locale) == null) {
            InputStream is = null;
            try {
                is = Thread.currentThread().getContextClassLoader().getResourceAsStream("applicationResources_" + locale + ".properties");
                Properties tempProperties = new Properties();
                tempProperties.load(is);
                propertiesMap.put(locale, tempProperties);
            } catch (Exception e) {
                logger.warn("load I18N error", e);
                return key;
            } finally {
                try {
                    if (null != is) {
                        is.close();
                    }
                } catch (IOException e) {
                    logger.warn("close input stream error", e);
                }
            }
        }
        Properties properties = propertiesMap.get(locale);
        return properties.getProperty(key);
    }
    
    public static String getI18NMessage(String key, String... args) {
        return getI18NMessage(key, "zh_CN", args);
    }
    
    public static String getI18NMessage(String key, String locale, String... args) {
        String message = getI18NMessage(key, locale);
        int i = 0;
        for(String arg : args) {
            message = message.replace("{" + i++ + "}", arg);
        }
        return message;
    }
}
