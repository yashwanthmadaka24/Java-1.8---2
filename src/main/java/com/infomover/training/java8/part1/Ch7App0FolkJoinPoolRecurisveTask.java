package com.infomover.training.java8.part1;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;


public class Ch7App0FolkJoinPoolRecurisveTask {

	public static void main(String[] args) {

		String arr[] = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

		ForkJoinPool fjp = new ForkJoinPool();

		MyRecursiveTask2 t = new MyRecursiveTask2(arr);
		fjp.execute(t);
		String value = t.join();

		System.out.println(value);

	}
}

class MyRecursiveTask2 extends RecursiveTask<String> {

	private String arr[];

	private static final int THRESHOLD = 2;

	public MyRecursiveTask2(String arr[]) {

		this.arr = arr;
	}

	@Override
	protected String compute() {

		System.out.println("compute : " + Thread.currentThread().getName());
		
		
		if (arr.length > THRESHOLD) {
			// fork

			MyRecursiveTask2 t1 = new MyRecursiveTask2(Arrays.copyOfRange(arr, 0, arr.length / 2));
			MyRecursiveTask2 t2 = new MyRecursiveTask2(Arrays.copyOfRange(arr, arr.length / 2, arr.length));

			
			List<MyRecursiveTask2> tasks = Arrays.asList(t1, t2);
			Collection<MyRecursiveTask2> submittedTasks = ForkJoinTask.invokeAll(tasks);

		//	String concatenatedString = "";
//			for (MyRecursiveTask2 t : submittedTasks) {
//				String val = t.join();
//				concatenatedString = concatenatedString + val;
//			}
			
			String concatenatedString =  submittedTasks
			   .stream()
			   .map(x -> {
				   
				   System.out.println("Stream processing : " + Thread.currentThread().getName());
				   return x.join();
				   
			   })
			   .collect(Collectors.joining());
			
			// java 1.8
			// sum = submittedTasks
			// .stream()
			// .mapToInt(task -> task.join())
			// .sum();

			return concatenatedString;
		} else {

			String concatenatedInTask = "";
			for (String i : arr) {
				concatenatedInTask = concatenatedInTask + i;
			}

			// String sum = Arrays.stream(arr).collect(Collectors.joining());
			
			return concatenatedInTask;
		}

	}

}
