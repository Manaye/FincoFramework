package observers;

import model.Account;
import model.AccountEntry;

import java.util.Observable;
import java.util.Observer;

public class EmailSender implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        //TODO send email
        if (o instanceof Account){
            Account acc = (Account)o;
            AccountEntry entry = (AccountEntry) arg;
            if (entry.getDescription().equals("deposit")) {
                try {
                    System.out.println(acc.getCustomer().getEmail());
                    System.out.println("The deposit amount " + entry.getAmount() + " is greater than $400");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (entry.getDescription().equals("withdraw")){
                try {
                    System.out.println(acc.getCustomer().getEmail());
                    System.out.println("The withdraw amount " + -entry.getAmount() + " is greater than $400");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
