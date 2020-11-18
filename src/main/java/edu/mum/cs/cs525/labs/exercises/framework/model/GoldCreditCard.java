package edu.mum.cs.cs525.labs.exercises.framework.model;

import edu.mum.cs.cs525.labs.exercises.framework.controller.interest_strategy.CCGoldMonthlyInterest;

public class GoldCreditCard extends CreditCard {
    public GoldCreditCard(String accountNumber) {
        super(accountNumber);
        this.setInterestService(new CCGoldMonthlyInterest());
    }
}
