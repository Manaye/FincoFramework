package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

import interest_strategy.InterestService;

public class Account extends Observable {
	private Customer customer;

	private String accountNumber;

	private List<AccountEntry> entryList = new ArrayList<AccountEntry>();

	public Account(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	private InterestService interestService;

	public double getBalance() {
		double balance = 0;
		for (AccountEntry entry : entryList) {
			balance += entry.getAmount();
		}
		return balance;
	}

	public void deposit(double amount) {
		AccountEntry entry = new AccountEntry(amount, "deposit", "", "");
		entryList.add(entry);
	}

	public void withdraw(double amount) {
		AccountEntry entry = new AccountEntry(-amount, "withdraw", "", "");
		entryList.add(entry);
	}

	private void addEntry(AccountEntry entry) {
		entryList.add(entry);
	}

	public void transferFunds(Account toAccount, double amount, String description) {
		AccountEntry fromEntry = new AccountEntry(-amount, description, toAccount.getAccountNumber(),
				toAccount.getCustomer().getName());
		AccountEntry toEntry = new AccountEntry(amount, description, toAccount.getAccountNumber(),
				toAccount.getCustomer().getName());
		
		entryList.add(fromEntry);
		
		toAccount.addEntry(toEntry);
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

		return interestService.calInterest(balance);
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

	public void addInterest() {
		AccountEntry entry = new AccountEntry(this.performInterest(), "interest", "", "");
		entryList.add(entry);
	}

	public void notifyChanged() {
		this.setChanged();
		this.notifyObservers();
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
