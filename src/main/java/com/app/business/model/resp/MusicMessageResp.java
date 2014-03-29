package com.app.business.model.resp;

public class MusicMessageResp extends BaseMessageResp {

	private MusicResp MusicResp;

	public MusicResp getMusic() {
		return MusicResp;
	}

	public void setMusic(MusicResp musicResp) {
		MusicResp = musicResp;
	}
}
