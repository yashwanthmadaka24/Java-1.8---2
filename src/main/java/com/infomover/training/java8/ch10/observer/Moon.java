package com.infomover.training.java8.ch10.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Ths subject
 * 
 * @author MuhammedShakir
 *
 */
public class Moon {

	private final List<LandingObserver> observers = new ArrayList<>();

	public void land(String name) {
		for (LandingObserver observer : observers) {
			observer.observeLanding(name);
		}
	}

	public void startSpying(LandingObserver observer) {
		observers.add(observer);
	}
	// END Moon

	

}
