package com.mslc.training.java8.part1;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.function.LongFunction;
import java.util.function.ToLongFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.mslc.training.java8.model.HealthData;

public class Ch3App0Primitives {
	
	public static void main(String[] args) {
		
		
		// 2nd argument is IntUnaryOperator
		
		IntUnaryOperator o = x -> x + 1;
		IntStream iStream= IntStream.iterate(0, (x -> x + 1));
		iStream.limit(50).forEach(System.out::println);
		
		LongStream lStream = LongStream.of(12, 13, 14, 17);
		DoubleStream dStream = DoubleStream.of(12.2, 13.4, 15.5, 17.5);
		
		
		LongStream.Builder b =   LongStream.builder();
		b.accept(12);
		b.accept(15);
		b.add(21);
		System.out.println(" --- ");
		b.build().forEach(System.out::println);
		System.out.println(" --- ");
		
		Stream<String> s = Stream.of("20", "15" , "10");
		s.sorted()
		   .map(x -> x)
		   .limit(2)
		   .forEach(System.out::println);
	

		// int takes 4 bytes whereas Integer takes 16 bytes.
		// Boxing / Autoboxing & Unboxing is expensive - in the
		// .class file there is
		// nothing like auto boxing .. it is about creating the objects.

		// Streams for primitive is available - long, int & double.

		ToLongFunction<String> toLong = x -> x.length();
		
		toLong.applyAsLong("Some String");

		LongFunction<String> toString = x -> String.valueOf(x);
		toString.apply(12);

		/**
		 * ToIntFunction / IntFunction ToDoubleFunction / DoubleFunction
		 * 
		 */

		LongStream ls = Stream.of("1", "2", "3").mapToLong(x -> Long.valueOf(x));

		ls = LongStream.of(1, 2, 3);
		Stream<Object> objects = ls.mapToObj(x -> new Object());

		// ls.map(mapper). mapper is actually a LongUnaryOperator

		// System.out.println(ls.sum());

		/**
		 * Primitive Streams : LongStream, IntStream & DoubleStream map
		 * functions like mapToLong actually does not return Stream<Long> but a
		 * LongStream.
		 */

		// Get the statistics of age of all Dependents of all Employees

		// Likewise you have LongSummaryStatistic and also DoubleSummaryStatistics
		
		IntSummaryStatistics statistics = 
					HealthData.employeeList
						.stream()
						.flatMap(x -> x.getDependents())
						.mapToInt(x -> x.getAge())
						.summaryStatistics();
		
		System.out.println("Average age : " + statistics.getAverage() + " -- Max Age" + statistics.getMax()
				+ " --  Min Age: " + statistics.getMin());

	}

	static List<Integer> getIds() {

		return Arrays.asList(1, 2);
	}

}
