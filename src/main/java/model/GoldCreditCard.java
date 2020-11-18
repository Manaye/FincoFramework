package model;

import interest_strategy.CCGoldMonthlyInterest;

public class GoldCreditCard extends CreditCard {
    public GoldCreditCard(String accountNumber) {
        super(accountNumber);
        this.setInterestService(new CCGoldMonthlyInterest());
    }
}
