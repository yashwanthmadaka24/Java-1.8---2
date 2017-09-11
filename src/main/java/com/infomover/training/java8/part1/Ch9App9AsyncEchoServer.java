package com.infomover.training.java8.part1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Ch9App9AsyncEchoServer {
	private AsynchronousServerSocketChannel serverChannel;
	private Future<AsynchronousSocketChannel> acceptResult;
	private AsynchronousSocketChannel clientChannel;

	public Ch9App9AsyncEchoServer() {
		try {
			serverChannel = AsynchronousServerSocketChannel.open();
			InetSocketAddress hostAddress = new InetSocketAddress("localhost", 4999);
			serverChannel.bind(hostAddress);
			acceptResult = serverChannel.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void runServer() {
		try {
			clientChannel = acceptResult.get();
			if ((clientChannel != null) && (clientChannel.isOpen())) {
				while (true) {

					ByteBuffer buffer = ByteBuffer.allocate(32);
					Future<Integer> readResult = clientChannel.read(buffer);

					// do some computation

					Integer i = readResult.get();
					System.out.println("number of bytes read : " + i);

					buffer.flip();
					String message = new String(buffer.array()).trim();
					if (message.equals("bye")) {
						break; // while loop
					}
					message = ">> Response from server :" + message +  " at : " + new Date() +  "\n";
					buffer = ByteBuffer.wrap(new String(message).getBytes());
					Future<Integer> writeResult = clientChannel.write(buffer);

					// do some computation
					writeResult.get();
					buffer.clear();

				} // while()

				clientChannel.close();
				serverChannel.close();

			}
		} catch (InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Ch9App9AsyncEchoServer server = new Ch9App9AsyncEchoServer();
		server.runServer();
	}

}