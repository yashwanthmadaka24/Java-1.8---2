package com.mslc.training.java8.part1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Ch4App0MethodReferences {

	public static void main(String[] args) {

		// static method reference : ContainingClass::methodName

		BiConsumer<ClassA, String> staticReference0 = (x, y) -> System.out
				.println(x.getClass().getName() + " -- " + y.getClass().getName());

		staticReference0.accept(new ClassA(), "2");

		BiConsumer<ClassA, String> staticReference = ClassA::add;
		staticReference.accept(new ClassA("static method reference"), "add");

		BiConsumer<Integer, String> staticReference1 = ClassA::add0;
		staticReference1.accept(2, "add");

		// Reference to instance method of a particular object :
		// containingObject::methodName

		ClassA a = new ClassA("instance method reference");

		BiConsumer<ClassA, String> methodReference = a::subtract0;
		methodReference.accept(a, "subtract0");

		// Reference to an instance method of an arbitrary object of particular type;

		BiConsumer<ClassA, String> arbitraryObjectReference = ClassA::subtract;
		arbitraryObjectReference.accept(new ClassA("arbitrary object method reference"), "subtract");

		BiConsumer<HealthInsuranceService, String> arbitraryObjectReference2 = HealthInsuranceService::deactivateInsurance;
		arbitraryObjectReference2.accept(new HealthInsuranceService("Arbitrary Object"), "deactivateInsurance");

		// similar in functionality but without method reference
		BiConsumer<ClassA, String> arbitraryObjectReference3 = (x, y) -> x.subtract(y);
		arbitraryObjectReference3.accept(new ClassA("no method reference"), "subtract");

		// the following first0 represents pure functionality
		MyComparator<String> first0 = String::compareToIgnoreCase;
		System.out.println(first0.cmp("shakir", "shakir1"));

		// the above is as same as the following
		Comparator<String> stringComparator = (first, second) -> first.compareToIgnoreCase(second);

		// Reference to the constructor

		Supplier<ClassA> f1 = ClassA::new;
		ClassA aNew1 = f1.get();

		Function<String, ClassA> f2 = ClassA::new;

		// constructor 2 gets executed
		ClassA aNew = f2.apply("Constructor 2");

		MyFunctionalInterfaceToTest<String, Integer, ClassA> f3 = ClassA::new;

		// f3.testing actually results in executing the constructor
		ClassA a3 = f3.testing("asdsa", 22);
		a3.subtract("asdsadasd");

		BiFunction<String, Integer, ClassA> f4 = ClassA::new;

		f4.apply("asdd", 22);

	}

}

interface MyFunctionalInterfaceToTest<T, U, R> {

	R testing(T s, U i);
}

class ClassA {

	private String id;

	ClassA() {
//		System.out.println("Default Constructor");
	}

	ClassA(String id) {
//		System.out.println("Constructor 2");
		this.id = id;
	}

	ClassA(String id, Integer i) {
//		System.out.println("Constructor 2");
		this.id = id;
	}

	@Override
	public String toString() {

		return id;
	}

	public static void add(ClassA a, String word) {

		System.out.println("in add : " + word + " -- " + a);

		// return a;
	}

	public static void add0(Integer a, String word) {

		System.out.println("in add : " + word + " -- " + a);

		// return a;
	}

	public ClassA subtract0(ClassA a, String word) {

		System.out.println("in subtract0 : " + word + " -- " + a);

		return a;
	}

	public ClassA subtract(String word) {

		System.out.println("in subtracT : " + word + " -- " + this);

		return this;
	}

}

class DemoClassA {

	public static Integer test(String value, String value2) {
		System.out.println("In test : " + value);
		return value.length();
	}
}

@FunctionalInterface
interface TestInterface {

	void t();

}

interface MyComparator<T> {

	int cmp(T o1, T o2);
}

class HealthInsuranceService {

	private String id;

	HealthInsuranceService() {

	}

	HealthInsuranceService(String id) {
		this.id = id;
	}

	@Override
	public String toString() {

		return id;
	}

	public static HealthInsuranceService addNewInsurance(HealthInsuranceService service, String insuranceName) {

		System.out.println("in addNewInsurance : " + insuranceName + " -- " + service);

		return service;
	}

	public HealthInsuranceService activateInsurance(HealthInsuranceService service, String insuranceName) {

		System.out.println("in activateInsurance : " + insuranceName + " -- " + service);

		return service;
	}

	public String deactivateInsurance(String insuranceName) {

		System.out.println("in deactivateInsurance : " + insuranceName + " -- " + this);

		Consumer<Person> pf = Person::printName;
		new PersonProcessor().processPerson(pf);

		return "this";
	}

}

class PersonProcessor {

	public void processPerson(Consumer<Person> pf) {
		List<Person> persons = Arrays.asList(new Person[] { new Person("Shakir"), new Person("Farhan") });
		for (Person p : persons) {
			pf.accept(p);
		}

	}
}

class Person {
	private String name;

	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(final String name) {
		this.name = name;
	}

	public void printName() {
		System.out.println(name);
	}
}
