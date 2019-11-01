package com.mslc.training.java8.ch4;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MyCustomCollector implements Collector<String, BiFunction<String, String, String>, String> {

	@Override
	public Supplier<BiFunction<String, String, String>> supplier() {
		
		System.out.println(" -> supplier");
		BiFunction<String, String, String> bi = (x, y) -> x + y;
		return () -> bi;
	}

	@Override
	public BiConsumer<BiFunction<String, String, String>, String> accumulator() {
		
		System.out.println(" -> accumulator");
		BiFunction<String, String, String> bi = (x, y) -> x + y;
		
		BiConsumer<BiFunction<String, String, String>, String>  bc = (x, y) -> {
			
			System.out.println("x : " + x + " -- " + y);
			 bi.apply("p1", y);
		};
		return bc;
	}

	@Override
	public BinaryOperator<BiFunction<String, String, String>> combiner() {
		System.out.println(" -> combiner");
		BiFunction<String, String, String> bi = (x, y) -> x + y;
		BinaryOperator<BiFunction<String,String,String>> bo = (x, y) -> bi;  
		return bo;
	}

	@Override
	public Function<BiFunction<String, String, String>, String> finisher() {
		System.out.println(" -> finisher");
		BiFunction<String, String, String> bi = (x, y) -> x + y;
		Function<BiFunction<String, String, String>, String> f = x -> x.apply("s", "d");
		return f;
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {

		return new HashSet<>();
	}

}
