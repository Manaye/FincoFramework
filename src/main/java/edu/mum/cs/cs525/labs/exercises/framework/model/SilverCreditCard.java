package edu.mum.cs.cs525.labs.exercises.framework.model;

import edu.mum.cs.cs525.labs.exercises.framework.controller.interest_strategy.CCSilverMonthlyInterest;

public class SilverCreditCard extends CreditCard  {
    public SilverCreditCard(String accountNumber) {
        super(accountNumber);
        this.setInterestService(new CCSilverMonthlyInterest());
    }
}
