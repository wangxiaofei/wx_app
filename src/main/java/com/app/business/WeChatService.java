package com.app.business;

import javax.servlet.http.HttpServletRequest;

public interface WeChatService {

	String processRequest(HttpServletRequest request)  throws Exception;
}
