package Service;

import Model.Account;

import DAO.AccountDAO;

public class AccountService {
	AccountDAO accountDAO;
	
	public AccountService() {
		accountDAO = new AccountDAO();
	}
	
	public AccountService(AccountDAO a) {
		accountDAO = a;
	}
	
	//account login
	public Account login(Account account) {
		//REQ: successful if the username and password provided
		//in the request body JSON match a real account existing on the database
		if(accountDAO.getAccountByUsername(account.getUsername()) != null
				&& accountDAO.getPassword(account) != null) {
//			System.out.println("account: " + account);
//			System.out.println("getAccount: " + accountDAO.getAccountByUsername(account.getUsername()));
			return accountDAO.getAccountByUsername(account.getUsername());
		}
		
		return null;
	}
	//
	
	//user registration
	public Account userRegistration(Account account) {
		//REQ: successful if the username is not blank,
		//password is at least 4 chars long,
		//and an account with that username DNE already
		
		if(!account.username.isEmpty() && account.password.length() >= 4
				&& accountDAO.getAccountByUsername(account.getUsername()) == null) {
			return accountDAO.insertAccount(account);
		}
		
		return null;
	}
	//
    
}
