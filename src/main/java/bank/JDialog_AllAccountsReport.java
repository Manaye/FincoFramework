package bank;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTable; 
import javax.swing.table.DefaultTableModel;

import controller.AccountService;
import model.Account;
import model.AccountEntry;
 
public class JDialog_AllAccountsReport { 

    JFrame f; 
    
    private AccountService accountService;
    JDialog_AllAccountsReport(AccountService accountService) 
    { 
    
    	this.accountService = accountService;
        f = new JFrame(); 
  
        
        f.setTitle("Account Report"); 
  
        
         JTable account_Table = new JTable();
        Collection<Account> accounts = new ArrayList<>();
        accounts = accountService.getAllAccounts();
        
        Object[] columns = {"Acc Number", "Customer Name", "AccountType","Balance"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columns);
            
              for(Account acc : accounts) {
            	  model.addRow(new String[] {
            			  acc.getAccountNumber(),
            			  acc.getCustomer().getName(),
            	          acc.getAccountType().toString(),
            	          String.valueOf(acc.getBalance())});
            	     System.out.println("Testxxx:" + acc.getBalance());
            	    for(AccountEntry entry : acc.getEntryList()) {
            	    	model.addRow(new String[] {
            	    			entry.getDescription(),
            	    			String.valueOf(entry.getAmount())});
            	    			
            	    }
            	    
              }
              
             
              
            
        account_Table.setModel(model);
     
        account_Table.setBounds(30, 40, 200, 300); 
  
        JScrollPane sp = new JScrollPane(account_Table); 
        f.add(sp);  
        f.setSize(500, 200);  
        f.setVisible(true); 
    } 
}
