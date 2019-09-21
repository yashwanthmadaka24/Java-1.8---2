package com.infomover.training.java8.part1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.infomover.training.java8.model.Dependent;
import com.infomover.training.java8.model.Employee;
import com.infomover.training.java8.model.HealthData;

public class Ch2App5RefactoringLegacyCode {

	
	public static void main(String[] args) {
		
		
		
		// @formatter:off


		// Find all the dependents in the system where the age is greater than
		// 15 and put their names (just the name) into a Set. In other words
		// we want names of all the dependents who are of age greater than 15
		// Note that depdendents are associated with employees


		List<Employee> allEmployeesList = HealthData.employeeList; 
		
		// following is no-stream code to get all employees whose dependents are aged more than 15 
		Set<String> greaterThan15 = new HashSet<>();
		// stream through 
		for (Employee  emp : allEmployeesList) {
			
			List<Dependent> dependents = emp.getDependentList();
			for (Dependent d : dependents) {
				
				if (d.getAge()  > 15) {
					
					greaterThan15.add(d.getName());
				}
			}
		}
		System.out.println(greaterThan15);
		
		// Streamy but still quite verbose
		
		greaterThan15.clear();
		allEmployeesList.stream()
		  .forEach(x -> {
			  
			 x.getDependentList().stream()
			    .forEach(dependent -> {
			    	  
			    		// a good candidate for filter
			    	    if (dependent.getAge() > 15) {
			    	    	
			    	    		// a good candidate for map
			    	    		greaterThan15.add(dependent.getName());
			    	    }
			    });
		  });
		System.out.println(greaterThan15);

		// Following code to make use of filter and map functions of stream
		greaterThan15.clear();
		allEmployeesList.stream()
			// candidate for flatMap
			.forEach(x -> {
				
				  x.getDependents()
				  	.filter(d -> d.getAge() > 15)
				  	.map(d -> d.getName())
				  	.forEach(name -> greaterThan15.add(name));
		});
		
		
		System.out.println(greaterThan15);
		
		Set<String> dependentNames = allEmployeesList.stream()
			// following flatMap returns stream of dependents [flatMap always returns a stream and also takes a mapper that provides stream]
			.flatMap(employee -> employee.getDependents())
			.filter(x -> x.getAge() >= 15)
			.map(x -> x.getName())
			.collect(Collectors.toSet());

		Set<String> dependentNames2 = allEmployeesList.stream()
				.flatMap(employee -> {
				
					System.out.println("in flatMap of " + employee.getName() + " -- " + employee.getDependentList().size());
					return employee.getDependents();
				})
				.filter(x -> {
					
					System.out.println("in filter");
					return x.getAge() >= 15;
				})
				.map(x -> x.getName())
				.collect(Collectors.toSet());
		System.out.println("Finally : " + dependentNames);
		
		
 		// Hands-On : Get me the state of comprehensive health plans of all employees 
		
		List<Employee> allEmps = HealthData.employeeList;
		
		List<String> allStatesWithComprePlans = 
				 allEmps.stream()
				  .flatMap(emp -> emp.getHealthPlans())
				  .filter(x -> x.getName().startsWith("Compre"))
				  .map(x -> x.getState())
				  .distinct()
				  .collect(Collectors.toList());
		
		System.out.println(allStatesWithComprePlans);
		
		
		// Hands-On : Get me all employees with at-least one plan that is Comprehensive Health Plan
		
		/**
		 * List<Employee> empsWithComprePlans = new ArrayList();
		 * for (Employee emp : emps) {
		 * 
		 *    List<HealthPlan> empPlans = emp.getPlans();
		 *    for (Plan p : empPlans) {
		 *        if (p.getName().startsWith("Compre")) {
		 *            empsWithComprePlans.add(emp);
		 *            break;
		 *        }
		 *    }
		 * }
		 * 
		 */
		
		List<Employee> allWithComprePlans = 
				  allEmployeesList
				   .stream()
				   .filter(x -> {
					   return x.getHealthPlans()
					    .filter(y -> y.getName().contains("compre"))
					    .count() > 0;
				   }).collect(Collectors.toList());
		
				
		List<Employee> allEmpsWithComprePlans = 
					allEmps.stream()
						.filter(x -> 
									x.getHealthPlans()
										.filter(p -> p.getName().startsWith("Compre"))
										.count() > 0)
						.collect(Collectors.toList());
		
		
		List<Employee> allEmpsWithComprePlans2 = 
				allEmps.stream()
					.filter(x -> 
								x.getHealthPlans()
									.anyMatch(plan -> plan.getName().startsWith("Compre")))
					.collect(Collectors.toList());
		
		System.out.println(allEmpsWithComprePlans);
		 
		// @formatter:on
		
			
	}
}
