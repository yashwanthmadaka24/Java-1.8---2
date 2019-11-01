package com.mslc.training.java8.part1;

import java.util.List;
import java.util.Optional;

import com.mslc.training.java8.model.Employee;
import com.mslc.training.java8.model.HealthData;

public class Ch2App2StreamOperationsFindAndFlatMap {

	public static void main(String[] args) {

		
		List<Employee> allEmployeesList = HealthData.employeeList;

		// @formatter:off

			boolean b = allEmployeesList
				.stream()
				.anyMatch(x -> x.getAge() < 10);
			
				
			System.out.println(b);
			
			boolean allEmpsAreAgedMoreThan10 = allEmployeesList
			.stream()
			.allMatch(x -> x.getAge() > 10);
			
			System.out.println(allEmpsAreAgedMoreThan10);
			
			Optional<Employee> e = allEmployeesList
				.stream()
				.filter(x -> x.getAge() > 10)
				.findFirst();
				
			System.out.println(e.get().getName());
		 
			// For flatMap checkout :Ch2App5RefactoringLegacyCode.java
		// @formatter:on
	}

}
