package com.app.business.model.req;
/**
 * 图片消息
 */
public class ImageMessageReq extends BaseMessageReq{

	private String PicUrl;
	private String MediaId;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
