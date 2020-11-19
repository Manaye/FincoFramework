package observers;

import java.util.Observable;
import java.util.Observer;

public class SMSSender implements Observer {
	public void update(Observable o, Object arg) {
		System.out.println("Send SMS update :" + o.toString());
	}
}
