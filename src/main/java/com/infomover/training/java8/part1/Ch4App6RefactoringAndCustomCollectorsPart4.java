package com.infomover.training.java8.part1;

import java.util.List;

import com.infomover.training.java8.ch4.MyStringCollector;
import com.infomover.training.java8.model.Employee;
import com.infomover.training.java8.model.HealthData;

public class Ch4App6RefactoringAndCustomCollectorsPart4 {

	
	/**
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<Employee> emps = HealthData.employeeList;

		String result = emps
						.parallelStream()
						.map(Employee::getName)
						.collect(new MyStringCollector(",","[", "]"));

		System.out.println(result);
	}
}
