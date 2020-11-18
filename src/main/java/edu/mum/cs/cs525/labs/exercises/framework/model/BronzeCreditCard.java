package edu.mum.cs.cs525.labs.exercises.framework.model;

import edu.mum.cs.cs525.labs.exercises.framework.controller.interest_strategy.CCBronzeMonthlyInterest;

public class BronzeCreditCard extends CreditCard {
    public BronzeCreditCard(String accountNumber) {
        super(accountNumber);
        this.setInterestService(new CCBronzeMonthlyInterest());
    }
}
