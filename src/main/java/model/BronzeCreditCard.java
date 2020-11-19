package model;

import java.time.LocalDate;

import framework.AccountClass;
import framework.CCBronzeMonthlyInterest;

public class BronzeCreditCard extends CreditCard {

	public BronzeCreditCard(String accountNumber, String creditCardNumber, LocalDate expireDate,
			AccountClass accountClass) {
		super(accountNumber, creditCardNumber, expireDate, accountClass);
		  this.setInterestService(new CCBronzeMonthlyInterest());
	}
//    public BronzeCreditCard(String accountNumber, String creditCardNumber, LocalDate expiredDate) {
//        super(accountNumber, creditCardNumber, expiredDate);
     
//    }
}
