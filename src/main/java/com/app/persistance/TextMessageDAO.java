package com.app.persistance;

import java.util.List;

import com.app.business.model.TextMessage;

public interface TextMessageDAO {

	Long save(TextMessage textMessage);
	
	void saveList(List<TextMessage> textMessageList);
	
	void saveArray(TextMessage[] textMessageArray);
}
