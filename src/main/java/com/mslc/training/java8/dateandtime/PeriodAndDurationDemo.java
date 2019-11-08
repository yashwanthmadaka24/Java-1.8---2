package com.mslc.training.java8.dateandtime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class PeriodAndDurationDemo {

	public static void main(String[] args) {
		
		LocalDate initialDate = LocalDate.parse("2007-05-10");

		LocalDate finalDate = initialDate.plus(Period.ofDays(5));
		System.out.println(finalDate);
		
		LocalTime initialTime = LocalTime.of(6, 30, 0);
		LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));
		LocalTime finalTime2 = initialTime.plus(Duration.ofMillis(500));
		LocalTime finalTime3 = initialTime.plus(Duration.ofNanos(500));
		
		System.out.println(finalTime);
		System.out.println(finalTime2);

		System.out.println(finalTime3);
		
		
		
	}
}
