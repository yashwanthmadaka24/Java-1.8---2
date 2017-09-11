package com.infomover.training.java8.part1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ch6App4MultipleFuturesInParallel {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");

		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {

			System.out.println("in : " + Thread.currentThread().getName());

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (true) {
				// throw new RuntimeException("Error");
			}
			return " World";
		}).handle((s, t) -> (t == null ? s : t.getMessage()));

		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
		// will always return null;
		//System.out.println(combinedFuture.get());

		System.out.println(future1.isDone());
		System.out.println(future2.isDone());
		System.out.println(future3.isDone());
		
		

		// to get the values

		// The CompletableFuture.join() method is similar to the get method, but
		// it throws an unchecked exception in case the Future does not complete
		// normally. This makes it possible to use it as a method reference in
		// the Stream.map() method.

		String combined = Stream.of(future1, future2, future3)
				.map(CompletableFuture::join)
				.collect(Collectors.joining(" "));

		System.out.println(combined);

	}

}
