package observers;

import java.util.Observable;
import java.util.Observer;

public class EmailSender implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("    Withdrawal amount is more than 400 ");
        System.out.println("withdraweal amount is more than 400 999 10");
    }
}
