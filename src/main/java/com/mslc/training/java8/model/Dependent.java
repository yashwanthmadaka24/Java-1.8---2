/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mslc.training.java8.model;

/**
 * @author Muhammed Shakir
 */
public final class Dependent {

	private final String name;
	private final int age;

	public Dependent(String name, int age) {
		this.name = name;
		this.age = age;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the age in years
	 */
	public int getAge() {
		return age;
	}

	@Override
	public String toString() {

		return "Name : " + this.name + " Age : " + this.age;
	}

	public Dependent copy() {
		return new Dependent(name, age);
	}

}
