package com.mslc.training.java8.part1;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class Ch9AppA1AsyncFileChannelReader {

	public static void main(String[] args) throws Exception {

		new Ch9AppA1AsyncFileChannelReader().readFile();

	}

	public void readFile() throws Exception {

		Path path = Paths.get(System.getProperty("user.home") + "/files/file.txt");
		AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		Future<Integer> operation = fileChannel.read(buffer, 0);

		// run other code as operation continues in background
		operation.get();

		String fileContent = new String(buffer.array()).trim();
		buffer.clear();

		System.out.println(fileContent);
	}

}
