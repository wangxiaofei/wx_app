package com.app.business.Impl;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.app.business.WeChatService;
import com.app.business.model.resp.TextMessage;
import com.app.utils.MessageUtil;

@Service
public class WeChatServiceImpl implements WeChatService {

	@Override
	public String processRequest(HttpServletRequest request) throws Exception {
		String content = "出错咯！！！！";
		Map<String, String> map = MessageUtil.parseXml(request);
		String from = map.get("FromUserName");
		String to = map.get("ToUserName");
		String createTime = map.get("CreateTime");
		String msgType = map.get("MsgType");
		String msgId = map.get("MsgId");
		TextMessage tm = new TextMessage();
		tm.setFromUserName(to);
		tm.setToUserName(from);
		tm.setCreateTime(new Date().getTime());
		tm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		
		if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
			content = "你能看到我吗？！你发的是文本信息";
		}
		if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
			content = "你能看到我吗？！你发的是图片信息";
		}
		tm.setContent(content);
		String result = MessageUtil.textMessageToXml(tm);
		return result;
	}

}
