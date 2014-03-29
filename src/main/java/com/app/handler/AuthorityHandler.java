package com.app.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.app.business.model.User;
import com.app.utils.Constant;

public class AuthorityHandler extends HandlerInterceptorAdapter  {

    private Logger logger = Logger.getLogger(AuthorityHandler.class);

    private final String LOGIN = "/views/login.jsp";
    private final String NON_AUTHORITY = "/views/error/authority.jsp";
    
    /**
     * 保留 mapping
     */
    private String reservedMapping;

    /**
     * This implementation always returns <code>true</code>.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        
        User user = (User) request.getSession().getAttribute(Constant.SESSION_KEY_USER);
        if (null == user) {
            response.sendRedirect(LOGIN);
            return false;
        }
        
        if (!Constant.ROLE_ADMIN.equals(user.getRole())) {
            String uri = request.getRequestURI();
            if (uri.matches(reservedMapping)) {
                // 需要判断处理的id 是否是登录用户可以处理的(自己创建的)
                
                /*Map<String, Object> map = new HashMap<String, Object>();
                map.put("taskId", request.getParameter("taskId"));
                TaskVO taskVO = this.taskService.getTaskByTaskId(map);
                if (null != taskVO && taskVO.getCreateUserId().equals(user.getUserId())) {
                    return super.preHandle(request, response, handler);
                }*/
            }
            logger.info("Authority Error: Normal User want to views task or account module userId:" + user.getUserId());
            response.sendRedirect(NON_AUTHORITY);
            return false;
        }
        
        return super.preHandle(request, response, handler);
    }
    
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public String getReservedMapping() {
        return reservedMapping;
    }

    public void setReservedMapping(String reservedMapping) {
        this.reservedMapping = reservedMapping;
    }
    
}