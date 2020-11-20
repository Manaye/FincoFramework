package observers;

import java.util.Observable;
import java.util.Observer;

import framework.AccountEntry;

public class Logger implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		//AccountEntry entry = (AccountEntry)arg;
		System.out.println("Log an Update: " + arg );
	}

}
