package com.mslc.training.java8.part1;

import com.mslc.training.java8.model.HealthPlan;
import com.mslc.training.java8.model.HealthPlanComprehensiveImpl;
import com.mslc.training.java8.model.HealthPlanGenericImpl;
import com.mslc.training.java8.model.factory.HealthPlanFactoryJava8;
import com.mslc.training.java8.model.factory.HealthPlanFactoryLegacy;

public class Ch4App0FactoryPattern {

	public static void main(String[] args) {
		
		HealthPlanFactoryLegacy factory = new HealthPlanFactoryLegacy();
		HealthPlan p1 = factory.getHealthPlan("dental", "IL", "compre");
		HealthPlan p2 = factory.getHealthPlan("general-health", "IL", "generic");
		
		p1.applyBenefits();
		p2.applyBenefits();
		
		HealthPlanFactoryJava8 factory2 = new HealthPlanFactoryJava8();
		
		factory2.getHealthPlan("dental", "IL", HealthPlanComprehensiveImpl::new);
		factory2.getHealthPlan("general-health", "IL", HealthPlanGenericImpl::new);
		
		
		

	}

}