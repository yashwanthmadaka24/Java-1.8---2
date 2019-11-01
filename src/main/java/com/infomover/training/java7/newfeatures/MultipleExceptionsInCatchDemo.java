package com.infomover.training.java7.newfeatures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MultipleExceptionsInCatchDemo {

	public static void main(String[] args) {

		File f = new File("asdasd");
		try {
			FileReader r = new FileReader(f);
			Class c = Class.forName("SomeClass");

		} catch (FileNotFoundException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
