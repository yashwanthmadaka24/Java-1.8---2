package com.infomover.training.java8.part1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ch4App4Strings {

	
	public static void main(String[] args) {
		
		
		List<String> names = Arrays.asList("JPMC", "Nomura", "Goldman Sacsh", "Morgan Stanley");
		String allNames = names.stream()
		   .collect(Collectors.joining(",", "[", "]"));
		
		System.out.println(allNames);
		
		
		
		
		
		
		
		
		
		
	}
}
