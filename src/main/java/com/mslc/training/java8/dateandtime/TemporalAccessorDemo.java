package com.mslc.training.java8.dateandtime;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

public class TemporalAccessorDemo {

	public static void main(String[] args) {

		// Parse String to Resolve Time
		DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
		TemporalAccessor temporal = f.parse("20:55:00");
		System.out.println(temporal.get(ChronoField.AMPM_OF_DAY));
		System.out.println(" AM / PM : " + temporal.get(ChronoField.HOUR_OF_AMPM));
		String msg = temporal.toString();
		System.out.println(msg.contains("11:30:56"));
		System.out.println(msg);

		// Parse String to Resolve Date
		DateTimeFormatter f1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		TemporalAccessor temporal1 = f1.parse("2010-06-30");
		String msg1 = temporal1.toString();
		System.out.println(msg1);
		System.out.println(temporal1.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
		
		parseStringToResolveDateAndTime();

	}
	
	public static void parseStringToResolveDateAndTime() {
	    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    TemporalAccessor temporal = f.parse("2010-06-30 11:30:56");
	    String msg = temporal.toString();
	    System.out.println(msg.contains("2010-06-30"));
	    System.out.println(msg.contains("11:30:56"));
	}

}
