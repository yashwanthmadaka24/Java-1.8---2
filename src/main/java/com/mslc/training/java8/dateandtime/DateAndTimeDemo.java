package com.mslc.training.java8.dateandtime;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.JulianFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateAndTimeDemo {
	
	
	private java.util.Date utilTime;


	public static void main(String[] args) throws InterruptedException {

		ZoneId.getAvailableZoneIds().forEach(System.out::println);
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
		Date d = new Date(1000L);
		SimpleDateFormat dateStyle = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss z");
		dateStyle.setTimeZone(TimeZone.getTimeZone("IST"));
		System.out.println(dateStyle.format(d));
		System.out.println(d);

		Calendar c = Calendar.getInstance();
		System.out.println(c.getClass().getName() + " -- " + c.getCalendarType());
//		Calendar.getAvailableCalendarTypes().forEach(System.out::println);

		LocalDate date = LocalDate.now();
		System.out.println(date.getDayOfWeek());
		LocalDate date2 = LocalDate.of(1991, 11, 2);
		System.out.println(date2.getDayOfWeek());
		LocalDate date3 = LocalDate.parse("1991-11-02");
		System.out.println(date3.getDayOfWeek());

		LocalDate d4 = date3.plusYears(28);
		System.out.println(d4.getDayOfWeek() + " -- " + d4.getYear());

		LocalDate d5 = d4.minus(28, ChronoUnit.YEARS);
		System.out.println(d5.getDayOfWeek());

		System.out.println(d5.isLeapYear());

		System.out.println(" ---- ");
		LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
		LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12").with(TemporalAdjusters.firstDayOfMonth());
		System.out.println(beginningOfDay + " -- " + firstDayOfMonth);
		
		System.out.println("Day of the weeek : " + firstDayOfMonth.get(ChronoField.DAY_OF_WEEK));
		
		
		LocalTime now = LocalTime.now(ZoneId.of("Asia/Kolkata"));
		System.out.println("now : " + now);
		
		LocalTime sixThirty = LocalTime.parse("06:30");
		LocalTime sevenThirty = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);
		System.out.println(sevenThirty);
		
		System.out.println(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
		
		System.out.println(LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30));
		
		System.out.println(LocalDateTime.parse("2015-02-20T06:30:00"));
		
		
		
		
		
		
	}

}
