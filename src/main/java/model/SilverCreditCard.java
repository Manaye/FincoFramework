package model;

import interest_strategy.CCSilverMonthlyInterest;

import java.time.LocalDate;

public class SilverCreditCard extends CreditCard  {
    public SilverCreditCard(String accountNumber, String creditCardNumber, LocalDate expiredDate) {
        super(accountNumber, creditCardNumber, expiredDate);
        this.setInterestService(new CCSilverMonthlyInterest());
    }
}
