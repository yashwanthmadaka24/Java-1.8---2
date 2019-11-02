package com.mslc.training.java8.part1;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mslc.training.java8.model.Employee;
import com.mslc.training.java8.model.HealthData;
import com.mslc.training.java8.model.HealthPlanGenericImpl;

public class Ch4App3Grouping {

	public static void main(String[] args) {
		
		
		/**
		 * Get employees grouped by their Primary HealthPlan
		 * 
		 */
		
		Stream<Employee> emps = HealthData.employees;
		
		Map<HealthPlanGenericImpl, List<Employee>> groupedEmps = 
				emps
				  .collect(groupingBy(emp -> emp.getPrimaryHealthPlan()));
		
		
		
		
		groupedEmps.forEach((key, value) -> System.out.println(key + " -- " + value));
		
		Stream<Employee> emps2 = HealthData.employeeList.stream();

		Map<Integer, List<Employee>> ageWiseEmps =  emps2
		 .collect(groupingBy(e -> e.getAge()));
		
		ageWiseEmps.forEach((key, value) -> System.out.println(key + " -- " + value));
		
	
		Map<Integer, List<Integer>> allInts = 
				Stream.of(35, 45, 35, 35, 35, 45, 48)
					.collect(groupingBy(x -> x));
		
		System.out.println(" **** ");
		allInts.forEach((key, value) -> System.out.println(key + " -- " + value));
		
		System.out.println(" **** ");
		
		Map<String, List<Integer>> allInts2 = 
				Stream.of(35, 45, 35, 35, 35, 45, 48)
					.collect(groupingBy(x -> (x >=30 && x <= 40 ? "30-40" : "40-50")));
		
		allInts2.forEach((key, value) -> System.out.println(key + " -- " + value));
		
		
		// Exercise for you : Do the ageWiseEmps grouping based on age groups.
		
	}
}
