package com.infomover.training.java8.part1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.infomover.training.java8.model.Employee;
import com.infomover.training.java8.model.HealthData;

public class Ch4App1EntertheCollector {

	public static void main(String[] args) {
		
		List<Integer> numbers = Arrays.asList(10, 10, 10, 412, 322, 112);
		
		Set<Integer>  numbers2 =  numbers
			.stream()
			.collect(Collectors.toSet());
		
		System.out.println("Original List : " + numbers);
		System.out.println("List converted to Set : " + numbers2);
		
		Set<Integer>  numbers3 =  numbers
				.stream()
				.collect(Collectors.toCollection(TreeSet::new));
			
		System.out.println("List converted to TreeSet : " + numbers3);
		
		
		Map<Integer, Integer>  numbers4 =  numbers2
				.stream()
				.collect(Collectors.toMap(   x -> x,      x -> x * 10    ));
		
		
		numbers4.forEach((x, y) -> System.out.print(x + " -- " +  y + " | "));
		System.out.println("\n****");
		
		/**
		 * Get the Employee with max dependents using Collectors.maxBy
		 * 
		 */
		
		Stream<Employee> employees = HealthData.employees;
		
		// @formatter:off

		//Comparator<Employee> employeeComparator = Comparator.comparing(x -> x.getDependentList().size());
		
		Comparator<Employee> employeeComparator = Comparator.comparing(x -> x.getDependents().count());
		Optional<Employee> emp0 = 
				employees
					.collect(Collectors.maxBy(employeeComparator));
		System.out.println("max : " + emp0.get());
		
				 
		// @formatter:on
		
		Optional<Employee> emp = 
				HealthData.employeeList.stream()
					.collect(Collectors
								.maxBy(Comparator
										.comparing(x -> 
											x.getDependents().count())));
		
		
		
		
		// Yet another way to write
		
		
		Stream<Employee> employees2 = HealthData.employeeList.stream();
		
		
		Function<Employee, Long> comparingFunction = 
				  employee -> employee.getDependents().count();
		
		emp = employees2
				.collect(Collectors.maxBy(Comparator.comparing(comparingFunction)));
		
		System.out.println(emp.isPresent() ? emp.get() : "None");
		
		
		Stream<Employee> employees3 = HealthData.employeeList.stream();
		
		double averageNumberOfDependents = employees3
			.collect(Collectors.averagingInt(emp5 -> emp5.getDependentList().size()));
		
		System.out.println(averageNumberOfDependents);

		
	}
}
