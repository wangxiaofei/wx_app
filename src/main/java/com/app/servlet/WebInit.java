package com.app.servlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.app.utils.Constant;
import com.app.utils.TemplateConstant;

/**
 * config init
 * 
 * @author andy
 */
public class WebInit extends HttpServlet {
    /**
     * UID
     */
    private static final long serialVersionUID = 1L;

    private Logger logger = Logger.getLogger(WebInit.class);
    
    private final String END_MARK = "/";

    private String prefix = "";
    private String filePath = "";
    private Properties config = new Properties();

    public void init() throws ServletException {
        super.init();
        logger.info("#########WebInit init");
        prefix = getServletContext().getRealPath("/");
        // 模板URI
        Constant.POI_TEMPLATE_FILE_URI = prefix + getServletConfig().getInitParameter("template");
        logger.info("POI template file uri :" + Constant.POI_TEMPLATE_FILE_URI);
       
      filePath = getServletConfig().getInitParameter("config");
      logger.info("config property file path :" + filePath);

        if (null == filePath) {
            logger.error("#########WebInit init ERROR...");
            return;
        }

        InputStream inputStr = null;
        try {
            inputStr = new FileInputStream(prefix + filePath);
            config.load(inputStr);
        } catch (Exception e) {
            logger.error("#########WebInit init ERROR...");
            return;
        }

        initConstant();
        initPOITemplate();

        logger.info("###### WebInit init over");
    }

    /**
     * 初始化 静态常量 配置
     */
    private void initConstant() {
        
       Constant.WECHAT_TOKEN = config.getProperty("WECHAT_TOKEN");
        
        // 发送邮件相关
        Constant.SYS_DOMAIN = this.pathHelper(config.getProperty("SYS_DOMAIN"));
        
        Constant.EMAIL_HOST = config.getProperty("EMAIL_HOST");
        Constant.EMIAL_FROM = config.getProperty("EMIAL_FROM");
        Constant.EMAIL_PASSWORD = config.getProperty("EMAIL_PASSWORD");
        Constant.EMAIL_USER_NAME = config.getProperty("EMAIL_USER_NAME");
        Constant.LOG_IMG = config.getProperty("LOG_IMG_PATH");
        
        //url规则相关
        Constant.URL_FILTER_POSITION = config.getProperty("URL_FILTER_POSITION");
        Constant.URL_FILTER_TURN_ON = config.getProperty("URL_FILTER_TURN_ON");
        Constant.REPORT_BASE_DIR = config.getProperty("REPORT_BASE_DIR");
    }
    



    /**
     * 初始化 报表 模板
     */
    private void initPOITemplate() {
        
        Constant.POI_TEMPLATE_HELPER = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_HELPER"));
        Constant.POI_TEMPLATE_HELPER_ADMIN = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_HELPER_ADMIN"));
        TemplateConstant.POI_TEMPLATE_FILTER_URL = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_FILTER_URL"));
        TemplateConstant.POI_TEMPLATE_URL_SOURCE_PV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_URL_SOURCE_PV"));
        TemplateConstant.POI_TEMPLATE_URL_SOURCE_UV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_URL_SOURCE_UV"));
        TemplateConstant.POI_TEMPLATE_URL_DEMOGRAPHY = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_URL_DEMOGRAPHY"));
        TemplateConstant.POI_TEMPLATE_URL_SOURCE_TYPE_PV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_URL_SOURCE_TYPE_PV"));
        TemplateConstant.POI_TEMPLATE_URL_SOURCE_TYPE_UV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_URL_SOURCE_TYPE_UV"));
        TemplateConstant.POI_TEMPLATE_URL_SOURCE_PATH_PV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_URL_SOURCE_PATH_PV"));
        TemplateConstant.POI_TEMPLATE_URL_SOURCE_PATH_UV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_URL_SOURCE_PATH_UV"));
        
        TemplateConstant.POI_TEMPLATE_VISIT_MEDIA_PV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_VISIT_MEDIA_PV"));
        TemplateConstant.POI_TEMPLATE_VISIT_MEDIA_UV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_VISIT_MEDIA_UV"));
        TemplateConstant.POI_TEMPLATE_VISIT_MEDIA_TYPE_PV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_VISIT_MEDIA_TYPE_PV"));
        TemplateConstant.POI_TEMPLATE_VISIT_MEDIA_TYPE_UV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_VISIT_MEDIA_TYPE_UV"));
        TemplateConstant.POI_TEMPLATE_VISIT_SEARCH_KEYWORD_PV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_VISIT_SEARCH_KEYWORD_PV"));
        TemplateConstant.POI_TEMPLATE_VISIT_SEARCH_KEYWORD_UV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_VISIT_SEARCH_KEYWORD_UV"));
        TemplateConstant.POI_TEMPLATE_INTERVAL_TIME_PV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_INTERVAL_TIME_PV"));
        TemplateConstant.POI_TEMPLATE_INTERVAL_TIME_UV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_INTERVAL_TIME_UV"));
        
        // 对比
        TemplateConstant.POI_TEMPLATE_COMPARED_VISIT_MEDIA_PV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_COMPARED_VISIT_MEDIA_PV"));
        TemplateConstant.POI_TEMPLATE_COMPARED_VISIT_MEDIA_UV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_COMPARED_VISIT_MEDIA_UV"));
        TemplateConstant.POI_TEMPLATE_COMPARED_VISIT_MEDIA_TYPE_PV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_COMPARED_VISIT_MEDIA_TYPE_PV"));
        TemplateConstant.POI_TEMPLATE_COMPARED_VISIT_MEDIA_TYPE_UV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_COMPARED_VISIT_MEDIA_TYPE_UV"));
        TemplateConstant.POI_TEMPLATE_COMPARED_VISIT_KEYWORD_PV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_COMPARED_VISIT_KEYWORD_PV"));
        TemplateConstant.POI_TEMPLATE_COMPARED_VISIT_KEYWORD_UV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_COMPARED_VISIT_KEYWORD_UV"));
        TemplateConstant.POI_TEMPLATE_COMPARED_TIMEINTERVAL_PV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_COMPARED_TIMEINTERVAL_PV"));
        TemplateConstant.POI_TEMPLATE_COMPARED_TIMEINTERVAL_UV = this.pathHelper(Constant.POI_TEMPLATE_FILE_URI, config.getProperty("POI_TEMPLATE_COMPARED_TIMEINTERVAL_UV"));
        
    }

    /**
     * 处理 URI 在配置中 开始|结束符'/'
     * @param dir URI
     * @param cnf 配置文件中Property
     */
    private String pathHelper(String dir, String cnf) {
        String path = null;
        if (dir.endsWith(this.END_MARK)) {
            dir = dir.substring(0, dir.length() - 1);
        }
        if (cnf.startsWith(this.END_MARK)) {
            path = dir + cnf.trim();
        } else {
            path = dir + this.END_MARK + cnf.trim();
        }
        return path;
    }
    
    /**
     * 判断字符是否 以结束符"/"结尾，是：直接返回。否：加入"/"  
     * 
     * @param str
     * @return
     */
    private String pathHelper(String str){
        if (StringUtils.isNotBlank(str) && !str.endsWith(this.END_MARK)) {
            return str + this.END_MARK;
        }
        return str;
    }
}
