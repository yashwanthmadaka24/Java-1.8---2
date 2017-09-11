package com.infomover.training.java8.part1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Ch6App2ComposingCompletableFutures {


	/** Explaination : 
	 *  thenCompose function executes the CFs sequentially because cf2 may be dependent on the results of cf1.
	 *   
	 *  How will the result of cf1 will be used (composed) by cf2, is upto the implementation of cf2 Supplier
	 *  
	 *  In the example below, note the following
	 *  
	 *  a) cf2 does not cick off till the time cf1 is not completed
	 *  b) cf2 and cf1 may run in different threads but still cf2 will start only after cf1 is completed with its results
	 *  c) So essentially this is sequential as cf2 is dependent on the value of cf1
	 *  d) The total time taken for execution is 8+ seconds 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		
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

		CompletableFuture<String> cf3 = cf1.thenCompose(x ->  CompletableFuture.supplyAsync(() -> {
			
			System.out.println("cf2 started in : " + Thread.currentThread().getName());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return x + " ~ cf2";
		}));
		
		long start = System.currentTimeMillis();
		String value = cf3.join();
		long end = System.currentTimeMillis();
		System.out.println(value + " : " + (end - start));
		
		
		

	}

}
