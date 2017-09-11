package com.infomover.training.java8.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ch7App1FolkJoinPoolRecursiveActionWithCount {

	static ForkJoinPool fjp = new ForkJoinPool();

	public static void main(String[] args) throws InterruptedException, IOException {

		// System.out.println(fjp.getParallelism() + " -- " +
		// ForkJoinPool.getCommonPoolParallelism() + " -- "
		// + fjp.getActiveThreadCount());

		//Stream<String> stream = Files.lines(Paths.get(
		//		"//Users//MuhammedShakir//poc-projects//core-java//corejava//src//main//java//com//mslc//learning//concurrency//java7//forkjoinpool//data.txt"));

		//String theString = stream.collect(Collectors.joining());

		MyRecursiveAction task = new MyRecursiveAction(
				"Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).");

		long start = System.currentTimeMillis();

		fjp.execute(task);
		task.join();

	}

}

class MyRecursiveAction extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	private String theString;
	private static final int THRESHOLD = 4;

	public MyRecursiveAction(String theString) {

		this.theString = theString;

	}

	@Override
	protected void compute() {

		if (theString.length() > THRESHOLD) {
			// fork
			String s1 = theString.substring(0, theString.length() / 2);
			String s2 = theString.substring(theString.length() / 2, theString.length());
			List<MyRecursiveAction> tasks = new ArrayList<>();
			tasks.add(new MyRecursiveAction(s1));
			tasks.add(new MyRecursiveAction(s2));
			ForkJoinTask.invokeAll(tasks);
			
			
		} else {
			String value = theString.toUpperCase();
			//synchronized (MyRecursiveAction.class) {
				count++;
				System.out.println(value.replace(" ", "-") + "  -> " + Thread.currentThread().getName() + " -- Count : "
						+ count);
		    //}

		}

	}

}
