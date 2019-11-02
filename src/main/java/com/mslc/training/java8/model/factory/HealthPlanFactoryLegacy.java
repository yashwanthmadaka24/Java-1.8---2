package com.mslc.training.java8.model.factory;

import com.mslc.training.java8.model.HealthPlan;
import com.mslc.training.java8.model.HealthPlanComprehensiveImpl;
import com.mslc.training.java8.model.HealthPlanGenericImpl;
import com.mslc.training.java8.model.HealthPlanNonComprehensiveImpl;

public class HealthPlanFactoryLegacy {
	
	public HealthPlan getHealthPlan(String name, String state, String type) {
		
		switch (type) {
		case "generic":
			
			return new HealthPlanGenericImpl(name, state);
		case "compre":
			return new HealthPlanComprehensiveImpl(name, state);

		case "non-compre":
			return new HealthPlanNonComprehensiveImpl(name, state);
		default:
			throw new RuntimeException("Invalid plan type");
		}
	}

}
