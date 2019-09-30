package com.mslc.training.qualcomm;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class ForkJoinPoolDemo {

	public static void main(String[] args) {

		String value = "Emerging from the Modi-Trump bilateral meeting on Tuesday, the Indian and U.S. sides offered summaries of the meetings that were, in some respects, at variance with one another.\n"
				+ "\n"
				+ "At least two differences between the accounts – one on Afghanistan and the other on terror and Kashmir – were substantive.\n"
				+ "\n"
				+ "The Indian account of the meeting, conveyed by Foreign Secretary Vijay Gokhale, said that Afghanistan had not been discussed due to a lack of time.";

		String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8"};
		
		ForkJoinPool pool = new ForkJoinPool();
		MyRecursiveTask t = new MyRecursiveTask(value);

		pool.execute(t);
		String changedString = t.join();
		System.out.println(changedString);

	}

}

class MyRecursiveTask extends RecursiveTask<String> {

	private final int THRESHOLD = 4;

	private String value;

	public MyRecursiveTask(String value) {
		this.value = value;

	}

	@Override
	protected String compute() {

		if (value.length() > THRESHOLD) {
			
			MyRecursiveTask t1 = new MyRecursiveTask(value.substring(0, value.length() / 2));
			MyRecursiveTask t2 = new MyRecursiveTask(value.substring(value.length() / 2, value.length()));
			
			Collection<MyRecursiveTask> submittedTasks =   ForkJoinTask.invokeAll(Arrays.asList(t1, t2));
			String returnValue = submittedTasks
			   .stream()
			   .map(x -> x.join())
			   .collect(Collectors.joining());
			
			System.out.println("tasks broken...");
			
			return returnValue;

		} else {

			return this.value.toUpperCase();
		}
	}

}












