package com.infomover.training.java8.part1;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ch4App5ComposingCollectors1 {

	
	public static void main(String[] args) {

		
		
	Set<Integer> set = 
			Stream.of(100, 2, 3, 4, 5, 6, 7, 8, 9)
				.filter(x -> {
					System.out.println("filtering : " + x);
					return x <= 100;
				})
				.map(x -> {
					System.out.println("   *** mapping");
					return x + 10;
				})
				.collect(Collectors.toCollection(TreeSet::new));
	
	System.out.println("Set : " + set);
		
		
		
		
		
		
	}
}
