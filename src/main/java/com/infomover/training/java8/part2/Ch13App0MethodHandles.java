package com.infomover.training.java8.part2;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Ch13App0MethodHandles {

	public static void main(String[] args) throws Throwable {

		MethodHandles.Lookup lookup = MethodHandles.lookup();

		MethodType methodType = MethodType.methodType(int.class, new Class<?>[] { String.class });

		MethodHandle methodHandle = lookup.findStatic(Counter.class, "count", methodType);

		// int count = (int) methodHandle.invokeExact("Muhammed Shakir");
		//
		// System.out.println("Count : " + count);

		// Following illustrates the difference between invokeExact and invoke
		// which is, invokeExact will not do auto-type conversion where as
		// invoke will do

		/*
		 * All the following will throw a runtime error int count1 = (int)
		 * methodHandle.invokeExact((Object) "foo"); int count2 = (Integer)
		 * methodHandle.invokeExact("foo"); methodHandle.invokeExact("foo");
		 */

		/*
		 * All the following will work (invoke is used instead of invokeExact)
		 * int count1 = (int) methodHandle.invoke((Object) "foo");
		 * methodHandle.invoke("foo");
		 */
		// int count1 = (int) methodHandle.invoke((Object) "foo");
		// int count3 = (int) methodHandle.invoke("New Value");
		//
		// System.out.println("Count : " + count1 + " -- " + count3);

	}

}

class Counter {

	static int count(String name) {

		return name.length();

	}

	private static int count2(String name) {

		return name.length() + 5;

	}

}
