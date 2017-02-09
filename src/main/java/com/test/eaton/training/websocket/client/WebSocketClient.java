package com.test.eaton.training.websocket.client;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.stereotype.Component;
@Component
@ClientEndpoint
public class WebSocketClient {

	Session userSession=null;
	//private MessageHandler messageHandler;
	
	   /**
     * Callback hook for Connection open events.
     *
     * @param userSession the userSession which is opened.
     */
	/*  @OnOpen
	    public void onOpen(Session session, EndpointConfig config) {
	        System.out.println("Connected to endpoint: " + session.getBasicRemote());
	        this.userSession = session;
	        sendMessage("Welcome to WebSocket");
	    }

    *//**
     * Callback hook for Connection close events.
     *
     * @param userSession the userSession which is getting closed.
     * @param reason the reason for connection close
     *//*
    @OnClose
    public void onClose(Session userSession) {
        System.out.println("closing websocket");
        this.userSession = null;
    }

    *//**
     * Callback hook for Message Events. This method will be invoked when a client send a message.
     *
     * @param message The text message
     *//*

        @OnMessage
        public void onMessage(String text) {
            System.out.println("Received response in client from server: " + text);
        }
    

    *//**
     * register message handler
     *
     * @param msgHandler
     *//*
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    *//**
     * Send a message.
     *
     * @param message
     *//*
    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }


    public static interface MessageHandler {

        public void handleMessage(String message);
    }*/
}
