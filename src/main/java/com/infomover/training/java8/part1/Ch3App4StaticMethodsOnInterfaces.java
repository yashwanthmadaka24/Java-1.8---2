package com.infomover.training.java8.part1;

import java.util.Collections;
import java.util.List;

public class Ch3App4StaticMethodsOnInterfaces {

	/**
	 * 
	 * <p>
	 * We’ve seen a lot of calling of Stream.of but haven’t gotten into its
	 * details yet. You may recall that Stream is an interface, but this is a
	 * static method on an interface. This is another new language change that
	 * has made its way into Java 8, primarily in order to help library
	 * developers, but with benefits for day-to-day application developers as
	 * well.
	 * </p>
	 * <p>
	 * An idiom that has accidentally developed over time is ending up with
	 * classes full of static methods. Sometimes a class can be an appropriate
	 * location for utility code, such as the Objects class introduced in Java 7
	 * that contained functionality that wasn’t specific to any particular
	 * class.
	 * </p>
	 * <p>
	 * Of course, when there’s a good semantic reason for a method to relate to
	 * a concept, it should always be put in the same class or interface rather
	 * than hidden in a utility class to the side. This helps structure your
	 * code in a way that’s easier for someone reading it to find the relevant
	 * method.
	 * </p>
	 * <p>
	 * For example, if you want to create a simple Stream of values, you would
	 * expect the method to be located on Stream. Previously, this was
	 * impossible, and the addition of a very interface-heavy API in terms of
	 * Stream finally motivated the addition of static methods on interfaces.
	 * </p>
	 * 
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		HealthInsurance.availablePolicies();
		
	}
}


interface HealthInsurance {
	
	public void calcualteInsurance() ;
	
	public static List<String> availablePolicies() {
		// some implementation
		return Collections.emptyList();
	}
	
	
}

