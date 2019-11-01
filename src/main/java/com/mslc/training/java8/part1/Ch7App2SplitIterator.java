package com.mslc.training.java8.part1;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

public class Ch7App2SplitIterator {

	public static void main(String[] args) {

		/**
		 * About characteristics()
		 * 
		 * 
		 * The value 16464 represents the characteristics of the Spliterator
		 * partition. This is important as it is these predefined
		 * characteristics that result in what ends up in each new partition and
		 * how it is structured. The int value returned from the
		 * characteristics() call is the result of the OR’ed values of the
		 * individual characteristics for an ArrayList, which is ORDERED, SORTED
		 * and SUBSIZED
		 * 
		 * 
		 * 
		 */

		List<String> names = Arrays.asList("a", "b", "c", "d", "e");

		Spliterator<String> si1 = names.spliterator();

		System.out.println("si1 estimateSize : " + si1.estimateSize() + " -- " + si1.characteristics());

		Spliterator<String> si2 = si1.trySplit();

		System.out.println("si2 estimateSize : " + si2.estimateSize() + " -- " + si1.characteristics());

		Spliterator<String> si3 = si1.trySplit();

		System.out.println("si3 estimateSize : " + si3.estimateSize() + " -- " + si1.characteristics());

		Spliterator<String> si4 = si3.trySplit();

		si1.forEachRemaining(x -> System.out.println("si1 : " + x));
		System.out.println("**");
		si2.forEachRemaining(x -> System.out.println("si2 : " + x));
		System.out.println("**");
		si3.forEachRemaining(x -> System.out.println("si3 : " + x));

		//
		// // Will throw null as it cannot be split further
		// System.out.println("**");
		// si4.forEachRemaining(x -> System.out.println("si4 : " + x));
		//
	}

}
