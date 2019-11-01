package com.mslc.training.java8.part1;

public class Ch3App3MultipleInheritance {

	/**
	 * <p>
	 * The Three Rules If you’re ever unsure of what will happen with default
	 * methods or with multiple inheritance of behavior, there are three simple
	 * rules for handling conflicts:
	 * </p>
	 * 
	 * <ol>
	 * <li>Any class wins over any interface. So if there’s a method with a
	 * body, or an abstract declaration, in the superclass chain, we can ignore
	 * the interfaces completely.</li>
	 * <li>Subtype wins over supertype. If we have a situation in which two
	 * interfaces are competing to provide a default method and one interface
	 * extends the other, the subclass wins.</li>
	 * <li>No rule 3. If the previous two rules don’t give us the answer, the
	 * subclass must either implement the method or declare it abstract></li>
	 * </ol>
	 * <b> Rule 1 is what brings us compatibility with old code.</b>
	 * <hr>
	 * 
	 * 
	 **/
	public static void main(String[] args) {

		TheHealthInsurance i = new TheHealthInsurance();
		i.getMaxInsuranceAmount();
		
	}
}


interface AccidentInsurance {

	default public int getMaxInsuranceAmount() {
		// in real-life, based on some business logic
		System.out.println("accident insurance");
		return 100000;
	}
}
interface DentalInsurance {

	default public int getMaxInsuranceAmount() {
		// in real-life, based on some business logic
		System.out.println("dental insurance");
		return 5000;
	}
}
class TheHealthInsurance implements AccidentInsurance, DentalInsurance {

	@Override
	public int getMaxInsuranceAmount() {
		
		// It can very well be DentalInsurance.super.getMaxInsuranceAmount()
		return AccidentInsurance.super.getMaxInsuranceAmount();
	}
}
