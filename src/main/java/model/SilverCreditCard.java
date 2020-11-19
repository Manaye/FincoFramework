package model;

import java.time.LocalDate;

import framework.AccountClass;
import framework.CCSilverMonthlyInterest;

public class SilverCreditCard extends CreditCard  {

	public SilverCreditCard(String accountNumber, String creditCardNumber, LocalDate expireDate,
			AccountClass accountClass) {
		super(accountNumber, creditCardNumber, expireDate, accountClass);
		this.setInterestService(new CCSilverMonthlyInterest());
    
        
    }
}
