package com.mslc.training.java8.dateandtime;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ConversionBetweenTimeZones {

	private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";

	public static void main(String[] args) {

//		String dateInString = "22-1-2015 10:15:55 AM";
//		LocalDateTime ldt =  LocalDateTime.now();//LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));
//		
//		System.out.println(ldt);
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		System.out.println(zonedDateTime);

		ZonedDateTime inLondon = zonedDateTime.withZoneSameInstant(ZoneId.of("Europe/London"));
		System.out.println(inLondon);
		System.out.println(inLondon.withZoneSameInstant(ZoneId.of("Asia/Kolkata")));

	}

}
