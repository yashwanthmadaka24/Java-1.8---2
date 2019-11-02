package com.mslc.training.java8.model.factory;

import java.util.function.Supplier;

import com.mslc.training.java8.model.HealthPlan;

public class HealthPlanFactoryJava8 {
	
	public HealthPlan getHealthPlan(String name, String state, Supplier<HealthPlan> supplier) {
		
		HealthPlan plan = supplier.get();
		
		/** Some code to initialize the plan as per business need**/
		
		return plan;
		
//		switch (type) {
//		case "generic":
//			
//			return new HealthPlanGenericImpl(name, state);
//		case "compre":
//			return new HealthPlanComprehensiveImpl(name, state);
//
//		case "non-compre":
//			return new HealthPlanNonComprehensiveImpl(name, state);
//		default:
////			throw new RuntimeException("Invalid plan type");
//		}
	}

}
