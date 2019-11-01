package com.mslc.training.java8.ch10.template;

public class EmployeeLoanApplication extends LoanApplication {

	public EmployeeLoanApplication(Employee employee) {

		super(employee::checkIdentity, employee::checkHistoricalDebt, employee::checkProfitAndLoss);
		// TODO Auto-generated constructor stub
	}

}
