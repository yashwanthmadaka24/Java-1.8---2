package com.mslc.training.java8.part1;

import java.util.List;
import java.util.stream.Collectors;

import com.mslc.training.java8.ch4.StringCombiner;
import com.mslc.training.java8.model.Employee;
import com.mslc.training.java8.model.HealthData;

public class Ch4App7ReductionAsCollector {

	/**
	 * As you’ve just seen, custom collectors aren’t that hard to write, but if
	 * you’re thinking about writing one in order to collect into a domain class
	 * it is worth examining the alternatives. The most obvious is to build one
	 * or more collection objects and then pass them into the constructor of
	 * your domain class. This is really simple and suitable if your domain
	 * class is a composite containing different collections. Of course, if your
	 * domain class isn’t just a composite and needs to perform some calculation
	 * based on the existing data, then that isn’t a suitable route. Even in
	 * this situation, though, you don’t necessarily need to build up a custom
	 * collector. You can use the reducing collector, which gives us a generic
	 * implementation of the reduction operation over streams. See the example
	 * in the main function of this class shows how we might write our
	 * String-processing example using the reducing collector.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<Employee> emps = HealthData.employeeList;

		// but will not work with parallelStream
		
		String result = emps
						.stream()
						.map(Employee::getName)
						.collect(Collectors.reducing(new StringCombiner(", ", "[", "]"),
													 name -> new StringCombiner(", ", "[", "]").add(name), 
													 StringCombiner::merge))
						.toString();

		System.out.println(result);
	}
}
