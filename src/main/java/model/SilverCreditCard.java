package model;

import interest_strategy.CCSilverMonthlyInterest;

import java.time.LocalDate;

public class SilverCreditCard extends CreditCard  {

	public SilverCreditCard(String accountNumber, String creditCardNumber, LocalDate expireDate,
			AccountClass accountClass) {
		super(accountNumber, creditCardNumber, expireDate, accountClass);
		this.setInterestService(new CCSilverMonthlyInterest());
    
        
    }
}
