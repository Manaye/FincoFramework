package controller;

import java.util.Collection;

import model.Account;
import model.CreditCardType;

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
