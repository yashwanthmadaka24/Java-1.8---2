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
		
		
		
		System.out.println("1.7 way : " + result);
		
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

		System.out.println("Streamy, yet not perfect way: " + result);
		 
		/** The operation that most closely matches what we’re doing 
		 * in terms of building up a String is the reduce operation.
		 *  
		 *   Refactoring to use reduce results in the following code
		 * 
		**/
		
		String[] listOfStrings = {"string-1", "string-2", "string-3", "string-4", "string-5", "string-6", "string-7"};
		Stream<String> ss1 = Stream.of(listOfStrings);
		/**
		 * There are 3 overloaded reduce functions
		 * a) Optional<T> reduce(BinaryOperator<T> accumulator); you need to provide the accumulator 
		 *      It returns an optional as there is not identity provided
		 * b) T reduce(T identity, BinaryOperator<T> accumulator); you need to provide identity and the accumulator
		 *      It returns input type (T) as you do provide identity
		 * c) <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
		 *        this has identity, accumulator and a combiner. more on this later..
		 * 
		 */
		Optional<String> reducedString = 
				 ss1
				  .reduce( (accumulator, element) -> accumulator + " , " +  element   );
		
		System.out.println("using 1 argument reduction : " + reducedString);
		
		Stream<String> ss2 = Stream.of(listOfStrings);
		
		System.out.println(" ***************** ");

		/** as the identity is provided of a particular type, the result is not an Optional but the type of identity **/
		String reducedString2 = ss2.reduce(new String("accumulator to start with"), 
				
				                        (accumulator, element) -> accumulator + " , " + element );
		
		System.out.println("using 2 arguments - identity & accumulator : " +  reducedString2);
		Stream<String> ss3 = Stream.of(listOfStrings);

		String reducedString3 = ss3.reduce(new String("1st Element"), 
				
				 (accumulator, element) -> accumulator + " ~ " + element,
				 
				 (left, right) -> {

					 return left + " - " + right;
					 
				 });
		System.out.println("*****");
		
		System.out.println(reducedString3);
		
		// @formatter:off

//		StringBuilder reduced5 = 
//					  ss3
//					  .map(x -> x.toString())
//					  .reduce(new StringBuilder(), (b, c) -> {return b;}, (left, right) -> {});
		
		StringBuilder i = new StringBuilder();
		StringBuilder reduced5 = 
				  HealthData.employeeList.stream()
				  .map(Employee::getName)
				  .reduce(new StringBuilder(), (stringB, element) -> {
					  
					    if (stringB.length() > 0) {
					    	stringB.append(",");
					    }
					    stringB.append(element);
					  	return stringB;
					 },
						  
				    ( left,  right) -> {
				    	//return right;
					  return left.append(right);
				  });
				 
		// @formatter:on

				
		System.out.println("using 3 arguments : " + reduced5);

	    
		
		

	}
}
