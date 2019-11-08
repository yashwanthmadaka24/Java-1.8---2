package com.mslc.training.java8.dateandtime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

public class ZonedDateTimeDemo {

	public static void main(String[] args) {
		LocalDateTime ld = LocalDateTime.now();
		System.out.println(ld);
		
	
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("GMT"));
		System.out.println(zdt.get(ChronoField.DAY_OF_MONTH));
		System.out.println(zdt);

		ZoneId zoneId = ZoneId.of("Europe/Paris");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(ld, zoneId);

		System.out.println(zonedDateTime + " -- " + ld);
		
	

	}

}
