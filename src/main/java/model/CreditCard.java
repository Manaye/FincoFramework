package model;

import java.time.LocalDate;

public class CreditCard extends Account {
    private String creditCardNumber;
    private LocalDate expireDate;

    public CreditCard(String accountNumber, String creditCardNumber, LocalDate expireDate) {
        super(accountNumber);
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
