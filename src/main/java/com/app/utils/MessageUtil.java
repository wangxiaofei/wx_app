package com.app.utils;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.app.business.model.resp.ArticleResp;
import com.app.business.model.resp.ImageMessageResp;
import com.app.business.model.resp.MusicMessageResp;
import com.app.business.model.resp.NewsMessageResp;
import com.app.business.model.resp.TextMessageResp;
import com.app.business.model.resp.VideoMessageResp;
import com.app.business.model.resp.VoiceMessageResp;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class MessageUtil {

	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}

	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessageResp
	 * @return
	 */
	public static String textMessageToXml(TextMessageResp textMessageResp) {
		xstream.alias("xml", textMessageResp.getClass());
		return xstream.toXML(textMessageResp);
	}

	/**
	 * 音乐消息对象转换xml
	 * 
	 * @param musicMessageResp
	 * @return
	 */
	public static String musicMessageToXml(MusicMessageResp musicMessageResp) {
		xstream.alias("xml", musicMessageResp.getClass());
		return xstream.toXML(musicMessageResp);
	}

	/**
	 * 图片消息对象转换xml
	 * @param imageMessageResp
	 * @return
	 */
	public static String imageMessageToXml(ImageMessageResp imageMessageResp) {
		xstream.alias("xml", imageMessageResp.getClass());
		return xstream.toXML(imageMessageResp);
	}

	/**
	 * 声音消息对象转换xml
	 * @param voiceMessageResp
	 * @return
	 */
	public static String voiceMessageToXml(VoiceMessageResp voiceMessageResp) {
		xstream.alias("xml", voiceMessageResp.getClass());
		return xstream.toXML(voiceMessageResp);
	}

	/**
	 * 图文消息对象转换xml
	 * @param mewsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessageResp mewsMessage) {
		xstream.alias("xml", mewsMessage.getClass());
		xstream.alias("item", new ArticleResp().getClass());  
		return xstream.toXML(mewsMessage);
	}

	/**
	 * 视频消息对象转换xml
	 * @param videoMessageResp
	 * @return
	 */
	public static String videoMessageToXml(VideoMessageResp videoMessageResp) {
		xstream.alias("xml", videoMessageResp.getClass());
		return xstream.toXML(videoMessageResp);
	}

	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

}
