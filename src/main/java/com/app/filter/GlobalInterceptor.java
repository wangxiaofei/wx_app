package com.app.filter;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.app.business.model.User;
import com.app.utils.Constant;


/**
 * login interceptor,check user login or not and copy data from session
 * @author dm song
 */
public class GlobalInterceptor implements HandlerInterceptor{//extends HandlerInterceptorAdapter 

    private Logger logger = Logger.getLogger(GlobalInterceptor.class);
    
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    String context = request.getSession().getServletContext().getContextPath();
		User user = (User)request.getSession().getAttribute(Constant.SESSION_USER);
		String uri = request.getRequestURI();
		logger.debug("URI : "+uri);
		if(null == user){//未登录
		    this.writeOut(uri, context, response);
		    return false;
		}		
		return true;
	}
	
	/**
	 * 依据不同的URL进行不同的跳转
	 * @param response
	 * @throws IOException 
	 */
	protected void writeOut(String uri,String context,HttpServletResponse response) throws IOException{
	    String jumpURL = "<script type=\"text/javascript\">window.top.location.href=\"" + context+ "/\";</script>";
	    System.out.println("jumpURL : "+jumpURL);
	    Writer writer = response.getWriter();
	    writer.write(jumpURL);
	    writer.close();
	}

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
