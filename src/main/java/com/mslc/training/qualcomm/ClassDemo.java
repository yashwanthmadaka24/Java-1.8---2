package com.mslc.training.qualcomm;

public class ClassDemo {

	public static void main(String[] args) throws ClassNotFoundException {

		
		System.out.println(ClassDemo.class.getClassLoader().getParent().getParent());
		
		

	}

}
