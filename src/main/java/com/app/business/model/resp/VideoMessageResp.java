package com.app.business.model.resp;

public class VideoMessageResp extends BaseMessageResp{

	private VideoResp VideoResp;

	public VideoResp getVideo() {
		return VideoResp;
	}

	public void setVideo(VideoResp videoResp) {
		VideoResp = videoResp;
	}
}
