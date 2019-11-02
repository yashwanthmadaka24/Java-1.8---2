package com.mslc.training.java8.part1;

import java.util.List;
import java.util.stream.Collectors;

import com.mslc.training.java8.model.Employee;
import com.mslc.training.java8.model.HealthData;
import com.mslc.training.java8.model.HealthPlanGenericImpl;

public class Ch2App5RefactoringLegacyCodeDemo {

	
	public static void main(String[] args) {
		
		
		
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
		
		List<Employee> allEmployeesList = HealthData.employeeList;

		
//		List<Employee> allWithComprePlans = 
				  allEmployeesList
				   .stream()
				   .filter(x -> 
					    x.getHealthPlans()
					    .filter(y -> y.getName().contains("Compre"))
					    .count() > 0
				   ).collect(Collectors.toList()).forEach(System.out::println);
		
		
				  System.out.println(" **** ");
				  allEmployeesList
				   .stream()
				   .filter(x -> 
					    x.getHealthPlans()
					    .anyMatch(y -> y.getName().contains("Compre"))
				   ).collect(Collectors.toList()).forEach(System.out::println);
		

		// @formatter:on
		
			
	}
}
