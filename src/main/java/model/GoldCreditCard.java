package model;

import interest_strategy.CCGoldMonthlyInterest;

import java.time.LocalDate;

public class GoldCreditCard extends CreditCard {

	public GoldCreditCard(String accountNumber, String creditCardNumber, LocalDate expireDate,
			AccountClass accountClass) {
		super(accountNumber, creditCardNumber, expireDate, accountClass);
		 this.setInterestService(new CCGoldMonthlyInterest());	
		 }
    
       
    }

