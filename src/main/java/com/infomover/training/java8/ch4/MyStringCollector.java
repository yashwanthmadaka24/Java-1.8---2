package com.infomover.training.java8.ch4;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MyStringCollector implements Collector<String, StringCombiner, String> {

	private final String delim;
	private final String prefix;
	private final String suffix;

	public MyStringCollector(String delim, String prefix, String suffix) {

		this.delim = delim;
		this.prefix = prefix;
		this.suffix = suffix;
	}

	/**
	 * A factory method to create the container. In our case - the
	 * StringCombiner which is analogous to 1st argument provided to the reduce
	 * function
	 * 
	 */
	@Override
	public Supplier<StringCombiner> supplier() {

		return () -> new StringCombiner(delim, prefix, suffix);

	}

	@Override
	public BiConsumer<StringCombiner, String> accumulator() {

		// Following is just to demonstrate the use of method reference of
		// "arbitrary object type"

		BiConsumer<StringCombiner, String> bc = (stringCombiner, word) -> stringCombiner.add(word);

		//bc = StringCombiner::add;

		return bc;

	}

	@Override
	public BinaryOperator<StringCombiner> combiner() {

		// Following is just to demonstrate the use of method reference of
		// "arbitrary object type"

		BinaryOperator<StringCombiner> sc = (x, y) -> x.merge(y);

		//sc = StringCombiner::merge;

		return sc;
	}

	@Override
	public Function<StringCombiner, String> finisher() {

		// Following is just to demonstrate the use of method reference of
		// "arbitrary object type"
		Function<StringCombiner, String> finisher =

				stringCombiner -> stringCombiner.toString();

		//finisher = StringCombiner::toString;

		return finisher;
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return Collections.emptySet();
	}

}
