package com.infomover.training.java8.part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.infomover.training.java8.model.Employee;
import com.infomover.training.java8.model.HealthData;

public class Ch4App6RefactoringAndCustomCollectorsPart2 {

	public static void main(String[] args) {

		/** Non-interference (Do not interfere with the source data) **/

		/**
		 * Streams enable you to execute possibly-parallel aggregate operations
		 * over a variety of data sources, including even non-thread-safe
		 * collections such as ArrayList. This is possible only if we can
		 * prevent interference with the data source during the execution of a
		 * stream pipeline. Except for the escape-hatch (https://stackoverflow.com/questions/42524688/what-is-an-escape-hatch-operation-in-a-java-stream) 
		 *  operations iterator()
		 * and spliterator(), execution begins when the terminal operation is
		 * invoked, and ends when the terminal operation completes. For most
		 * data sources, preventing interference means ensuring that the data
		 * source is not modified at all during the execution of the stream
		 * pipeline. The notable exception to this are streams whose sources are
		 * concurrent collections, which are specifically designed to handle
		 * concurrent modification. Concurrent stream sources are those whose
		 * Spliterator reports the CONCURRENT characteristic.
		 * 
		 * 
		 * Accordingly, behavioral parameters in stream pipelines whose source
		 * might not be concurrent should never modify the stream's data source.
		 * A behavioral parameter is said to interfere with a non-concurrent
		 * data source if it modifies, or causes to be modified, the stream's
		 * data source. The need for non-interference applies to all pipelines,
		 * not just parallel ones. Unless the stream source is concurrent,
		 * modifying a stream's data source during execution of a stream
		 * pipeline can cause exceptions, incorrect answers, or nonconformant
		 * behavior. For well-behaved stream sources, the source can be modified
		 * before the terminal operation commences and those modifications will
		 * be reflected in the covered elements. For example, consider the
		 * following code:
		 * 
		 * 
		 **/
		
		/* The need for non-interference applies to all pipelines,
		 * not just parallel ones. */

		List<String> l = new ArrayList(Arrays.asList("one", "two"));
		Stream<String> sl = l.stream();
		l.add("three");
		String s1 = sl.collect(Collectors.joining(" "));
		System.out.println(s1);

		/** Stateless Behavior **/

		// 00053101000
		// 00001503100
		// 00005110003
		// 00005031100
		// 00053011000
		Set<Integer> seen = Collections.synchronizedSet(new HashSet<>());

		Stream<Integer> stream = Stream.of(1, 4, 3, 1, 1, 3, 2, 5, 5, 7, 8);
		Stream<Integer> added = stream.parallel().map(e -> {
//			System.out.println(Thread.currentThread().getName());
			if (seen.add(e))
				return 0;
			else
				return e;
		});
		added.forEach(System.out::print);
		System.out.println(" **** ");

		// @formatter:off

		int[] arr = IntStream
						.range(0, 5)
						.parallel()
						.map(x -> {
							try {
								Thread.sleep(new Random().nextInt(100));
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							return x * 2;
							
						})
						.toArray();
		 
		// @formatter:on

		System.out.println(arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3] + " " + arr[4]);

		/** Side Effects **/
		// following with side effects. ArrayList is not synchronized and any
		// synchronization will result in
		// contention that will undermine the benefits of parallelism

		ArrayList<String> results = new ArrayList<>();
		Pattern pattern = Pattern.compile("a*");
		Stream<String> stream2 = Stream.of("1", "2", "3", "4");
		stream2
			.filter(s -> pattern.matcher(s).matches())
			.forEach(s -> results.add(s)); // Unnecessary use of side-effects
																			
		Stream<String> stream3 = Stream.of("1", "2", "3", "4");
		List<String> listResult = stream3
									.filter(s -> pattern.matcher(s).matches())
									.collect(Collectors.toList()); // No side-effects

		/** concatenating names of all employees - Using reduce and a StringBuilder **/

		Stream<String> allStrings = Stream.of("1", "2", "3", "4", "5");
		
		// single argument reduce. The return will be Optional<< what ever the stream type is >>
		Optional<String> reducedString1 = allStrings
										.reduce((acc, element) -> {
											
											return acc + " ~ " + element;
										});
		
		System.out.println("reducedString 1 : " + reducedString1);					  	  
		// two arguments reduce. The return will be inferred based on the identity and hence not Optional. 
		// Note that identity is some concrete value and hence something will for sure return
		allStrings = Stream.of("1", "2", "3", "4", "5");
		String reducedString2 = allStrings
										.reduce("1st element", (acc, element) -> {
											
											return acc + " ~ " + element;
										});

		System.out.println("reducedString 2 : "+ reducedString2);
		
		// 3 arguments reduce - only when you want to change the return type i.e. U
		allStrings = Stream.of("1", "2", "3", "4", "5");
		StringBuilder reducedString3 = allStrings
										.reduce(new StringBuilder(), (acc, element) -> {
											if (acc.length() > 0) {
												acc.append(",");
											}
											acc.append(element);
											return acc;
											
										}, (left, right) -> {
											System.out.println(" ------ executing combiner (only when parallel stream) -----");
											return left.append(right);
											//return new StringBuilder();
										});
		reducedString3.insert(0, "[").append("]");
	    System.out.println("reducedString3 : " + reducedString3);
		
		List<Employee> emps = HealthData.employeeList;

		 StringBuilder reduced =
				 emps.stream()
				 .parallel()
				 .map( Employee:: getName)
				 .reduce( new StringBuilder(),
						 (accumulatorBuilder, theName) -> {
							 
								 if (accumulatorBuilder.length() > 0) {
									 accumulatorBuilder.append(", ");
								 }
								
								 accumulatorBuilder.append( theName);
								
								 return accumulatorBuilder;
							 },
						(left, right) -> {
							
							return left;
							//return left.append(right);
				 });
				
		 reduced.insert( 0, "["); reduced.append("]");
		 String result = reduced.toString();
		 System.out.println(" ------- ");
		 System.out.println(result);

	}
}
