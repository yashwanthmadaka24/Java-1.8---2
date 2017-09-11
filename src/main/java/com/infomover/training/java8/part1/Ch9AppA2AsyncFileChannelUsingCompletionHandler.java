package com.infomover.training.java8.part1;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Ch9AppA2AsyncFileChannelUsingCompletionHandler {

	public static void main(String[] args) throws Exception {

		new Ch9AppA2AsyncFileChannelUsingCompletionHandler().readFile();
		Thread.sleep(3000);


	}

	public void readFile() throws Exception {

		Path path = Paths.get(System.getProperty("user.home") + "/files/file.txt");
		AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		fileChannel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {

			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				// result is number of bytes read
				// attachment is the buffer containing content
				System.out.println(new String(attachment.array()).trim());
				
				
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {

				System.out.println("failed : " + exc.getMessage());
			}
		});
	}

}
