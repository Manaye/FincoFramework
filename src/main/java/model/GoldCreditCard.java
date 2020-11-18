package model;

import interest_strategy.CCGoldMonthlyInterest;

import java.time.LocalDate;

public class GoldCreditCard extends CreditCard {
    public GoldCreditCard(String accountNumber, String creditCardNumber, LocalDate expiredDate)  {
        super(accountNumber,creditCardNumber, expiredDate);
        this.setInterestService(new CCGoldMonthlyInterest());
    }
}
