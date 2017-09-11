package com.infomover.training.java8.part1;

import java.util.function.Predicate;

public class App0RoughWork {

	public static void main(String[] args) {

		int len = 4;

		Predicate<String> f = new Predicate<String>() {

			@Override
			public boolean test(String t) {
				// TODO Auto-generated method stub
				return t.length() > len;
			}

		};

	}

}
