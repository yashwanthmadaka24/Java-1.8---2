package com.mslc.training.java8.dateandtime;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ZoneOffSetDemo {

	public static void main(String[] args) {
		
		

		LocalDateTime localDateTime = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
		ZoneOffset offset = ZoneOffset.of("+02:00");
		 
		OffsetDateTime offSetByTwo = OffsetDateTime
		  .of(localDateTime, offset);
		System.out.println(offSetByTwo.toLocalDateTime());
	}

}
