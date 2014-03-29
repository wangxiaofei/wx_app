package com.app.websocket;

import java.io.IOException;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.app.business.UserService;
import com.app.business.model.User;

@ServerEndpoint(value = "/message",configurator = SpringConfigurator.class)
public class MessageServer {

	@Autowired
	private UserService userService;
	
	@OnMessage
	public void onMessage(Session session,String message){
		List<User> list = userService.getAllUserList();
		System.out.println(list.size());
		System.out.println("on Message!"+message);
		try {
			String json = JSONArray.fromObject(list).toString();
			for (Session s : session.getOpenSessions()) {
				s.getBasicRemote().sendText(json);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnOpen
	public void onOpen(Session session){
		System.out.println("websocket is open!");
	}
	@OnClose
	public void onClose(){
		System.out.println("websocket is close!");
	}
}
