package com.app.business.model.resp;

public class VoiceMessageResp extends BaseMessageResp{

	private VoiceResp VoiceResp;

	public VoiceResp getVoice() {
		return VoiceResp;
	}

	public void setVoice(VoiceResp voiceResp) {
		VoiceResp = voiceResp;
	}
}
