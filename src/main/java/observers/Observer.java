package observers;

import model.Account;

public interface Observer {
	void update(Account account, String transaction);
}
