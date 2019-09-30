package com.infomover.training.java8.lambdaenabledconcurrency;

import io.vertx.core.AbstractVerticle;
public class ChatVertical extends AbstractVerticle {
	
	public void start() {
		vertx.createNetServer().connectHandler(socket -> {
			
			  System.out.println("socket connected");
			  socket.handler(new User(socket, this));
			
		}).listen(7001);
		
		System.out.println("ChatVertical Started...");
	}
}

