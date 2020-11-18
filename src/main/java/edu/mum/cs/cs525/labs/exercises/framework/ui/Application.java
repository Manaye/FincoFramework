package edu.mum.cs.cs525.labs.exercises.framework.ui;

import edu.mum.cs.cs525.labs.exercises.framework.controller.AccountService;
import edu.mum.cs.cs525.labs.exercises.framework.controller.AccountServiceImpl;
import edu.mum.cs.cs525.labs.exercises.framework.model.*;
import edu.mum.cs.cs525.labs.exercises.framework.model.CreditCardType;

public class Application {
	public static void main(String[] args) {
		AccountService accountService = new AccountServiceImpl();

		// create 2 accounts;
		accountService.createAccount("1263862", "Frank Brown", CreditCardType.GOLD);
		accountService.createAccount("4253892", "John Doe", CreditCardType.SILVER);
//		accountService.createAccount("4253892", "John Doe", CreditCardType.GOLD);

		// use account 1;
		accountService.deposit("1263862", 240);
		accountService.deposit("1263862", 529);
//		accountService.withdraw("1263862", 230);
		accountService.withdraw("1263862", 450);

		// use account 2;
		accountService.deposit("4253892", 12450);

		// add interest to all accounts
		accountService.addInterest();

		// show balances
		for (Account account : accountService.getAllAccounts()) {
			String accountType = "";
			Customer customer = account.getCustomer();
			System.out.println("Statement for Account: " + account.getAccountNumber());
			System.out.println("Account Holder: " + customer.getName());
			System.out.println("-Date-------------------------" 
					+ "-Description------------------" 
					+ "-Amount-------------");


			
			for (AccountEntry entry : account.getEntryList()) {
				System.out.printf("%30s%30s%20.2f\n", 
						entry.getDate().toString(), 
						entry.getDescription(),
						entry.getAmount());
			}
			
			System.out.println("----------------------------------------" + "----------------------------------------");
			System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
			System.out.printf("%30s%30s%20.2f\n\n", "", "Minimum Payment:", account.getMinimumPayment());
		}
	}

}