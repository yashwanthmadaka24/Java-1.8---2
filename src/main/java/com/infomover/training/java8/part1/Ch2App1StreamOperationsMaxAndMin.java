package com.infomover.training.java8.part1;

import java.util.Comparator;
import java.util.stream.Stream;

import com.infomover.training.java8.model.Employee;
import com.infomover.training.java8.model.HealthData;

public class Ch2App1StreamOperationsMaxAndMin {

	public static void main(String[] args) {
		
		
		// Find the employee with least dependents
		
		Stream<Employee> emps = HealthData.employees;

		Employee empWithLeastDependents = 
					emps
					  .min(Comparator.comparing(emp -> emp.getDependentList().size()))
					  .get();

		System.out.println(empWithLeastDependents + " - has " + empWithLeastDependents.getDependentList().size() + " dependents");
		
	}
}
