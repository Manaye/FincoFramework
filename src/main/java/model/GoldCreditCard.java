package model;

import java.time.LocalDate;

import framework.AccountClass;
import framework.CCGoldMonthlyInterest;

public class GoldCreditCard extends CreditCard {

	public GoldCreditCard(String accountNumber, String creditCardNumber, LocalDate expireDate,
			AccountClass accountClass) {
		super(accountNumber, creditCardNumber, expireDate, accountClass);
		 this.setInterestService(new CCGoldMonthlyInterest());	
		 }
    
       
    }

