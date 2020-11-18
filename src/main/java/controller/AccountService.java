package controller;

import model.Account;
import model.CreditCardType;

import java.time.LocalDate;
import java.util.Collection;

public interface AccountService {

    Account createAccount(String accountNumber, String customerName,
                          String email, String street,
                          String city, String state, String zip,
                          String creditCardNumber, LocalDate expiredDate,
                          CreditCardType accountType);
    Account getAccount(String accountNumber);
    Collection<Account> getAllAccounts();
    void deposit (String accountNumber, double amount);
    void withdraw (String accountNumber, double amount);
    //void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description);
    void addInterest();
    String getBillingReport(); //TODO
}
