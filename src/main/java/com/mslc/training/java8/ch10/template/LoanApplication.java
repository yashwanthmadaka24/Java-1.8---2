package com.mslc.training.java8.ch10.template;

// BEGIN LoanApplication
public class LoanApplication {

	private final Criteria identity;
	private final Criteria creditHistory;
	private final Criteria incomeHistory;

	public LoanApplication(Criteria identity, Criteria creditHistory, Criteria incomeHistory) {

		this.identity = identity;
		this.creditHistory = creditHistory;
		this.incomeHistory = incomeHistory;
	}

	public void checkLoanApplication() throws ApplicationDenied {
		identity.check();
		creditHistory.check();
		incomeHistory.check();
		reportFindings();
	}

	private void reportFindings() {
		// END LoanApplication

	}

}
