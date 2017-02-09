package com.test;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.eaton.training.websocket.client.WebSocketClient;

@RestController
@EnableAutoConfiguration
@ComponentScan
public class Start {
	@Autowired
	static
	WebSocketClient webSocketClient;
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Start.class, args);


	}

}