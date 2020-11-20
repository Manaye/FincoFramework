package framework;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

import framework.InterestService;
import framework.InterestStrategy;
import observers.Logger;


public class Account extends Observable {
	
	private Customer customer;
	private String accountNumber;
	private List<AccountEntry> entryList = new ArrayList<AccountEntry>();
	private AccountClass accountClass;
	private AccountType accountType;
	private InterestService interestService;
	private InterestStrategy interestStrategy;
	
	
	
	
	//bank account contractor
	public Account(String accountNumber, AccountType accountType, AccountClass accountClass) {
	    this.accountNumber = accountNumber;
	    this.accountType = accountType;
	    this.accountClass = accountClass;
	   
	   
	}
	//credit card contractor
	public Account(String accountNumber, AccountClass accountClass) {
		this.accountNumber = accountNumber;
		this.accountClass = accountClass;
		
	}
	public Account() {
		
		// TODO Auto-generated constructor stub
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public AccountClass getAccountClass() {
		return accountClass;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public InterestService getInterestService() {
		return interestService;
	}

	public InterestStrategy getInterestStrategy() {
		return interestStrategy;
	}

	public double getBalance() {
		double balance = 0;
		for (AccountEntry entry : entryList) {
			balance += entry.getAmount();
		}
		return balance;
	}

	public void deposit(double amount) {
		System.out.println("amount" + amount);
		AccountEntry entry = new AccountEntry(LocalDate.now(),amount, "deposit", "", "");
		entryList.add(entry);
		System.out.println("Deposited " + amount);

		if(amount > 400) {
			this.notifyChanged(entry);
		}
	}

	public void withdraw(double amount) {
		AccountEntry entry = new AccountEntry(LocalDate.now(),-amount, "withdraw", "", "");
		entryList.add(entry);
		System.out.println("Withdrawn " + amount);

		if(amount > 400) {
			this.notifyChanged(entry);
		}
	}

	private void addEntry(AccountEntry entry) {
		entryList.add(entry);
	}



	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<AccountEntry> getEntryList() {
		return entryList;
	}

	public double performInterest() {
		double balance = this.getBalance();
		double tempBalance = 0;

		if(this.getAccountType().equals(AccountType.CHECKING) ||
				this.getAccountType().equals(AccountType.SAVING)) {
		   tempBalance = interestStrategy.calInterest(balance);
		}else {
			tempBalance = interestService.calInterest(balance);
		}
		return tempBalance;
	}

	public double getMonthlyInterest() {
		return interestService.getMonthlyInterest();
	}

	public double getMinimumPayInterest() {
		return interestService.getMinimumPayInterest();
	}

	public double getMinimumPayment() {
		double balance = this.getBalance();

		return interestService.caclMinPayment(balance);
	}

	public void setInterestService(InterestService interestService) {
		this.interestService = interestService;
	}
	public void setInterestStrategy(InterestStrategy interestStrategy) {
		this.interestStrategy = interestStrategy;
	}


	public void addInterest() {
		AccountEntry entry = new AccountEntry(LocalDate.now(),this.performInterest(), "interest", "", "");
		entryList.add(entry);
	}

	public void notifyChanged(AccountEntry entry) {
		this.setChanged();
		this.notifyObservers(entry);
	}

	//Get total charge of an account in current month.
	public double getTotalCharge() {
		double result = 0.0;

		Collection<AccountEntry> accountEntries = this.getEntryList();

		result = accountEntries.stream()
				.filter(accountEntry -> accountEntry.getDescription().equals("withdraw"))
				.filter(accountEntry -> DateToLocalDate(accountEntry.getDate()).getMonthValue() == LocalDate.now().getMonthValue())
				.filter(accountEntry -> Period.between(DateToLocalDate(accountEntry.getDate()), LocalDate.now()).getMonths()==0)
				.map(accountEntry -> accountEntry.getAmount())
				.reduce(0.0, (num1, num2) -> num1 + num2);

		return result;
	}

	//Get total charge of an account in current month.
	public double getTotalCredit() {
		double result = 0.0;

		Collection<AccountEntry> accountEntries = this.getEntryList();

		result = accountEntries.stream()
				.filter(accountEntry -> accountEntry.getDescription().equals("deposit"))
				.filter(accountEntry -> DateToLocalDate(accountEntry.getDate()).getMonthValue() == LocalDate.now().getMonthValue())
				.filter(accountEntry -> Period.between(DateToLocalDate(accountEntry.getDate()), LocalDate.now()).getMonths()==0)
				.map(accountEntry -> accountEntry.getAmount())
				.reduce(0.0, (num1, num2) -> num1 + num2);

//		for (AccountEntry accountEntry: accountEntries
//			 ) {
//			System.out.println(accountEntry.getDescription().equals("deposit"));
//			System.out.println(DateToLocalDate(accountEntry.getDate()).getMonthValue());
//			System.out.println(Period.between(DateToLocalDate(accountEntry.getDate()), LocalDate.now()).getMonths());
//		}
		return result;
	}

	//Get total charge of an account in current month.
	public double getPreviousBalance() {
		double result = 0.0;

		Collection<AccountEntry> accountEntries = this.getEntryList();

		result = accountEntries.stream()
				.filter(accountEntry -> DateToLocalDate(accountEntry.getDate()).getMonthValue() == LocalDate.now().getMonthValue())
				.filter(accountEntry -> Period.between(DateToLocalDate(accountEntry.getDate()), LocalDate.now()).getMonths()==1)
				.map(accountEntry -> accountEntry.getAmount())
				.reduce(0.0, (num1, num2) -> num1 + num2);

		return result;
	}

	public LocalDate DateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	//Should be override
	public String getCCNumber() {
		return null;
	}

	public String billingReport() {
		return null;
	}
}
