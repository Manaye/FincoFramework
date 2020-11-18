package model;

import interest_strategy.CCBronzeMonthlyInterest;

public class BronzeCreditCard extends CreditCard {
    public BronzeCreditCard(String accountNumber) {
        super(accountNumber);
        this.setInterestService(new CCBronzeMonthlyInterest());
    }
}
