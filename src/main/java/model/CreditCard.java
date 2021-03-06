package model;

import java.time.LocalDate;

import framework.Account;
import framework.AccountClass;

public class CreditCard extends Account {
    private String creditCardNumber;
    private LocalDate expireDate;

    public CreditCard(String accountNumber, String creditCardNumber, LocalDate expireDate, AccountClass accountClass) {
        super(accountNumber, accountClass);
        this.creditCardNumber = creditCardNumber;
        this.expireDate = expireDate;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }
}
