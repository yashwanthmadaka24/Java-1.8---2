package com.infomover.training.java8.part1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Ch9App2BroadCastingEchoServer extends Thread {

	protected DatagramSocket socket = null;
	protected boolean running;
	protected byte[] buf = new byte[256];

	public Ch9App2BroadCastingEchoServer() throws IOException {

		socket = new DatagramSocket(null);
		socket.setReuseAddress(true);
		socket.bind(new InetSocketAddress(4445));
	}

	public void run() {
		running = true;

		while (running) {
			try {
				
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);
				String received = new String(packet.getData(), 0, packet.getLength());
				System.out.println("received by server : " + received);
				if (received.equals("end")) {
					running = false;
					continue;
				}
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
				running = false;
			}
		}
		socket.close();
	}
	
	public static void main(String[] args) throws IOException {
		
		new Ch9App2BroadCastingEchoServer().start();
		
	}
}
