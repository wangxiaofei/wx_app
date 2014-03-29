package com.app.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.business.model.resp.ArticleResp;
import com.app.business.model.resp.ImageResp;
import com.app.business.model.resp.ImageMessageResp;
import com.app.business.model.resp.NewsMessageResp;
import com.app.utils.MessageUtil;

public class Test {

	public static void main(String[] args) {
		// ImageMessage im = new ImageMessage();
		// Image image = new Image();
		// image.setMediaId("111111");
		// im.setToUserName("toUser");
		// im.setFromUserName("fromUser");
		// im.setCreateTime(new Date().getTime());
		// im.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
		// im.setImage(image);
		// String xml = MessageUtil.imageMessageToXml(im);
		// System.out.println(xml);

		// NewsMessage nm = new NewsMessage();
		// nm.setToUserName("fromUser");
		// nm.setFromUserName("fromUser");
		// nm.setCreateTime(new Date().getTime());
		// nm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		// nm.setArticleCount("2");
		// List<Article> articles = new ArrayList<Article>();
		// Article a0 = new Article();
		// a0.setTitle("title0");
		// a0.setUrl("url1");
		// a0.setPicUrl("picurl0");
		// a0.setDescription("desc0");
		// Article a1 = new Article();
		// a1.setTitle("title0");
		// a1.setUrl("url1");
		// a1.setPicUrl("picurl0");
		// a1.setDescription("desc0");
		// articles.add(a0);
		// articles.add(a1);
		// nm.setArticles(articles );
		// String xml = MessageUtil.newsMessageToXml(nm);
		// System.out.println(xml);
		
		System.out.println(new Date().getTime());
		System.out.println(new Date(Long.valueOf("1396085149352")));
	}
}
