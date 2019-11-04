package com.mslc.training.java8.part1;

import java.util.concurrent.Callable;
import java.util.function.BinaryOperator;

public class Ch3App1OverloadResolution {

	public static void main(String[] args) throws Exception {

		overloaded((x, y) -> x + y);
		String s = invoke(() -> "done");

	}

	static void overloaded(IntegerBiFunction lambda) {
		System.out.println("in IntegerBiFunction : " + lambda.apply(3, 3));
	}

	static void overloaded(BinaryOperator<Integer> lambda) {
		System.out.println("in BinaryOperator : " + lambda.apply(2, 3));
	}

	static void invoke(Runnable r) {
		System.out.println("in runnable");
		r.run();
	}

	static <T> T invoke(Callable<T> c) throws Exception {
		System.out.println("in callable");
		return c.call();
	}
}

interface IntegerBiFunction extends BinaryOperator<Integer> {

}
