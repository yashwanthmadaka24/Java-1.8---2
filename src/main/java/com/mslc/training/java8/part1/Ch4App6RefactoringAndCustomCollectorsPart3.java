package com.mslc.training.java8.part1;

import java.util.List;

import com.mslc.training.java8.ch4.StringCombiner;
import com.mslc.training.java8.model.Employee;
import com.mslc.training.java8.model.HealthData;

public class Ch4App6RefactoringAndCustomCollectorsPart3 {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<Employee> emps = HealthData.employeeList;
		

		StringCombiner combined = emps
									.stream()
									.map(Employee::getName)
									.reduce(new StringCombiner(", ", "[", "]"),
												StringCombiner::add, 
												StringCombiner::merge);
		String result = combined.toString();
		

		System.out.println(result);
	}
}
