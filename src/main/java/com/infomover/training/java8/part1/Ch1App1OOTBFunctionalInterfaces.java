package com.infomover.training.java8.part1;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

public class Ch1App1OOTBFunctionalInterfaces {

	public static void main(String[] args) {

		Function<Integer, Integer> adder = x -> x + 1;
		BiFunction<Integer, String, String> biFunction = (x, y) -> x + " -- " + y;
		Predicate<Integer> predicate = x -> x >= 5;
		UnaryOperator<Integer> uo = x -> x + 10;
		BinaryOperator<Integer> bo = (x, y) -> x + y + 20;

		IntUnaryOperator iuo = x -> x + 1;
		DoubleUnaryOperator duo = x -> x + 1.4;

		IntBinaryOperator ibo = (x, y) -> x + y;
		DoubleBinaryOperator dbo = (x, y) -> x + y + 2.5;

		ToIntFunction<String> tif = x -> x.length();
		ToIntBiFunction<String, String> tibf = (x, y) -> x.length() + y.length();

	}
}
