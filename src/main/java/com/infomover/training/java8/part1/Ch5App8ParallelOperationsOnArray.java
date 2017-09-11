package com.infomover.training.java8.part1;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class Ch5App8ParallelOperationsOnArray {

	public static void main(String[] args) {
		
		double[] values = new double[5];
		
		Arrays.parallelSetAll(values, i -> {
			System.out.println(Thread.currentThread().getName() + " i : " + i);
			return values.length - i;
		});
		DoubleStream.of(values).forEach(System.out::println);
		
		System.out.println(" **** ");
		Arrays.parallelSort(values);
		DoubleStream.of(values).forEach(System.out::println);
		
	}
}
