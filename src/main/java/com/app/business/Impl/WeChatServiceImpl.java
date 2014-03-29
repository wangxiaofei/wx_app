package com.app.business.Impl;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.business.WeChatService;
import com.app.business.model.TextMessage;
import com.app.business.model.resp.TextMessageResp;
import com.app.persistance.TextMessageDAO;
import com.app.utils.MessageUtil;

@Service
public class WeChatServiceImpl implements WeChatService {

	@Autowired
	TextMessageDAO tmDao;

	@Override
	public String processRequest(HttpServletRequest request) throws Exception {

		String content_send = "出错咯！！！！";
		Map<String, String> map = MessageUtil.parseXml(request);
		String from_receive = map.get("FromUserName");
		String to_receive = map.get("ToUserName");
		String createTime_receive = map.get("CreateTime");
		String msgType_receive = map.get("MsgType");
		String msgId_receive = map.get("MsgId");
		String content_receive = map.get("Content");

		TextMessageResp tm = new TextMessageResp();
		tm.setFromUserName(to_receive);
		tm.setToUserName(from_receive);
		tm.setCreateTime(new Date().getTime());
		tm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

		if (msgType_receive.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
			content_send = "你一直说"+content_receive+"\n你这么傻缺你家人造吗？";
			TextMessage receive = new TextMessage();
			receive.setCreateTime(new Date());
			receive.setFromUserName(from_receive);
			receive.setIsDeleted(0);
			receive.setMsgId(Long.valueOf(msgId_receive));
			receive.setToUserName(to_receive);
			receive.setType(0);
			receive.setContent(content_receive);
			tmDao.save(receive);
		}
		if (msgType_receive.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
			
			content_send = "图片碎了，请重发一次！！！";
		}
		tm.setContent(content_send);
		String result = MessageUtil.textMessageToXml(tm);

		

		TextMessage send = new TextMessage();
		send.setCreateTime(new Date());
		send.setFromUserName(to_receive);
		send.setIsDeleted(0);
		send.setMsgId(Long.valueOf(msgId_receive));
		send.setToUserName(from_receive);
		send.setType(1);
		send.setContent(content_send);
		tmDao.save(send);
		return result;
	}

}
