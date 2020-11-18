package model;

import interest_strategy.CCBronzeMonthlyInterest;

import java.time.LocalDate;

public class BronzeCreditCard extends CreditCard {
    public BronzeCreditCard(String accountNumber, String creditCardNumber, LocalDate expiredDate) {
        super(accountNumber, creditCardNumber, expiredDate);
        this.setInterestService(new CCBronzeMonthlyInterest());
    }
}
