package com.mslc.training.java8.part1;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

public class Ch1App1OOTBFunctionalInterfaces {

	public static void main(String[] args) {
		
		// T & R : T is input and R is return type
		Function<Integer, Integer> adder = x -> x + 1;
		System.out.println(adder.apply(5));
		
		// T & U & R : T input 1 , U inout 2 and R the return type
		BiFunction<Integer, String, String> biFunction = (x, y) -> x + " -- " + y;
		
		
		// T input and always returns boolean
		Predicate<Integer> predicate = x -> x >= 5;
		
		// Accepts an integer and returns and integer
		UnaryOperator<Integer> uo = x -> x + 10;
		
		// Accepts 2 arguments - both integers
		BinaryOperator<Integer> bo = (x, y) -> x + y + 20;

		// Accepts int and returns int; it has applyAsInt
		IntUnaryOperator iuo = x -> x + 1;
		
		// Double instead of an int
		DoubleUnaryOperator duo = x -> x + 1.4;

		// Same as IntUnaryOperator but binary - 2 arguments
		IntBinaryOperator ibo = (x, y) -> x + y;
		
		DoubleBinaryOperator dbo = (x, y) -> x + y + 2.5;

		// Takes a string and returns an int
		ToIntFunction<String> tif = x -> x.length();
		
		// Takes 2 strings and returns an int
		ToIntBiFunction<String, String> tibf = (x, y) -> x.length() + y.length();
		
		

		

	}
}
