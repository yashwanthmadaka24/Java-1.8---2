package com.mslc.training.qualcomm;

import java.nio.ByteBuffer;

public class DirectBufferDemo {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		ByteBuffer bb = ByteBuffer.allocateDirect(300 * 1024 * 1204);
		System.out.println("Memory allocated");
		Thread.sleep(Long.MAX_VALUE);
		
		
		
	}

}
