package com.mslc.training.java8.part1;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mslc.training.java8.model.HealthData;
import com.mslc.training.java8.model.HealthPlanGenericImpl;

public class Ch2App4PuttingOperationsToGether {
	
	public static void main(String[] args) {
		
		
				// We want to get all the states that offer comprehensive health plans
		
		 
				// We use filter to trim down health plans that are comprehensive
				// We use map to turn the health plan into state
				// We use list to collect the values
				
				
				// of a given employee
				
				Set<String> states = HealthData.johnColtrane.getHealthPlans()
						.filter(x -> x.getName().startsWith("Compre"))
						.map(x -> x.getState())
						.collect(Collectors.toSet());
				
				List<HealthPlanGenericImpl> healthPlans = HealthData.getThreeHealthPlans();
				
				
				 states =   healthPlans.stream()
							.filter(x -> x.getName().startsWith("Compre"))
							.map(x -> x.getState())
							.collect(Collectors.toSet());
				 
				  
				System.out.println(states);
				
				
				
	}

}
