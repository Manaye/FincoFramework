package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import framework.Account;
import framework.AccountEntry;
import framework.Customer;

public class BillingReport {
    private Collection<Account> creditCaredAccount;
    private LocalDate previousMonth ;

    public BillingReport(Collection<Account> accounts) {
        this.creditCaredAccount=accounts;
        getPrevious();
    }

    private void getPrevious() {
        // TODO Auto-generated method stub
        Date d= new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, 1);
        d=c.getTime();
        c.add(Calendar.MONTH, -1);
        int month = c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DATE)-1;
        previousMonth= LocalDate.of(year, month, day);

    }

    public String getBillingReport(){
        //List<String>reports = new ArrayList<>();
        String report = "";
        for(Account creditCard :creditCaredAccount) {
            
            Collection<AccountEntry> acountentrylist= creditCard.getEntryList();
            double previousBalance= calpreviousBalance(creditCard);
            double totalCreadits =  calTotal("paid",acountentrylist);
            double totalCharges = calTotalCharge(creditCard);
            double newBalance = calNewBlance(creditCard,previousBalance,totalCreadits,totalCharges);
            double totalDue = calTotalDue(creditCard,newBalance);
            System.out.println("Billing");
            Customer customer =  creditCard.getCustomer();
            report = "Name"+customer.getName()+"address"+customer.getAddress().toString()+
                    "creditcared"+creditCard.getAccountNumber()+"type"+
                    creditCard.getAccountType()+"\n"+"previous"+ previousBalance+"\n total creadit "+totalCreadits+"\n"+"newBlance"+newBalance+"\n"+"totalDue"+totalDue;

           // System.out.println("report");
            //reports.add(report);
        }
        return report;
    }

    private double calpreviousBalance(Account creditCard) {
        double previousBalance = creditCard.getBalance();
        Collection<AccountEntry> acountentrylist = creditCard.getEntryList();

        for(AccountEntry accountEntry:acountentrylist) {
            if(DateToLocalDate(accountEntry.getDate()).compareTo(previousMonth)>0) {
                previousBalance -=accountEntry.getAmount();
            }
        }
        return previousBalance;
    }

    private double calTotalCharge(Account creditCard) {
        double totalCharge;
        Collection<AccountEntry> acountentrylist = creditCard.getEntryList();

        totalCharge = acountentrylist.stream()
                .filter(accountEntry -> accountEntry.getDescription().equals("withdraw"))
                .filter(accountEntry -> DateToLocalDate(accountEntry.getDate()).getMonthValue() == LocalDate.now().getMonthValue())
                .filter(accountEntry -> Period.between(DateToLocalDate(accountEntry.getDate()), LocalDate.now()).getMonths()==0)
                .map(accountEntry -> accountEntry.getAmount())
                .reduce(0.0, (num1, num2) -> num1 + num2);

        return totalCharge;
    }

    public double calNewBlance(Account creditCard, double previousBalance, double totalCreadit, double totalCharges) {
//      //formula:  new balance = previous balance – total credits + total charges + MI * (previous balance – total credits)
        double newBalance = previousBalance - totalCreadit + totalCharges
                            + creditCard.getMonthlyInterest()*(previousBalance -totalCreadit);
        return newBalance;
    }

    private double calTotalDue(Account creditCard, double newBalance) {
        //formula: MP * new balance
        double totalDue = creditCard.getMinimumPayInterest()*newBalance;
        return totalDue;
    }

    private double calTotal(String string, Collection<AccountEntry> acountentrylist) {
        double total =0;
        for(AccountEntry accountEntry:acountentrylist) {
            if(DateToLocalDate(accountEntry.getDate()).compareTo(previousMonth)>0) {
                total +=accountEntry.getAmount();
            }
        }
        return total;
    }

    public LocalDate DateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
