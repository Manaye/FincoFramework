package framework;

import java.util.Collection;



public interface AccountDAO {
	void saveAccount(Account account);
	void updateAccount(Account account);
	Account loadAccount(String accountnumber);
	Collection<Account> getAccounts();
	//hiwi u have to learn more git hub
	
}
