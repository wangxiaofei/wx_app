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
		String content = "出错咯！！！！";
		Map<String, String> map = MessageUtil.parseXml(request);
		String from = map.get("FromUserName");
		String to = map.get("ToUserName");
		String createTime = map.get("CreateTime");
		String msgType = map.get("MsgType");
		String msgId = map.get("MsgId");
		
		TextMessage tmModel = new TextMessage();
		tmModel.setCreateTime(Long.valueOf(createTime));
		tmModel.setFromUserName(from);
		tmModel.setIsDeleted(0);
		tmModel.setMsgId(Long.valueOf(msgId));
		tmModel.setToUserName(to);
		tmModel.setType(0);
		tmModel.setContent(content);
		tmDao.save(tmModel);
		
		
		TextMessageResp tm = new TextMessageResp();
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
