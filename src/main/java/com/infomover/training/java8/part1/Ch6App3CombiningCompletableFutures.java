package com.infomover.training.java8.part1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Ch6App3CombiningCompletableFutures {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		
		/** Explaination : 
		 *  thenCombine combines cf1 with cf2. With thenCombine, cf2 does not wait for cf1 to get finished.  
		 *  cf2 also starts. The intend here is to combine the results; not that the start of cf2 is dependent on r
		 *  results from cf1. In such a case, cf1 & cf2 executes in parallel. The 2nd argument to thenCombine
		 *  combines the results (combining may translate to anything other than concatenating)
		 *  
		 *  In the example below, note the following
		 *  
		 *  a) cf2 does not wait till cf1 is completed
		 *  b) cf2 execution is not dependent on the results of cf1
		 *  c) The intend is to combine the results and hence the name - thenCombine
		 *  d) cf1 & cf2 must execute in different threads. The supplyAsync in the below given example ensures that
		 *  e) The total time taken for execution is ~5 seconds 
		 */
		
		
		CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {

			System.out.println("cf1 started in : " + Thread.currentThread().getName());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("finished cf1");
			return "cf1";

		});

		CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
			
			System.out.println("cf2 started in : " + Thread.currentThread().getName());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("finished cf2");

			return "cf2";
			
		});
		
		CompletableFuture<String> cf3 =  cf1.thenCombine(cf2, (x, y) -> x + " ~ " + y);
		
		long start = System.currentTimeMillis();
		String value = cf3.join();
		long end = System.currentTimeMillis();
		System.out.println(value + " : " + (end - start));
		
		
		

	}

}
