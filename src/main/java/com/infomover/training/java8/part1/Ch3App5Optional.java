package com.infomover.training.java8.part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Ch3App5Optional {

	/**
	 * <p>
	 * Optional is a new core library data type that is designed to provide a
	 * better alternative to null.
	 * </p>
	 * <p>
	 * null is often used to represent the absence of a value, and this is the
	 * use case that Optional is replacing.
	 * </p>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> strings = Arrays.asList("xy", "x");
		
		/** change the value of strings to new ArrayList<>() and see
		    the exception that you get **/ 
		
		Optional<String> maxLenString = 
				 strings.stream()
				.max(Comparator.comparing(x -> x.length()));

		System.out.println(maxLenString.get());
		
		System.out.println(Optional.empty().isPresent());
		Optional.empty().ifPresent(x -> System.out.println(x));
		System.out.println(Optional.of("Some Value").get());
		String someString = null; // put Farhan and check
		Optional alsoEmpty = Optional.ofNullable(someString);
		
		System.out.println(alsoEmpty);
		System.out.println(alsoEmpty.orElse("Shakir"));
		System.out.println(alsoEmpty.orElseGet(() -> "Value provided by supplier"));
		
		

	}
}
