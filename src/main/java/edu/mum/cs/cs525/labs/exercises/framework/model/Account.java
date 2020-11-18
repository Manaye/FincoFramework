package edu.mum.cs.cs525.labs.exercises.framework.model;

import edu.mum.cs.cs525.labs.exercises.framework.controller.interest_strategy.InterestService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;

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
}