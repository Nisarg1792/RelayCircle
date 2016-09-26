package com.user.circlerelay;
 
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
 
@WebSocket
public class UserServiceWebSocket {
 
    private Session session;
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private UserManager  manager = new UserManager();

    // called when the socket connection with the browser is established
    @OnWebSocketConnect
    public void handleConnect(Session session) {
        this.session = session;
    }
 
    // called when the connection closed
    @OnWebSocketClose
    public void handleClose(int statusCode, String reason) {
        System.out.println("Connection closed with statusCode=" 
            + statusCode + ", reason=" + reason);
    }
 
    // called when a message received from the browser
    @OnWebSocketMessage
	public void handleMessage(String message) {
		if (message.startsWith("add")) {
			String[] data = message.split(",");
			User user = new User();
			user.setUserName(data[1]);
			user.setColor(data[2].substring(0, data[2].indexOf(".")));
			manager.add(user);
			manager.printUsers();
			
		   
		    executor.scheduleAtFixedRate(() -> send(getJsonObject()), 0, 5, TimeUnit.SECONDS);
		} else {
			switch (message) {
			case "start":
				send("Circle Relay service started!");
				executor.scheduleAtFixedRate(() -> send("started"), 0, 5, TimeUnit.SECONDS);
				break;
			case "stop":
				this.stop();
				break;
			}
		}
	}
    
  
    public String getJsonObject(){
    	//i need to create everytime to make sure old values are removed
    	
    	JSONObject json = new JSONObject(manager.getUsers());
    	 
    	
    	System.out.printf( "JSON: %s", json.toString() );
	    //json.putAll( manager.getUsers() );
	    return json.toString();
    }
    
    // called in case of an error
    @OnWebSocketError
    public void handleError(Throwable error) {
        error.printStackTrace();    
    }
 
    // sends message to browser
    private void send(String message) {
        try {
            if (session.isOpen()) {
                session.getRemote().sendString(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    // closes the socket
    private void stop() {
        try {
            session.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}