package com.mslc.training.java7.newfeatures;

public class BinaryLiteralsDemo {

	public static void main(String[] args) {
		int i = 0b0111;
		byte b = (byte) 0b0111;
		long l = (long) 0B0111L;
		System.out.println("i=" + i);
		System.out.println("b=" + b);
		System.out.println("l=" + l);
	}

}
