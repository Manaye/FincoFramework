package framework;


import model.*;
import observers.Logger;
import observers.SMSSender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observer;

import framework.CheckingInterest;
import framework.InterestService;
import framework.InterestStrategy;
import framework.SavingInterest;

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
		Customer customer = new Customer(customerName, email, address,LocalDate.now(),0);
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
		
		Customer customer = new Customer(name, email, address,birthdate,0);

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
		Customer customer = new Customer(name, email, address,LocalDate.now(),numEmployees);

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
		//List<String> billstring = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		/*for (Account account : this.getAllAccounts()) {
			sb.append(String.valueOf(account.getMonthlyInterest()) + ",");
			sb.append(account.getAccountNumber());
			sb.append(String.valueOf(account.getTotalCharge() + ","));
			sb.append("\n");
			
		}
		return sb.toString();
		*/
		
	     /*   getAllAccounts()
	                .stream().filter(account -> account.getMonthlyInterest() != null)
	                .forEach((Account acc) -> {
	            billstring.add(acc.billingReport());
	        });
	        return billstring ;
	
*/
		BillingReport billingReport = new BillingReport(this.getAllAccounts());
		return billingReport.getBillingReport();
	
	}
}
