package com.app.business.model.req;

/**
 * 文本消息
 * @date 2013-05-19
 */
public class TextMessageReq extends BaseMessageReq {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
