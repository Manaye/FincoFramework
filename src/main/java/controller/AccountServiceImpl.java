package controller;

import dao.AccountDAO;
import dao.AccountDAOImpl;
import model.*;
import observers.EmailSender;
import observers.Logger;
import observers.SMSSender;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Observer;

public class AccountServiceImpl implements AccountService {
	private AccountDAO accountDAO;
	private Observer emailSender = new EmailSender();
	private Observer[] arrayAccountChangeObservers;

    private static AccountServiceImpl instance;
	
	
	public static AccountServiceImpl getInstance() {
		if(instance == null) instance = new AccountServiceImpl();
		return instance;
	}
	
	public AccountServiceImpl() {
		accountDAO = new AccountDAOImpl();

		arrayAccountChangeObservers = new Observer[] { new SMSSender(), new Logger() };
	}

	public Account createCreditAccount(String accountNumber, String customerName, String email, String street, String city,
			String state, String zip, String creditCardNumber, LocalDate expiredDate,  CreditCardType accountType,  AccountClass accountclass) {
		
		Account account = new Account(accountNumber, AccountClass.CREDITCARD);
		switch (accountType) {
		case GOLD:
			account = new GoldCreditCard(accountNumber, creditCardNumber, expiredDate, accountclass);
			break;
		case SILVER:
			account = new SilverCreditCard(accountNumber, creditCardNumber, expiredDate, accountclass);
			break;
		default:
			account = new BronzeCreditCard(accountNumber, creditCardNumber, expiredDate, accountclass);
		}

		Address address = new Address(street, city, state, zip);
		Customer customer = new Customer(customerName, email, address);
		account.setCustomer(customer);

		// Add Email observer
		account.addObserver(emailSender);

		accountDAO.saveAccount(account);
		return account;
	}
	public Account createPersonalAccount(String accountNumber, String name, AccountType accountType, String street, 
			String city, String state, String zip, String email, LocalDate birthdate, AccountClass accountclass) {
		
		Account account = new Account(accountNumber, accountType, AccountClass.PERSONAL);
		Address address = new Address(street, city, state, zip);
		Customer customer = new Customer(name, email, address);
		
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		account.addObserver(emailSender);
		
		return account;
	}
	//String accountNumber, AccountType accountType, AccountClass accountClass
	public Account createCompanyAccount(String accountNumber, String name, AccountType accountType, String street, 
			String city, String state, String zip, String email, int numEmployees, AccountClass accountclass) {
		
		Account account = new Account(accountNumber, accountType, AccountClass.COMPANY);
		Address address = new Address(street, city, state, zip);
		Customer customer = new Customer(name, email, address);
		
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		account.addObserver(emailSender);
		
		return account;
	}


	
	private void addObservers(Account account, Observer arrayObs[]) {
		if (arrayObs == null)
			return;
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

		if (amount > 400) {
			account.notifyChanged();
		}
	}

	public void addInterest(String accountNumber) {
		for (Account account : this.getAllAccounts()) {
			account.addInterest();
		}
	}

	public void getBillingReport() {
		// TODO
	}
}
