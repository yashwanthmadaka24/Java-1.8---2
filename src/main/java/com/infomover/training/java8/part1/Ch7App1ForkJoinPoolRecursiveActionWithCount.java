package com.infomover.training.java8.part1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;

public class Ch7App1ForkJoinPoolRecursiveActionWithCount {

	static ForkJoinPool fjp = new ForkJoinPool();

	public static void main(String[] args) throws InterruptedException, IOException {
		
//		String age1 = "25";
//		String age2 = "25";
//		System.out.println(age1 == age2);
		
		Integer age1 = 250;
		Integer age2 = 250;
		
		System.out.println(age1 == age2);
		
		
		
		if (true) {
			return;
		}

		// System.out.println(fjp.getParallelism() + " -- " +
		// ForkJoinPool.getCommonPoolParallelism() + " -- "
		// + fjp.getActiveThreadCount());

		// Stream<String> stream = Files.lines(Paths.get(
		// "//Users//MuhammedShakir//poc-projects//core-java//corejava//src//main//java//com//mslc//learning//concurrency//java7//forkjoinpool//data.txt"));

		// String theString = stream.collect(Collectors.joining());

		String value = "Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).Amazon Prime Day is almost here, and if you’re serious about finding the best deals, admittedly you’ll probably refrain from shopping this weekend and see what Amazon has to offer (like big discounts on its own devices).";

		MyRecursiveAction task = new MyRecursiveAction(value);
		System.out.println("Len of String : " + value.length());
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
			MyRecursiveAction a1 = new MyRecursiveAction(s1);
			MyRecursiveAction a2 = new MyRecursiveAction(s2);
//			a1.invoke();
//			System.out.println("Thread : " + Thread.currentThread().getName());
//			a2.invoke();
			tasks.add(a1);
			tasks.add(a2);
			Collection<MyRecursiveAction> submittedTasks = ForkJoinTask.invokeAll(tasks);
			

//					   .collect(Collectors.joining());

		} else {
			String value = theString.toUpperCase();
//			System.out.println(value);
			synchronized (MyRecursiveAction.class) {

			count++;

				System.out.println(
						value.replace(" ", " ") + "  -> " + Thread.currentThread().getName() + " -- Count : " + count);
			}

		}

	}

}
