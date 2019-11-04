package com.mslc.training.java8.part1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mslc.training.java8.model.Dependent;
import com.mslc.training.java8.model.Employee;
import com.mslc.training.java8.model.HealthData;

public class Ch2App5RefactoringLegacyCodeStepByStepTowardsJava8 {

	
	public static void main(String[] args) {

		// Find all the dependents in the system where the age is greater than
		// 15 and put their names (just the name) into a Set. In other words
		// we want names of all the dependents who are of age greater than 15
		// Note that dependents are associated with employees


		List<Employee> allEmployeesList = HealthData.employeeList; 
				
		/** Code Snippet 1 **/
		
		Set<String> greaterThan15 = new HashSet<>();
		// stream through 
		for (Employee  emp : allEmployeesList) {
			
			List<Dependent> dependents = emp.getDependentList();
			for (Dependent d : dependents) {
				
				// check the age of dependent and add name of the dependent to the greaterThan15 set
			}
		}
		System.out.println(greaterThan15);
		
		
		
		/** Code Snippet 2 **/
		// Streamy but still quite verbose
		greaterThan15.clear();
		allEmployeesList.stream()
		  .forEach(x -> {
			  
			 x.getDependentList().stream()
			    .forEach(dependent -> {
			    	  

			    	
			    		// check the age of dependent and add name of the dependent to the greaterThan15 set
			    	
			    	
			    		// dont you think this will be good candidate for filter ?
			    	
			    		
			    	
			    });
		  });
		System.out.println(greaterThan15);
		
	
		
		/** Code Snippet 3 **/
		// Following code to make use of filter and map functions of stream
		greaterThan15.clear();
		allEmployeesList.stream()
		
			// dont you think this is a flatMap candidate (flattening of all dependents)
		
			.forEach(x -> {
				
//				  x.getDependents()
//				  	<<  use a filter to filter out dependents whoes age is >= 15  >>
//				  	<< use  map to convert dependent to name (String) >>
//				  	// never add values from stream to shared data structure. Local variable is also NOT OK
//				  	.forEach(name -> greaterThan15.add(name));
		});
		
		
		System.out.println(greaterThan15);
		
		/** Code Snippet 4 **/
		/** Implement one set of operations on the stream to get the desired results **/
		
//		Set<String> dependentNames = allEmployeesList.stream()
//			<< Use a flatMap to only convert employee to dependents. Note that this will return a stream of dependents >>
//			<< Use a filter to filter dependents that are >= 15 yrs of age >>
//			<< Use a map to convert dependents to name (String)  >>
//		    << Use collect and then a Collector to get a Set  >>

//		System.out.println("Finally : " + dependentNames);
		
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
		// Solution in the corejava8 project
		
		
		
		
		
		
		
		
	
		
		
	}
}
