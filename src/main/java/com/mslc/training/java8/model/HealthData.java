package com.mslc.training.java8.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class HealthData {

	
	public static final HealthBenefit fullCover = new HealthBenefit("Full Cover", "Covers end to end expenses of all illness");
	public static final HealthBenefit cover50 = new HealthBenefit("Cover - 50%", "Insurance policy covers 50% of cost");
	public static final HealthBenefit allInclusiveCover = new HealthBenefit("All Inclusive Cover","Inclusive of meds & transportation cost");
	public static final HealthBenefit accidentCover = new HealthBenefit("Accident Cover", "Full accident cover");
	
	public static final HealthPlan dentalPlan = new HealthPlan("Dental Health Plan", asList(fullCover, cover50), "IL");
    public static final HealthPlan comprehensiveHealthPlan = new HealthPlan("Comprehensive Health Plan",  asList(cover50, allInclusiveCover),  "NY");
    public static final HealthPlan maturityPlan = new HealthPlan("Maturity Plan", asList(fullCover, cover50, allInclusiveCover, accidentCover),  "NY");
    public static final HealthPlan maternityPlan = new HealthPlan("Comprehensive Materity Plan",  asList(fullCover), "IL");
    public static final HealthPlan oldAgePlan = new HealthPlan("Old Age Plan",  asList(accidentCover), "NY");
    
    

    public static final Employee johnColtrane = new Employee("John Coltrane",  asList(dentalPlan, comprehensiveHealthPlan), asList( new Dependent("Sarah", 16), new Dependent("Richard", 12)), 45);

    public static final Employee johnLennon = new Employee("John Lennon", asList(maturityPlan),asList(new Dependent("Mike", 14), new Dependent("Nancy", 12), new Dependent("Shakira", 5)), 35) ;

    // Sarah Corner does not have any dependents
    public static final Employee sarahCorner2 = new Employee("Sarah Corner2", asList(oldAgePlan),  Collections.emptyList(), 35) ;

    public static final Employee sarahCorner = new Employee("Sarah Corner", asList(dentalPlan, oldAgePlan),  Collections.emptyList(), 35) ;
    
    
    public static final Employee sarahCorner3 = new Employee("Sarah Corner3", asList(maturityPlan, maternityPlan, dentalPlan, oldAgePlan),  Collections.emptyList(), 35) ;
    public static final Employee sarahCorner4 = new Employee("Sarah Corner4", asList(maturityPlan, maternityPlan, dentalPlan, oldAgePlan),  Collections.emptyList(), 35) ;
    

    
    


    public static Stream<Employee> employees = Stream.of(HealthData.johnColtrane, HealthData.johnLennon, HealthData.sarahCorner2, HealthData.sarahCorner);
    public static List<Employee> employeeList = Arrays.asList(HealthData.johnColtrane, HealthData.johnLennon,  HealthData.sarahCorner2, HealthData.sarahCorner, HealthData.sarahCorner3, HealthData.sarahCorner4 );
	


    public static Stream<HealthPlan> threeHealthPlans() {
    	
        return Stream.of(maturityPlan, maternityPlan, oldAgePlan);
    }

    public static List<HealthPlan> getThreeHealthPlans() {
    	
        return Arrays.asList(maturityPlan, maternityPlan, oldAgePlan);
    }

}
