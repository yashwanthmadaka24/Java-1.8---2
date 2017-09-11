package com.infomover.training.java8.part1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class Ch8App0LongAdder {

	public static void main(String[] args) throws InterruptedException {

		
		LongAdder adder = new LongAdder();
		adder.increment();
		adder.increment();
		adder.increment();
		
		
		
		System.out.println(adder.sum());
		
		
		 LongAdder counter = new LongAdder();
	        ExecutorService executorService = Executors.newFixedThreadPool(8);

	        int numberOfThreads = 4;
	        int numberOfIncrements = 100;

	        //when
	        Runnable incrementAction = () -> IntStream
	          .range(0, numberOfIncrements)
	          .forEach((i) -> counter.increment());

	        for (int i = 0; i < numberOfThreads; i++) {
	            executorService.execute(incrementAction);
	        }

	        //then
	        executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
	        executorService.shutdown();

	        System.out.println(counter.sum() + " -- " + (numberOfIncrements * numberOfThreads));
	        
		
		
		
		
		
	}

}
