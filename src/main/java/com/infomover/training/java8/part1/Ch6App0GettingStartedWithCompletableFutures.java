package com.infomover.training.java8.part1;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.infomover.training.java8.ch6.MyTask;

public class Ch6App0GettingStartedWithCompletableFutures {

	
	public static void main(String[] args) {
		
		List<MyTask> tasks = IntStream.range(0, 10)
                .mapToObj(i -> new MyTask(1))
                .collect(toList());
		
		// Approach 1 : Sequentially 
		
		//runSequentially(tasks);
		
		// Approach 2 : Parallel Stream 
		
		// useParallelStream(tasks);
		
	
		// Approach 3 : Parallel Stream 
		
		  useCompletableFuture(tasks);
		
		// Approach 4 : Parallel Stream 
		
		  System.out.println(" ****************** ");
		   useCompletableFutureWithExecutor(tasks);
	}
	
	public static void runSequentially(List<MyTask> tasks) {
		
		  long start = System.nanoTime();
		  List<Integer> result = tasks.stream()
		                              .map(MyTask::calculate)
		                              .collect(toList());
		  
		  long duration = (System.nanoTime() - start) / 1_000_000;
		  
		  System.out.printf("Processed %d tasks in %d millis\n", tasks.size(), duration);
		  
		  System.out.println(result);
	}
	
	public static void useParallelStream(List<MyTask> tasks) {
		
		  long start = System.nanoTime();
		  List<Integer> result = tasks.parallelStream()
		                              .map(MyTask::calculate)
		                              .collect(toList());
		  long duration = (System.nanoTime() - start) / 1_000_000;
		  System.out.printf("Processed %d tasks in %d millis\n", tasks.size(), duration);
		  System.out.println(result);
		  
	}
	
	public static void useCompletableFuture(List<MyTask> tasks) {
		
		  long start = System.nanoTime();
		  
		  List<CompletableFuture<Integer>> completableFutures =
		      tasks.stream()
		           .map(t -> CompletableFuture.supplyAsync(() -> t.calculate()))
		           .collect(Collectors.toList());
		 
		  List<Integer> result =
		      completableFutures.stream()
		             .map(CompletableFuture::join)
		             .collect(Collectors.toList());
		  
		  long duration = (System.nanoTime() - start) / 1_000_000;
		  System.out.printf("Processed %d tasks in %d millis\n", tasks.size(), duration);
		  System.out.println(result);
		}
	
	public static void useCompletableFutureWithExecutor(List<MyTask> tasks) {
		
		
		  long start = System.nanoTime();
		  
		  ExecutorService executor = Executors.newFixedThreadPool(Math.min(tasks.size(), 10));
		  
		  List<CompletableFuture<Integer>> futures =
		      tasks.stream()
		           .map(t -> CompletableFuture.supplyAsync(() -> t.calculate(), executor))
		           .collect(Collectors.toList());
		 
		  List<Integer> result =
		      futures.stream()
		             .map(CompletableFuture::join)
		             .collect(Collectors.toList());
		  
		  long duration = (System.nanoTime() - start) / 1_000_000;
		  System.out.printf("Processed %d tasks in %d millis\n", tasks.size(), duration);
		  
		  System.out.println(result);
		  executor.shutdown();
		  
		  
		}
	
}
