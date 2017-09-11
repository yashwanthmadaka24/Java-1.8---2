package com.infomover.training.java8.ch10.template;

// BEGIN CompanyLoanApplication
public class CompanyLoanApplication extends LoanApplication {

    public CompanyLoanApplication(Company company) {
        super(company::checkIdentity,
              company::checkHistoricalDebt,
              company::checkProfitAndLoss);
    }

}
// END CompanyLoanApplication
