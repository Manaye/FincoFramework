package controller;


import dao.AccountDAO;
import dao.AccountDAOImpl;
import interest_strategy.CheckingInterest;
import interest_strategy.InterestService;
import interest_strategy.InterestStrategy;
import interest_strategy.SavingInterest;
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
	 InterestStrategy checkingInterest = new CheckingInterest();
	 InterestStrategy savingInterest = new SavingInterest();

    private static AccountServiceImpl instance = new AccountServiceImpl();

	private AccountServiceImpl() {
		accountDAO = new AccountDAOImpl();

		arrayAccountChangeObservers = new Observer[] { new SMSSender(), new Logger() };
	}
	public static AccountService getInstance() {
		return instance;
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


		//Add Email observer
		account.addObserver(emailSender);

		accountDAO.saveAccount(account);
		return account;
	}

	public Account createPersonalAccount(String accountNumber, String name, AccountType accountType, String street,
										 String city, String state, String zip, String email, LocalDate birthdate, AccountClass accountclass) {

		Account account = new Account(accountNumber, accountType, AccountClass.PERSONAL);
		Address address = new Address(street, city, state, zip);
		Customer customer = new Customer(name, email, address);

		if(accountType.equals(AccountType.CHECKING)) {
			account.setInterestStrategy(checkingInterest);
		}else if(accountType.equals(AccountType.SAVING)) {
			account.setInterestStrategy(savingInterest);
		}
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

		if(accountType.equals(AccountType.CHECKING)) {
			account.setInterestStrategy(checkingInterest);
		}else if(accountType.equals(AccountType.SAVING)) {
			account.setInterestStrategy(savingInterest);
		}
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		account.addObserver(emailSender);

		return account;
	}

	private void addObservers(Account account, Observer arrayObs[]) {
		if(arrayObs == null) return;
		for (Observer obs : arrayObs) {
			account.addObserver(obs);
		}
	}

	public Account getAccount(String accountNumber) {
		Account account = accountDAO.loadAccount(accountNumber);
		return account;
	}

	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	public void deposit(String accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);

		accountDAO.updateAccount(account);
	}

	public void withdraw(String accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);

		account.withdraw(amount);

		accountDAO.updateAccount(account);
	}

	@Override
	public void addInterest(String accountNumber) {

	//		Account account = accountDAO.loadAccount(accountNumber);

	}	
	
	
	

	public void addInterest() {
		for (Account account : this.getAllAccounts()) {
			account.addInterest();
		}
	}

	public String getBillingReport() {
		//TODO
		String billstring = "";

		for (Account account : getAllAccounts()) {
			billstring += "Name= John White\r\n";
			billstring += "Address= 1000 Main, Fairfield, IA, 52556\r\n";
			billstring += "CC number= 2341 3421 4444 5689\r\n";
			billstring += "CC type= GOLD\r\n";
			billstring += "Previous balance = $ " + account.getPreviousBalance() + "\r\n";
			billstring += "Total Credits = $ " + account.getTotalCredit() + "\r\n";
			billstring += "Total Charges = $ " + account.getTotalCharge() + "\r\n";
			billstring += "New balance = $ " + account.getBalance() + "\r\n";
			billstring += "Total amount due = $" + account.getMinimumPayment() + "\r\n\r\n";
		}

		return billstring;

//		BillingReport billingReport = new BillingReport();
	}
}
