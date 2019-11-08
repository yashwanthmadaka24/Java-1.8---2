package com.mslc.training.java8.dateandtime;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateFormatterDemo {

	public static void main(String[] args) {

		LocalDateTime localDateTime = LocalDateTime.of(2015, Month.JANUARY, 25, 6, 30);

		String localDateString = localDateTime.format(DateTimeFormatter.ISO_DATE);
		System.out.println(localDateString);
		System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MMMM/dd")));
	}

}
