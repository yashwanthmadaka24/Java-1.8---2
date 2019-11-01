package com.mslc.training.java8.part1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Ch9App4MulticastEchoServer extends Thread {

	protected MulticastSocket socket = null;
	protected byte[] buf = new byte[256];
	protected InetAddress group = null;

	public Ch9App4MulticastEchoServer() throws IOException {
		System.setProperty("java.net.preferIPv4Stack", "true");
		socket = new MulticastSocket(4446);
		socket.setReuseAddress(true);
		group = InetAddress.getByName("230.0.0.0");
		socket.joinGroup(group);
		
	}

	public void run() {
		try {
			while (true) {
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);
				String received = new String(packet.getData(), 0, packet.getLength());
				System.out.println("received by server : " + received);
				if (received.equals("end")) {
					break;
				}
				socket.send(packet);
			}
			socket.leaveGroup(group);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		new Ch9App4MulticastEchoServer().start();
		
	}

}
