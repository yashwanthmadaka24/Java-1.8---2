package com.infomover.training.java8.part1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ch2App0IntroducingStreams {

	/**
	 * Illustrates how streams are processed. It picks up one element at a time and
	 * performs all operations on that element in the flow and then picks up the
	 * other element. If parallel then too, it is exactly the same; just that
	 * different threads may pick up the element from the stream. Note that all the
	 * operations on that element will now be processed by the same thread for the
	 * picked up element
	 * 
	 * @param args
	 * @author Muhammed Shakir
	 */
	public static void main(String[] args) {

		List<String> names = Arrays.asList("Nomura", "BNP Pariba", "Goldman Sasch", "JP Morgan", "Morgan Stanley");

		Stream<String> streamOfNames = names.stream();

		// @formatter:off

		List<String> morganCompanies = streamOfNames
				.parallel()
				.filter(name -> {

			if (name.equals("JP Morgan")) {
				System.out.println("*** starting JP Morgan filtering: " + name + " " + Thread.currentThread().getName());

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("*** ending JP Morgan filtering: " + name + " " + Thread.currentThread().getName());

			} else {

				System.out.println("filtering: " + name + " " + Thread.currentThread().getName());

			}

			System.out.println("filtering : " + name + " in thread :" + Thread.currentThread().getName());
			return name.contains("Morgan");
		}).map(name -> {

			System.out.println("mapping: " + name + " " + Thread.currentThread().getName());

			return name.toUpperCase();
		}).collect(Collectors.toList());

		 
		// @formatter:on

		System.out.println(morganCompanies);
		
		
		/** following for additional demo only [if time permits] **/
		
		/**
		 * 
		 *  There is no guarantee that the elements will be processed in an order 
		 */
		// @formatter:off

		Stream.of(1, 2, 3,4, 5, 6, 7, 8)
			.parallel()
			.forEach(x -> System.out.println(x + " -- " + Thread.currentThread()));
		
		 
		// @formatter:on
		
		/**
		 * 
		 *  In the following, forEachOrdered will be process in the order of "encounter order" 
		 *  which however, defeats the purpose of parallel  
		 */
		// @formatter:off

		System.out.println(" ***** ");
		Stream.of(1, 2, 3,4, 5, 6, 7, 8)
			.parallel()
			.forEachOrdered(x -> System.out.println(x + " -- " + Thread.currentThread()));
		
		 
		// @formatter:on
		

		

	}

}
