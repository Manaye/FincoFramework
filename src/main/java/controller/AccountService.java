package controller;

import model.Account;
import model.AccountClass;
import model.AccountType;
import model.CreditCardType;

import java.time.LocalDate;
import java.util.Collection;

public interface AccountService {

	Account createCreditAccount(String accountNumber, String customerName, String email, String street, String city,
			String state, String zip, String creditCardNumber, LocalDate expiredDate,  CreditCardType accountType,  AccountClass accountclass);

	Account createPersonalAccount(String accountNumber, String customerName, AccountType accountType, String customerStreet,
			String customerCity, String customerState, String customerZip, String customerEmail, LocalDate birthdate, AccountClass accountclass);

	Account createCompanyAccount(String accountNumber, String name, AccountType accountType, String street,
			String city, String state, String zip, String email, int numEmployees, AccountClass accountclass);

	Account getAccount(String accountNumber);

	Collection<Account> getAllAccounts();

	void deposit(String accountNumber, double amount);

	void withdraw(String accountNumber, double amount);

	// void transferFunds(String fromAccountNumber, String toAccountNumber, double
	// amount, String description);
	void addInterest(String accountNumber);
	void addInterest();

	String getBillingReport(); // TODO
	

}
