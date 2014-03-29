package com.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.app.business.model.User;
import com.app.utils.Constant;

public class CheckLoginFilter implements Filter {

    private Logger logger = Logger.getLogger(CheckLoginFilter.class);

    private final String LOGIN = "/views/login.jsp";

    /**
     * reserved mapping
     */
    private String reservedMapping = "reservedMapping";

    private String loginMapping = "loginMapping";

    private String loginPages = "loginPages";
    
    private String encoding = "encoding";
    
    private String contentType = "contentType";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        String uri = ((HttpServletRequest) request).getRequestURI();

        logger.debug("requestURI :" + uri);
        if (uri.matches(reservedMapping) || uri.matches(loginPages) || uri.matches(loginMapping)) {

            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding(encoding);
            logger.debug("reservedMapping | loginMapping Pass ...");
            chain.doFilter(request, response);
        } else {

            User user = (User) session.getAttribute(Constant.SESSION_KEY_USER);
            if (null == user) {

                ((HttpServletResponse) response).sendRedirect(LOGIN);
            } else {
                
                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding(encoding);
                logger.debug("has login user pass");
                chain.doFilter(request, response);
            }
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
        this.setContentType(filterConfig.getInitParameter(contentType));
        this.setEncoding(filterConfig.getInitParameter(encoding));
        this.setLoginMapping(filterConfig.getInitParameter(loginMapping));
        this.setReservedMapping(filterConfig.getInitParameter(reservedMapping));
        this.setLoginPages(filterConfig.getInitParameter(loginPages));
    }

    @Override
    public void destroy() {

    }

    public String getReservedMapping() {
        return reservedMapping;
    }

    public void setReservedMapping(String reservedMapping) {
        this.reservedMapping = reservedMapping;
    }

    public String getLoginMapping() {
        return loginMapping;
    }

    public void setLoginMapping(String loginMapping) {
        this.loginMapping = loginMapping;
    }

    public String getLoginPages() {
        return loginPages;
    }

    public void setLoginPages(String loginPages) {
        this.loginPages = loginPages;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
