package com.mslc.training.java8.part2;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Ch13App1MethodHandlesTransformationOfArguments {

	public static void main(String[] args) throws Throwable {

		MethodHandles.Lookup lookup = MethodHandles.lookup();

		MethodHandle mh = lookup.findStatic(Math.class, "pow",
				MethodType.methodType(double.class, double.class, double.class));

		int arg1 = 2, arg2 = 10;
		mh = MethodHandles.insertArguments(mh, 1, arg2);
		// Uncomment the following and do not pass any arguments to invoke
		// mh = MethodHandles.insertArguments(mh, 0, arg1);

		System.out.printf(arg1 + "^" + arg2 + " = %d%n", (int) (double) mh.invoke(2.0));

		/**
		 * Notice the double cast -- (int) (double). invoke()'s return value
		 * must be cast to double type to be compatible with the previously
		 * specified method type when the method handle was obtained (via the
		 * findStatic() method call). Because System.out.printf() requires an
		 * int value (because of %d), the double value must be cast to int.
		 **/

	}

}
