package com.infomover.training.java8.part1;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.infomover.training.java8.model.Employee;
import com.infomover.training.java8.model.HealthData;

public class Ch4App6RefactoringAndCustomCollectorsPart1 {

	public static void main(String[] args) {

		/** concatenating names of all employees **/
		
		// Java 1.7 way of 
		
		List<Employee> emps = HealthData.employeeList;
		
		
		
		StringBuilder builder = new StringBuilder("[");
		
		for (Employee emp : emps) {

			if (builder.length() > 1)
				builder.append(", ");
			
			String name = emp.getName();
			builder.append(name);
		}
		
		builder.append("]");
		
		String result = builder.toString();
		
		
		
		System.out.println(result);
		
		// Refactoring the code to use Streams & Map
		
		StringBuilder builder2 = new StringBuilder("["); 
		
		emps.stream()
		  .map( Employee:: getName) 
		  .forEach( name -> {
			  if (builder2.length() > 1)
				  builder2.append(", ");
			  builder2.append( name); 
		   });
		
		builder2.append("]"); 
		result = builder2.toString();

		System.out.println(result);
		 
		/** The operation that most closely matches what we’re doing 
		 * in terms of building up a String is the reduce operation.
		 *  
		 *   Refactoring to use reduce results in the following code
		 * 
		**/
		
		Stream<String> ss = Stream.of("string-1", "string-2", "string-3", "string-4", "string-5", "string-6", "string-7");
		
		
		Optional<String> reducedString = 
				 ss.reduce( (accumulator, element) -> accumulator + " , " +  element   );
		
		
		
		/** as the identity is provided of a particular type, the result is not an Optional but the type of identity **/
		String reducedString2 = ss.reduce(new String("accumulator to start with"), 
				
				                        (accumulator, element) -> accumulator + " , " + element );
		
//		System.out.println(reducedString);
		
		String reducedString3 = ss.reduce(new String("1st Element"), 
				
				 (accumulator, element) -> accumulator + " ~ " + element,
				 
				 (left, right) -> {

					 return left + " - " + right;
					 
				 });
		
		System.out.println("reduced string : " + reducedString3);

	    
		
		

	}
}
