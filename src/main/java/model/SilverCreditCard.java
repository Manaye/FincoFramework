package model;

import interest_strategy.CCSilverMonthlyInterest;

public class SilverCreditCard extends CreditCard  {
    public SilverCreditCard(String accountNumber) {
        super(accountNumber);
        this.setInterestService(new CCSilverMonthlyInterest());
    }
}
