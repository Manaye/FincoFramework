package controller;

import model.*;
import observers.EmailSender;
import observers.Logger;
import observers.SMSSender;

import java.util.Collection;
import java.util.Observer;

import dao.AccountDAO;
import dao.AccountDAOImpl;

public class AccountServiceImpl implements AccountService {
	private AccountDAO accountDAO;
	private Observer emailSender = new EmailSender();
	private Observer[] arrayAccountChangeObservers;
	
	public AccountServiceImpl(){
		accountDAO = new AccountDAOImpl();

		arrayAccountChangeObservers = new Observer[]{new SMSSender(), new Logger()};
	}

	public Account createAccount(String accountNumber, String customerName) {
		Account account = new Account(accountNumber);

		Customer customer = new Customer(customerName);
		account.setCustomer(customer);

		//Add Email observer
		account.addObserver(emailSender);
		
		accountDAO.saveAccount(account);
		
		return account;
	}

	public Account createAccount(String accountNumber, String customerName, CreditCardType accountType) {
		Account account;
		switch (accountType) {
			case GOLD: account = new GoldCreditCard(accountNumber);
				break;
			case SILVER: account = new SilverCreditCard(accountNumber);
				break;
			default: account = new BronzeCreditCard(accountNumber);
		}

		Customer customer = new Customer(customerName);
		account.setCustomer(customer);

		//Add Email observer
		account.addObserver(emailSender);

		accountDAO.saveAccount(account);
		return account;
	}

	private void addObservers(Account account, Observer arrayObs[]) {
		if(arrayObs == null) return;
		for (Observer obs : arrayObs) {
			account.addObserver(obs);
		}
	}

	public void deposit(String accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);

		accountDAO.updateAccount(account);
	}

	public Account getAccount(String accountNumber) {
		Account account = accountDAO.loadAccount(accountNumber);
		return account;
	}

	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	public void withdraw(String accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);

		if (amount>400) {
			account.notifyChanged();
		}
	}

	public void addInterest() {
		for (Account account : this.getAllAccounts()) {
			account.addInterest();
		}
	}

	public void getBillingReport() {
		//TODO
	}
}
