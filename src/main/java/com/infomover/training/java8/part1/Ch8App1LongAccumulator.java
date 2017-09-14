package com.infomover.training.java8.part1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

public class Ch8App1LongAccumulator {

	public static void main(String[] args) throws InterruptedException {

		ExecutorService executorService = Executors.newFixedThreadPool(8);
		LongBinaryOperator sum = Long::sum;
		// sum = (x, y) -> x + y;
		LongAccumulator accumulator = new LongAccumulator(sum, 0L);
		int numberOfThreads = 4;
		int numberOfIncrements = 100;

		// when
		// IntStream.rangeClosed : startInclusive & endInclusive
		Runnable accumulateAction = () -> 
				IntStream.rangeClosed(0, numberOfIncrements)
				.forEach(accumulator::accumulate);

		for (int i = 0; i < numberOfThreads; i++) {
			executorService.execute(accumulateAction);
		}

		// then
		executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
		executorService.shutdown();
		System.out.println(accumulator.get() + " -- " + 20200);

	}

}
