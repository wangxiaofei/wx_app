package com.app.business.model.req;
/**
 * 语音消息
 * @author wangx_000
 *
 */
public class VoiceMessageReq extends BaseMessageReq {

	//媒体ID
	private String MediaId;
	//语音格式
	private String Format;
	
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
}
