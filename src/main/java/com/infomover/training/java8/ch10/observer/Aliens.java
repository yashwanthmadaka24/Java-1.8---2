package com.infomover.training.java8.ch10.observer;

/**
 * 
 * Observer 1
 * 
 * @author MuhammedShakir
 *
 */
public class Aliens implements LandingObserver {

    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("They're distracted, lets invade earth!");
        }
    }

}
