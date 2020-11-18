package edu.mum.cs.cs525.labs.exercises.framework.dao;

import edu.mum.cs.cs525.labs.exercises.framework.model.Account;

import java.util.Collection;

public interface AccountDAO {
	void saveAccount(Account account);
	void updateAccount(Account account);
	Account loadAccount(String accountnumber);
	Collection<Account> getAccounts();
}
