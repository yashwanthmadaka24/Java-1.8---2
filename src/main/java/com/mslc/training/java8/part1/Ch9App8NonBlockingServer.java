package com.mslc.training.java8.part1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

public class Ch9App8NonBlockingServer {

	int port = 4001;

	Selector selector = null;

	ServerSocketChannel selectableChannel = null;

	int keysAdded = 0;

	static String QUIT_SERVER = "quit";

	static String SHUTDOWN = "shutdown";

	public Ch9App8NonBlockingServer() {
	}

	public Ch9App8NonBlockingServer(int port) {
		this.port = port;
	}

	public void initialize()

			throws IOException {
		this.selector = SelectorProvider.provider().openSelector();
		this.selectableChannel = ServerSocketChannel.open();
		this.selectableChannel.configureBlocking(false);
		InetAddress lh = InetAddress.getLocalHost();
		InetSocketAddress isa = new InetSocketAddress(lh, this.port);
		this.selectableChannel.socket().bind(isa);
	}

	public void finalize()

			throws IOException {
		this.selectableChannel.close();
		this.selector.close();
	}

	public void acceptConnections() throws IOException, InterruptedException {

		SelectionKey acceptKey = this.selectableChannel.register(this.selector, SelectionKey.OP_ACCEPT);

		while ((this.keysAdded = acceptKey.selector().select()) > 0) {

			Set readyKeys = this.selector.selectedKeys();
			Iterator i = readyKeys.iterator();

			while (i.hasNext()) {
				SelectionKey key = (SelectionKey) i.next();
				i.remove();

				if (key.isAcceptable()) {
					ServerSocketChannel nextReady = (ServerSocketChannel) key.channel();

					SocketChannel channel = nextReady.accept();
					channel.configureBlocking(false);
					SelectionKey readKey = channel.register(this.selector,
							SelectionKey.OP_READ | SelectionKey.OP_WRITE);
					readKey.attach(new ChannelCallback(channel));
				}

				else if (key.isReadable()) {
					SelectableChannel nextReady = (SelectableChannel) key.channel();

					this.readMessage((ChannelCallback) key.attachment());
				}

				else if (key.isWritable()) {
					ChannelCallback callback = (ChannelCallback) key.attachment();
					String message = "What is your name? ";
					ByteBuffer buf = ByteBuffer.wrap(message.getBytes());
					int nbytes = callback.getChannel().write(buf);
				}
			}
		}

	}

	public void writeMessage(SocketChannel channel, String message) throws IOException {
		ByteBuffer buf = ByteBuffer.wrap(message.getBytes());
		int nbytes = channel.write(buf);
	}

	static final int BUFSIZE = 8;

	public String decode(ByteBuffer byteBuffer) throws CharacterCodingException {
		Charset charset = Charset.forName("us-ascii");
		CharsetDecoder decoder = charset.newDecoder();
		CharBuffer charBuffer = decoder.decode(byteBuffer);
		String result = charBuffer.toString();
		return result;
	}

	public void readMessage(ChannelCallback callback) throws IOException, InterruptedException {
		ByteBuffer byteBuffer = ByteBuffer.allocate(BUFSIZE);
		int nbytes = callback.getChannel().read(byteBuffer);
		byteBuffer.flip();
		String result = this.decode(byteBuffer);

		if (result.indexOf("quit") >= 0)
			callback.getChannel().close();
		else if (result.indexOf("shutdown") >= 0) {
			callback.getChannel().close();
			throw new InterruptedException();
		} else {
			callback.append(result.toString());
			// If we are done with the line then we execute the callback.
			if (result.indexOf("\n") >= 0)
				callback.execute();
		}
	}

	public class ChannelCallback {
		private SocketChannel channel;

		private StringBuffer buffer;

		public ChannelCallback(SocketChannel channel) {
			this.channel = channel;
			this.buffer = new StringBuffer();
		}

		public void execute() throws IOException {

			writeMessage(this.channel, this.buffer.toString());
			buffer = new StringBuffer();
		}

		public SocketChannel getChannel() {
			return this.channel;
		}

		public void append(String values) {
			buffer.append(values);
		}

	}

	public static void main(String[] args) {

		Ch9App8NonBlockingServer nbServer = new Ch9App8NonBlockingServer(8089);

		try {
			nbServer.initialize();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		try {
			nbServer.acceptConnections();
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		catch (InterruptedException e) {

		}

	}

}
