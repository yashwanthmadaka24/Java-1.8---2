package com.mslc.training.java8.model;

import java.util.List;
import java.util.Optional;

public class Employees {

	private List<Employee> employees;

	public Employees(List<Employee> emps) {

		this.employees = emps;
	}

//	public Employee getEmployee(int index) {
//		if (index < 0 || index >= employees.size()) {
//			throw new RuntimeException("index :" + index + " does not correspond to Employee");
//		}
//		return employees.get(index);
//	}
	

	
	public Optional<Employee> getEmployee(int index) {
		if (index < 0 || index >= employees.size()) {
			return Optional.empty();
		} else {
			return Optional.of(employees.get(index)); 
		}
		
	}

	
	
	
	
}
