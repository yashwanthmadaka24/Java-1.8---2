package com.infomover.training.java8.part1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Ch6App1JoiningCompletableFutures {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		CompletableFuture<String> cf1  = CompletableFuture.supplyAsync(() -> {
			System.out.println("Started cf1 : "  + Thread.currentThread().getName());			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Completed cf1");
			return "cf1";
		});
		
		CompletableFuture<String> cf2  = CompletableFuture.supplyAsync(() -> {
			System.out.println("Started cf2 : "  + Thread.currentThread().getName());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Completed cf2");
			return "cf2";
		});
		
		List<CompletableFuture<String>> cfs = Arrays.asList(cf1, cf2);
		
		long start = System.currentTimeMillis();
		List<String> results = cfs.stream()
		   .map(CompletableFuture::join)
		   .collect(Collectors.toList());
		long end = System.currentTimeMillis();
		System.out.println("Total time : " + (end - start));
		System.out.println(results);
		

	}

}
