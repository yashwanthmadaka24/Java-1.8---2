package com.infomover.training.java8.part1;

public class Ch1App0CustomFunctionalInterface {
	
	public static void main(String[] args) {
		
		AdderTo500 adder = new AdderTo500() {
			public int add(int value) {
				return value + 10;
			}
		};//
		
		int value = execute(adder);
		System.out.println(value);
		value = execute(x -> x + 100);
		System.out.println(value);
	}

	// can pass instance of anonymous class or a lambda
	
	static int execute(AdderTo500 adder) {
		
		return adder.add(500);
	}
}

interface AdderTo500 {
	public int add(int value);
}
