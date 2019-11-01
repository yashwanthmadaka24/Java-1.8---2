package com.mslc.training.java8.part2;

import com.mslc.training.java8.ch10.observer.Aliens;
import com.mslc.training.java8.ch10.observer.Moon;
import com.mslc.training.java8.ch10.observer.Nasa;

public class Ch10App2ObserverPattern {

	public static void main(String[] args) {
		classBasedExample();
		lambdaBasedExample();
	}

	private static void classBasedExample() {
		Moon moon = new Moon();
		
		moon.startSpying(new Nasa());
		moon.startSpying(new Aliens());

		moon.land("An asteroid");
		moon.land("Apollo 11");
	}

	private static void lambdaBasedExample() {
		Moon moon = new Moon();

		moon.startSpying(name -> {
			if (name.contains("Apollo"))
				System.out.println("We made it!");
		});

		moon.startSpying(name -> {
			if (name.contains("Apollo"))
				System.out.println("They're distracted, lets invade earth!");
		});

		moon.land("An asteroid");
		moon.land("Apollo 11");
	}

}
