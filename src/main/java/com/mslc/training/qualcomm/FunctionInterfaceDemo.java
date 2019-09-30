package com.mslc.training.qualcomm;

import java.util.function.Function;

public class FunctionInterfaceDemo {

	public static void doSomething(TaxCalculator t) {

//		int value  = t.calculateTax(5);
//		System.out.println(value);
//		

	}

	public static void main(String[] args) {
		
		
		Function<String, Integer> f1 = x -> x.length() ;
		int i = f1.apply("Shakir");
		System.out.println(i);
		

		TaxCalculator t = (x) -> x.length() + 10;

//			System.out.println("in calculate tax");
//			return 5;
//		

//				
//				new TaxCalculator() {
//			
//			@Override
//			public int calculateTax() {
//				
//				return 0;
//				
//			}
//
////			@Override
////			public int calculateTax(int percentage) {
////				// TODO Auto-generated method stub
////				return 0;
////			}
//		};

	}

}

@FunctionalInterface
interface TaxCalculator {

	int calculateTax(String x);

	// int calculateTax(int percentage);

}
