package com.mslc.training.java8.model;

public class HealthBenefit {

	private String name;
	private String details;

	public HealthBenefit() {
		// TODO Auto-generated constructor stub
	}

	public HealthBenefit(String name, String details) {

		this.name = name;
		this.details = details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public HealthBenefit copy() {

		return new HealthBenefit(name, details);
	}

}
