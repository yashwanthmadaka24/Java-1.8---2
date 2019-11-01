package com.mslc.training.java8.ch4;

import java.util.TreeSet;

public class MyTreeSet extends TreeSet<Integer> {
	
	public MyTreeSet() {
	
		
		super();
		System.out.println("MyTreeSet Created");
	}
	
	@Override
	public boolean add(Integer e) {
		System.out.println("   --- adding object : " + e);
		
		return super.add(e);
	}
	

}
