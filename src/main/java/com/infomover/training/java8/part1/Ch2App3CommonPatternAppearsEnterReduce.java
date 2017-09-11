package com.infomover.training.java8.part1;

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

	}

}
