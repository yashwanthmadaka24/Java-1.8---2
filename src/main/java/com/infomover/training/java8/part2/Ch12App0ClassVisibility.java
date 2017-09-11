package com.infomover.training.java8.part2;

import java.net.URL;
import java.net.URLClassLoader;

public class Ch12App0ClassVisibility {

	public static void main(String[] args) {

		try {
			
			
			URLClassLoader mycl = new URLClassLoader(new URL[] { new URL("file:/Users/MuhammedShakir/classloading/") });
			Class c2 = mycl.loadClass("ClassA");
			System.out.println("Loaded...");
			
			System.out.println(c2.getClassLoader().getClass().getName());
			System.out.println( c2.getSuperclass().getName() +   " is also loaded by the same classloader : " + c2.getSuperclass().getClassLoader().getClass().getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
