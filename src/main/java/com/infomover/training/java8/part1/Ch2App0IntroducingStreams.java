package com.infomover.training.java8.part1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ch2App0IntroducingStreams {

	public static void main(String[] args) {

		List<String> names = Arrays.asList("Nomura", "BNP Pariba", "Goldman Sasch", "JP Morgan", "Morgan Stanley");

		Stream<String> streamOfNames = names.stream();

		List<String> morganCompanies = streamOfNames
										.parallel()
										.unordered()
										.filter(name -> {
											
											
											if (name.equals("JP Morgan"))  {
												System.out.println("*** start filtering: " + name + " " + Thread.currentThread().getName());
												
												try {
													Thread.sleep(3000);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}

												System.out.println("*** end filtering: " + name + " " + Thread.currentThread().getName());

												
												
											} else {
												
												System.out.println("filtering: " + name + " " + Thread.currentThread().getName());

											}
											
											return name.contains("Morgan");
										})
										.map(name -> {
											
											System.out.println("mapping: " + name + " " + Thread.currentThread().getName());

											return name.toUpperCase();
										})
										.collect(Collectors.toList());

		System.out.println(morganCompanies);

	}

}
