package com.infomover.training.java8.part1;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import com.infomover.training.java8.model.Employee;
import com.infomover.training.java8.model.HealthData;

public class Ch2App3CommonPatternAppearsEnterReduce {

	public static void main(String[] args) {

		// Find the employee with least dependents
		
		List<Employee> emps = HealthData.employeeList;

		Employee emp = emps.get(0);
		for (Employee e : emps) {
			if (e.getDependentList().size() < emp.getDependentList().size()) {
				emp = e;
			}
		}
		System.out.println("emp with least dependents : " + emp + " -- " + emp.getDependentList().size());

		int sum = Stream.of(1, 2, 3)
					.reduce(0, (acc, element) -> acc + element);
		System.out.println(sum);

		
		/**
		 * Use emps.stream().reduce function in order to get employee with max dependents
		 * . Solution below (but first try on your own)
		 */
		
		// @formatter:off
		Employee emp2 = emps.get(0);
		Employee e = emps
			.stream()
			.reduce(emp2, (e1, e2) -> {
				if (e1.getDependentList().size() > e2.getDependentList().size()) {
					return e1;
				} else {
					return e2;
				}
			});
		 
		// @formatter:on
		
		System.out.println("emp with max dependents : " + e + " -- " + e.getDependentList().size());


	}

}
