package com.app.business;

public interface WeChatService {

	String receiveText();
	
	void sendText(String content);
}
