package com.infomover.training.java8.part1;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Ch4App0MethodReferences {

	public static void main(String[] args) {

		// static method reference : ContainingClass::methodName

		BiConsumer<ClassA, String> staticReference0 = (x, y) -> System.out
				.println(x.getClass().getName() + " -- " + y.getClass().getName());
		staticReference0.accept(new ClassA(), "2");

//		IntStream.of(1, 2, 3).forEach(System.out::println);

		BiConsumer<ClassA, String> staticReference = ClassA::add;
		staticReference.accept(new ClassA("static method reference"), "add");

		// Reference to instance method of a particular object :
		// containingObject::methodName

		ClassA a = new ClassA("instance method reference");

		BiConsumer<ClassA, String> methodReference = a::subtract0;
		methodReference.accept(a, "subtract0");

		// Reference to an instance method of an arbitrary object of particular type;

		BiConsumer<ClassA, String> arbitraryObjectReference = ClassA::subtract;
		arbitraryObjectReference.accept(new ClassA("arbitrary object method reference"), "subtract");

		// similar in functionality but without method reference
		BiConsumer<ClassA, String> arbitraryObjectReference2 = (x, y) -> x.subtract(y);
		arbitraryObjectReference2.accept(new ClassA("no method reference"), "subtract");

		// Reference to the constructor

		Supplier<ClassA> f1 = ClassA::new;
		ClassA aNew1 = f1.get();

		Function<String, ClassA> f2 = ClassA::new;
		ClassA aNew = f2.apply("Constructor 2");

	}

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

	@Override
	public String toString() {

		return id;
	}

	public static void add(ClassA a, String word) {

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
