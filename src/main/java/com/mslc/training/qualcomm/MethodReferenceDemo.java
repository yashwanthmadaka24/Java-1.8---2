package com.mslc.training.qualcomm;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.infomover.training.java8.model.Employee;
import com.infomover.training.java8.model.HealthData;

public class MethodReferenceDemo {
	
	public static void main(String[] args) {
		
		
		
		Employee emp = new Employee("asdad");
//		x-> emp::getName;
		
		
		
			List<Employee> emps = HealthData.employeeList;
			// @formatter:off
		 Set<String> allNames =	emps
			 	.stream()
			 	.map(Employee::getName)
			 	.collect(Collectors.toCollection(TreeSet::new));
		 
		 
		 
			
			 
			// @formatter:on

			
			
		
	}

}
