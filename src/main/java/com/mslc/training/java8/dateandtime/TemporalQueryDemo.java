package com.mslc.training.java8.dateandtime;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

public class TemporalQueryDemo {

	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2014, Month.SEPTEMBER, 15);

		

		Boolean isTaxSubmissionPeriod = date.query(new TaxSubmissionPeriod());
		System.out.println(isTaxSubmissionPeriod);
	}

}

class TaxSubmissionPeriod implements TemporalQuery<Boolean> {

	@Override
	public Boolean queryFrom(TemporalAccessor temporal) {

		LocalDate date = LocalDate.from(temporal);

		MonthDay aprilFirst = MonthDay.of(Month.APRIL.getValue(), 1);
		MonthDay septemberThirty = MonthDay.of(Month.SEPTEMBER.getValue(), 30);

		if (date.isAfter(aprilFirst.atYear(date.getYear())) && date.isBefore(septemberThirty.atYear(date.getYear()))) {
			return true;
		} else {
			return false;
		}
	}
}
