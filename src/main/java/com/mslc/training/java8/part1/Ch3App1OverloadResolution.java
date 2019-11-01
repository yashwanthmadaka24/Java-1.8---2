package com.mslc.training.java8.part1;

import java.util.function.BinaryOperator;

public class Ch3App1OverloadResolution {

	public static void main(String[] args) {
		
		overloaded((x, y) -> x + y);
		
		
	}
	
	static void overloaded(IntegerBiFunction lambda) {
		System.out.println("in IntegerBiFunction");
	}
	
	static void overloaded(BinaryOperator<Integer> lambda) {
		System.out.println("in BinaryOperator");
	}
}

interface IntegerBiFunction extends BinaryOperator<Integer> {
	
	
}


