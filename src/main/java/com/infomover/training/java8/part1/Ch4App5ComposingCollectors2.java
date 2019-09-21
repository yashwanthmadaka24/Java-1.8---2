package com.infomover.training.java8.part1;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.infomover.training.java8.ch4.MyCollectors;
import com.infomover.training.java8.ch4.MyTreeSet;
import com.infomover.training.java8.model.Employee;
import com.infomover.training.java8.model.HealthData;
import com.infomover.training.java8.model.HealthPlan;

public class Ch4App5ComposingCollectors2 {

	
	public static void main(String[] args) {

		Stream<Employee> emps1 = HealthData.employeeList.stream();
		
		// Count the number of employees by primary health plan instead 
		// of getting Map<HealthPlan, List<Employee>>
		// counting is actually a collector - Collectors.counting();
		
		/**
		 * This form of groupingBy divides elements into buckets. 
		 * Each bucket gets associated with the key provided by the
		 * classifier function: getPrimaryHealthPlan. 
		 * 
		 * The groupingBy operation then uses the downstream 
		 * collector to collect each bucket and makes a map of the results.
		 * 
		 */
		
		
		Map<HealthPlan, Long> healthPlanEmps0 = 
				HealthData.employeeList.stream()
				  .collect(groupingBy(emp -> emp.getPrimaryHealthPlan(), Collectors.counting()));

		healthPlanEmps0.forEach((key, value) -> System.out.println(key + " -- " + value));
		
		
		// Using method reference
		
		Map<HealthPlan, Long> healthPlanEmps = 
				emps1
				  .collect(
						  groupingBy(Employee::getPrimaryHealthPlan, 
								  Collectors.counting()));
		
		healthPlanEmps.forEach((key, value) -> System.out.println(key + " -- " + value));
		
		
		/**
		 * Following is to get the Set of Last Names grouped by Health Plan
		 * 
		 * First take a look at the naive way of doing this will be as follows
		 * 
		 */
		
		
		/*
		 * The mapping collector allows you to perform a
		 *  map-like operation over your collector’s container. 
		 *  You also need to tell your mapping collector what
		 *  collection it needs to store the results in,
		 *  which you can do with the toList collector.
		 *  It’s turtles, I mean collectors, all the way down! 
		 *  Just like map, this takes an implementation of Function. 
		 *  If we refactor our code to use a second collector, it looks like 
		 *  what I have written below
		 * 
		 */
		Stream<Employee> emps4 = HealthData.employeeList.stream();
		
		Map<HealthPlan, List<Employee>> planWiseEmps = emps4
				.collect(groupingBy(Employee::getPrimaryHealthPlan));
		
		Map<HealthPlan, Set<String>> planWiseEmpNames = new HashMap<>();
		
		for (Map.Entry<HealthPlan, List<Employee>> entry : planWiseEmps.entrySet()) {
			
			planWiseEmpNames.put(entry.getKey(), entry.getValue()
													.stream()
													.map(emp -> emp.getLastName())
													.collect(Collectors.toSet()));
		}
		
		System.out.println(planWiseEmpNames);
		

		// Use the following to do it in a nicer and neater way
		
		Stream<Employee> emps5 = HealthData.employeeList.stream();
		
		Map<HealthPlan, Set<String>> groupedEmps2 = 
				emps5
				  .collect(Collectors.groupingBy(
						    // The key to groupBy
						  	Employee::getPrimaryHealthPlan,
						  	// cascaded operation with a mapper 
						  	Collectors.mapping(Employee::getLastName,
						  			// to convert the value to what ever type of Collection
						  			// this is a downstream collector - Collectors.toSet()
						  			Collectors.toSet())   ));
		
		groupedEmps2.forEach((key, value) -> System.out.println(key + " -- " + value));
		
		//Collectors.groupingBy(classifier, downstream)
		
	}
}
