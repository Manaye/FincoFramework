package ui;

import model.*;

import java.time.LocalDate;

import framework.Account;
import framework.AccountClass;
import framework.AccountEntry;
import framework.AccountService;
import framework.AccountServiceImpl;
import framework.Customer;

public class Application {
	public static void main(String[] args) {
		AccountService accountService = AccountServiceImpl.getInstance();

		// create 2 accounts;
		accountService.createCreditAccount("1263862", "Frank Brown","nvhbao@yopmail.com",
				"1004 N 4th St", "Fairfield", "IA", "52557", "123456789", LocalDate.now(), CreditCardType.GOLD, AccountClass.CREDITCARD);
		accountService.createCreditAccount("4253892", "John Doe", "nvhbao@yopmail.com", "1004 N 4th St", "Fairfield", "IA", "52557", "123456789", LocalDate.now(), CreditCardType.SILVER, AccountClass.CREDITCARD);

		accountService.deposit("1263862", 240);
		accountService.deposit("1263862", 529);
		accountService.withdraw("1263862", 230);
		accountService.withdraw("1263862", 450);
		

		// use account 2;
		accountService.deposit("4253892", 12450);

		// add interest to all accounts
		accountService.addInterest("hhh");

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
			System.out.printf("%30s%30s%20.2f\n", "", "Current Balance:", account.getBalance());
			System.out.printf("%30s%30s%20.2f\n\n", "", "Minimum Payment:", account.getMinimumPayment());
		}

		System.out.println(accountService.getBillingReport());
	}

}
