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
			sum = sum + i;
		}

		// Find the employee with least dependents
		
		Stream<Employee> emps = HealthData.employees;
		
		
		
		// @formatter:off
		Optional<Employee> optionalEmp = 
					emps
					  .min(Comparator.comparing(emp -> emp.getDependentList().size()));
		// @formatter:on
					  
		Employee empWithLeastDependents = optionalEmp.get();
		
		/** Following lines for additional demo only ***/
		
		System.out.println(empWithLeastDependents + " - has " + empWithLeastDependents.getDependentList().size() + " dependents");
		
		
		Comparator<Employee> c = Comparator.comparing(x -> x.getDependentList().size());
		
		
		Comparator<Employee> c2 = Comparator.comparing(x -> x.getDependentList(), (l1, l2) -> {
			
			return l1.size() - l2.size();
		});
		
		
		Comparator<Employee> c3 = Comparator.comparing(Employee::getDependentList, (l1, l2) -> {
			System.out.println(l1.size() + " -- " + l2.size()  + " -- " );
			return l1.size() - l2.size();
		});
		
		
		// @formatter:off
			Optional<Employee> optionalEmp2 = 
						HealthData.employeeList
						.stream()
						  .min(c2);
			// @formatter:on
						  
			Employee empWithLeastDependents2 = optionalEmp2.get();
			System.out.println(empWithLeastDependents2 + " - has " + empWithLeastDependents2.getDependentList().size() + " dependents");
			
		
	}
}
