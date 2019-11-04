package com.mslc.training.java8.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.mslc.training.java8.model.Employee;
import com.mslc.training.java8.model.HealthData;

public class Ch1App1OOTBFunctionalInterfaces2 {

	public static void main(String[] args) {
		
		
	
			
			

		List<Employee> empList = HealthData.employeeList;
		List<String> newList = mutateEmployeeToStringJava8(empList, emp -> emp.getName());
		System.out.println(newList);

		List<String> newList2 = mutateEmployeeToComposeLogicJava8(empList, emp -> emp.getName(),
				s -> s.substring(0, s.indexOf(" ")));
		System.out.println(newList2);

		Function<Employee, String> mapper = emp -> emp.getName();
		Function<String, String> postMapper = x -> x.substring(0, x.indexOf(" "));

		List<String> newList3 = mutateEmployeeToComposeLogicJava8v2(empList, mapper.andThen(postMapper));
		System.out.println(newList3);
		
		List<String> newList4 = mutateEmployeeToComposeLogicJava8v2(empList, postMapper.compose(mapper));
		System.out.println(newList4);
		
	}

	public static List<String> mutateEmployeeToStringJava8(List<Employee> employeeList,
			Function<Employee, String> mapper) {

		List<String> empNameList = new ArrayList<String>();
		for (Employee emp : employeeList) {
			String result = mapper.apply(emp);
			empNameList.add(result);
		}
		return empNameList;
	}

	public static List<String> mutateEmployeeToComposeLogicJava8(List<Employee> employeeList,
			Function<Employee, String> mapper, Function<String, String> composer) {

		List<String> empNameList = new ArrayList<String>();
		for (Employee emp : employeeList) {
			// String result = mapper.andThen(composer).apply(emp);
			Function<Employee, String> f = mapper.andThen(composer);
			String result = f.apply(emp);
			empNameList.add(result);
		}
		return empNameList;
	}

	public static List<String> mutateEmployeeToComposeLogicJava8v2(List<Employee> employeeList,
			Function<Employee, String> mapper) {

		List<String> empNameList = new ArrayList<String>();
		for (Employee emp : employeeList) {
			// String result = mapper.andThen(composer).apply(emp);

			String result = mapper.apply(emp);
			empNameList.add(result);
		}
		return empNameList;
	}

}
