package com.mslc.training.java8.ch10.command;


/**
 * The receiver
 * 
 * 
 * @author MuhammedShakir
 *
 */
public class SimpleEditor implements Editor {

	@Override
	public void save() {
		
		System.out.println("Saving the editor");
		
	}

	@Override
	public void open() {
		
		System.out.println("Opening the editor");
		
	}

	@Override
	public void close() {
		System.out.println("Closing the editor");
		
	}

}
