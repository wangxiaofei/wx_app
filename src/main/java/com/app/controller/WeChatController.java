package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.business.WeChatService;
import com.app.utils.SignUtil;

@Controller
public class WeChatController {

	@Autowired
	private WeChatService wcService;
	private Logger logger = Logger.getLogger("wxLog");

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody
	String process(HttpServletRequest request, HttpServletResponse response) {
		logger.info("收到客户端消息");
		String respStr;
		try {
			respStr = wcService.processRequest(request);
		} catch (Exception e) {
			logger.error(e.getMessage());
			respStr = "请求错误！";
		}
		if (respStr != null) {
			return respStr;
		} else {
			return "";
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody
	String webAccess(HttpServletRequest request, HttpServletResponse response, @RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr) {

		boolean rs = SignUtil.checkSignature(signature, timestamp, nonce);
		if (rs) {
			logger.info("webAccess success!!!");
			return echostr;
		} else {
			logger.info("webAccess fail!!!");
			return "";
		}
	}
}
