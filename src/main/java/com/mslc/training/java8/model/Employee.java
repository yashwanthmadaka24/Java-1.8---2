/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mslc.training.java8.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Muhammed Shakir
 */
public final class Employee {

	private String name;
	private String lastName;
	private List<HealthPlan> healthPlans;
	private List<Dependent> dependents;
	private int age;

	public Employee(String name, Integer age) {

		this.name = name;
		this.age = age;
	}

	public Employee(String name) {

		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Employee(String name, List<HealthPlan> healthPlans, List<Dependent> dependents, int age) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(healthPlans);
		Objects.requireNonNull(dependents);

		this.name = name;
		this.age = age;
		this.lastName = name + " Mc-Clain";
		this.healthPlans = new ArrayList<>(healthPlans);
		this.dependents = new ArrayList<>(dependents);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the healthPlans
	 */
	public Stream<HealthPlan> getHealthPlans() {
		return healthPlans.stream();
	}

	/**
	 * Used in imperative code examples that need to iterate over a list
	 */
	public List<HealthPlan> getHealthPlanList() {

		return unmodifiableList(healthPlans);

	}

	public boolean hasDependents() {

		if (this.getDependentList().size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @return the dependents
	 */
	public Stream<Dependent> getDependents() {

		return dependents.stream();
	}

	/**
	 * Used in imperative code examples that need to iterate over a list
	 */
	public List<Dependent> getDependentList() {
		
		
		return unmodifiableList(dependents);
	}

	public HealthPlan getPrimaryHealthPlan() {
		return healthPlans.get(0);
	}

	@Override
	public String toString() {

		return this.name + " -- " + this.age;

	}

	public Employee copy() {

		List<HealthPlan> healthPlans = getHealthPlans().map(HealthPlan::copy).collect(toList());

		List<Dependent> dependents = getDependents().map(Dependent::copy).collect(toList());

		return new Employee(name, healthPlans, dependents, age);
	}

}
