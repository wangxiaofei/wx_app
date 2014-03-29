package com.app.controller;

import org.apache.log4j.Logger;
import org.apache.tomcat.websocket.server.WsSci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.business.WeChatService;
import com.app.utils.WeChatUtils;

@Controller
public class WeChatController {

	@Autowired
	private WeChatService wcService;
	private Logger logger = Logger.getLogger("wxLog");

	@RequestMapping("/")
	public @ResponseBody
	String webAccess(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr) {
		boolean rs = WeChatUtils.checkJoin(signature, timestamp, nonce);		
		if (rs) {
			logger.info("webAccess success!!!");
			return echostr;
		} else {
			logger.info("webAccess fail!!!");
			return "";
		}
	}
	
	public String receiveAndSend(){
		wcService.receiveText();
		wcService.sendText("aaa");
		return null;
	}

}
