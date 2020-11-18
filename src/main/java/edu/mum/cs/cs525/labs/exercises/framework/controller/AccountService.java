package edu.mum.cs.cs525.labs.exercises.framework.controller;

import edu.mum.cs.cs525.labs.exercises.framework.model.CreditCardType;
import edu.mum.cs.cs525.labs.exercises.framework.model.Account;

import java.util.Collection;

public interface AccountService {
    Account createAccount(String accountNumber, String customerName, CreditCardType accountType);
//    Account createAccount(String accountNumber, String customerName, CreditCardType creditCardType);
    Account getAccount(String accountNumber);
    Collection<Account> getAllAccounts();
    void deposit (String accountNumber, double amount);
    void withdraw (String accountNumber, double amount);
    //void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description);
    void addInterest();
    void getBillingReport(); //TODO
}
