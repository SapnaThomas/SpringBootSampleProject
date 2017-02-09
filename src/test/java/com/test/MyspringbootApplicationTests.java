package com.test;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.eaton.training.websocket.client.WebSocketClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Start.class)
@SpringBootTest
public class MyspringbootApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testWebscoket(){
        String uri = "ws://localhost:8181/websocket";
        System.out.println("Connecting to " + uri);
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
        container.connectToServer(WebSocketClient.class, URI.create(uri));
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }
	}

}
