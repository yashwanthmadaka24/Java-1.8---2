package com.infomover.training.java8.part1;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import com.infomover.training.java8.model.Employee;
import com.infomover.training.java8.model.HealthData;

public class Ch2App1StreamOperationsMaxAndMin {

	public static void main(String[] args) {
		
		
		int[] arr  = new int[] {1, 5, 6, 10};
		
		int sum = 0;
		for (int i : arr) {
			sum = sum + arr[i];
		}
		
		
		
		
		
		// Find the employee with least dependents
		
		Stream<Employee> emps = HealthData.employees;

		Optional<Employee> optionalEmp = 
					emps
					  .min(Comparator.comparing(emp -> emp.getDependentList().size()));
		
					  
		Employee empWithLeastDependents = optionalEmp.get();
		
		
		System.out.println(empWithLeastDependents + " - has " + empWithLeastDependents.getDependentList().size() + " dependents");
		
	}
}
