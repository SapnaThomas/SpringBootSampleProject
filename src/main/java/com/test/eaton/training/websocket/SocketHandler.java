package com.test.eaton.training.websocket;

import java.io.IOException;




import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.test.eaton.training.dao.StudentService;

@Component
public class SocketHandler extends TextWebSocketHandler {

	@Autowired
	StudentService studentService;
	
    CopyOnWriteArrayList<Object> sessions= new CopyOnWriteArrayList<>();
	
    public static final String STATUS="Present";
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws IOException, InterruptedException {
		try {
			long numOfStudents = studentService.getCount(STATUS);
			for (Object webSocketSession : sessions) {
				TextMessage msg = new TextMessage("Number of Students Present :"+numOfStudents);
				((WebSocketSession) webSocketSession).sendMessage(msg);
			}
		}
		catch (Exception e) {
			System.out.println("Exception   " + e.getCause());
            throw e;
		}
	}
		
		@SuppressWarnings("unchecked")
		@Override
		public void afterConnectionEstablished(WebSocketSession webSocketSession){
			sessions.add(webSocketSession);
		}
}
