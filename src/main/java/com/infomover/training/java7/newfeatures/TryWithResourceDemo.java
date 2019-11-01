package com.infomover.training.java7.newfeatures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TryWithResourceDemo {

	/**
	 * A resource is an object that must be closed once your program is done using
	 * it. For example, a File resource or JDBC resource for a database connection
	 * or a Socket connection resource. Before Java 7, there was no auto resource
	 * management and we should explicitly close the resource once our work is done
	 * with it. Usually, it was done in the finally block of a try-catch statement.
	 * This approach used to cause memory leaks and performance hit when we forgot
	 * to close the resource.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		/**
		 * Very important : All such resources are ultimately end up implementing
		 * AutoClosable Interface somewhere in the hierarchy
		 * 
		 */
		File f = new File("Asdsad");
		FileReader r = new FileReader(f);

	}

}
