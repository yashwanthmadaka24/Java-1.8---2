package com.infomover.training.java8.part1;

import java.util.function.Function;

public class Ch1App1OOTBFunctionalInterfacesAdvance {

	public static void main(String[] args) {

		// Composing Functions
		Function<Integer, Integer> adderBy1 = x -> x + 1;
		Function<Integer, Integer> multiplierBy2 = x -> x * 2;
		
		System.out.println(adderBy1.compose(multiplierBy2).apply(4));
		System.out.println(multiplierBy2.compose(adderBy1).apply(4));
		
		System.out.println(adderBy1.andThen(multiplierBy2).apply(4));
		
		
		System.out.println(" **** ");
		
	
		System.out.println(adderBy1.apply(5));
		// following will print 9. It will first executed the composed function and then
		// the original one
		System.out.println(adderBy1.compose(multiplierBy2).apply(4));

		// What will be the value here
		System.out.println(multiplierBy2.compose(adderBy1).apply(4));

		// Following will first execute adderBy1 and then multiplierBy2
		System.out.println(adderBy1.andThen(multiplierBy2).apply(4));

	}
}
