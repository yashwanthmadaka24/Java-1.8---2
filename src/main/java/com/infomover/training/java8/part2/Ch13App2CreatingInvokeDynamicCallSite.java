package com.infomover.training.java8.part2;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

public class Ch13App2CreatingInvokeDynamicCallSite {

	public static void main(String[] args) throws Throwable {

		// We are creating an "invokedynamic" callsite using the bootstrap method
		
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		CallSite callSite = Bootstrapper.bootstrap(lookup, "fictitious method name");
		System.out.println("The target method is : " + callSite.getTarget());
		MethodHandle m = callSite.dynamicInvoker();
		int c = (int) m.invokeExact("Nomura");
		
		System.out.println(c);

	}
}

class Bootstrapper {

	
	public static CallSite bootstrap(Lookup lookup, String name) throws Throwable {

		MethodType methodType = MethodType.methodType(int.class, new Class<?>[] { String.class });
		//MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle methodHandle = lookup.findStatic(Counter5.class, "count", methodType);
		return new ConstantCallSite(methodHandle);

	}
}

class Counter5 {

	static int count(String name) {

		System.out.println(" *** count method is executed *** ");
		return name.length();

	}

	private static int count2(String name) {

		return name.length() + 5;

	}

}
