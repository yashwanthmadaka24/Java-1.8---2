package com.infomover.training.java8.lambdaenabledconcurrency;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import io.vertx.core.Handler;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.net.NetSocket;

public class User implements Handler<Buffer>{
	
	private static final Pattern newLine = Pattern.compile("\\n");
	private final NetSocket socket;
	private final Set<String> names;
	private final EventBus eventBus;
	private Optional<String> name ;

	public User(NetSocket socket, Verticle verticle) {
	    this.socket = socket;
	    Vertx vertx = verticle.getVertx();
	    eventBus = vertx.eventBus();
	    names = new HashSet<>();
	}
	
	@Override
	public void handle(Buffer event) {
		
		// handling data here
		newLine.splitAsStream(event.toString())
			.forEach(line -> {
				System.out.println(line);
			});
		
	    eventBus.registerCodec(null);
	}
	
	

}
