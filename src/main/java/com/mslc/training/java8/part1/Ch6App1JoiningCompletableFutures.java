package com.mslc.training.java8.part1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.sun.xml.internal.ws.util.CompletedFuture;

public class Ch6App1JoiningCompletableFutures {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		

		CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {

			return "cf1";
		});

		CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {

			return "cf2";
		});

		List<CompletableFuture<String>> cfs = Arrays.asList(cf1, cf2);

		long start = System.currentTimeMillis();
		// @formatter:off

		List<String> results = cfs.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
		 
		// @formatter:on

		long end = System.currentTimeMillis();
		System.out.println("Total time : " + (end - start));
		System.out.println(results);

	}

}
