/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mslc.training.java8.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Domain class for a HealthPlan
 * 
 * @author Muhammed Shakir
 */
public final class HealthPlanComprehensiveImpl implements HealthPlan {
    
	// HealthPlan name starting with "Compre" will be considered as comprehensive
    private String name;
    private List<HealthBenefit> benefits;
    private String state;
    
  public HealthPlanComprehensiveImpl() {
    	
        this("default", Collections.emptyList(), "all-states");
        
    }
    
    public HealthPlanComprehensiveImpl(String name, String state) {
    	
        this(name, Collections.emptyList(), state);
        
    }

    public HealthPlanComprehensiveImpl(String name, List<HealthBenefit> benefits, String state) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(benefits);
        Objects.requireNonNull(state);
        this.name = name;
        this.benefits = new ArrayList<>(benefits);
        this.state = state;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the members
     */
    public Stream<HealthBenefit> getBenefits() {
        return benefits.stream();
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    public boolean defaultBenefits() {
    	
        return benefits.isEmpty();
        
    }

    public boolean isFrom(String state) {
    	
        return this.state.equals(state);
    }

    @Override
    public String toString() {
        return getName();
    }

    public HealthPlanComprehensiveImpl copy() {
    	
        List<HealthBenefit> benefits = getBenefits()
        									.map(HealthBenefit::copy)
        									.collect(toList());
        
        return new HealthPlanComprehensiveImpl(name, benefits, state);
    }

	@Override
	public void applyBenefits() {
		// TODO Auto-generated method stub
		
	}

}
