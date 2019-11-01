package com.mslc.training.java8.ch10.observer;


/**
 * Observer 2
 * 
 * 
 * @author MuhammedShakir
 *
 */
public class Nasa implements LandingObserver {
    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("We made it!");
        }
    }
}
