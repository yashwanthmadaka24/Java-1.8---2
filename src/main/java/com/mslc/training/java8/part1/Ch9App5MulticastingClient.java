package com.mslc.training.java8.part1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class Ch9App5MulticastingClient {
	private DatagramSocket socket;
	private InetAddress group;
	private int expectedServerCount;
	private byte[] buf;

	public Ch9App5MulticastingClient(int expectedServerCount) throws Exception {
		this.expectedServerCount = expectedServerCount;
		this.socket = new DatagramSocket();
		this.group = InetAddress.getByName("230.0.0.0");
	}

	public int discoverServers(String msg) throws IOException {
		copyMessageOnBuffer(msg);
		multicastPacket();

		return receivePackets();
	}

	private void copyMessageOnBuffer(String msg) {
		buf = msg.getBytes();
	}

	private void multicastPacket() throws IOException {
		DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
		socket.send(packet);
	}

	private int receivePackets() throws IOException {
		int serversDiscovered = 0;
		while (serversDiscovered != expectedServerCount) {
			receivePacket();
			serversDiscovered++;
		}
		return serversDiscovered;
	}

	private void receivePacket() throws IOException {
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String received = new String(packet.getData(), 0, packet.getLength());
		System.out.println("received by client: " + received);

	}

	public void close() {
		socket.close();
	}

	public static void main(String[] args) throws IOException, Exception {
		while (true) {
			new Ch9App5MulticastingClient(1).discoverServers("New Message : " + new Date());
			Thread.sleep(1000);
		}

	}
}
