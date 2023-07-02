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
//	public Account login(Account account) {
//		//REQ: successful if the username and password provided
//		//in the request body JSON match a real account existing on the database
//		if() {
//			
//		}
//		
//		return null;
//	}
	//
	
	//user registration
	public Account userRegistration(Account account) {
		//REQ: successful if the username is not blank,
		//password is at least 4 chars long,
		//and an account with that username DNE already
		
		if(/*!account.username.isEmpty() &&*/ account.username.length() >= 4
				&& accountDAO.getAccountByUsername(account.getUsername()) == null) {
			return accountDAO.insertAccount(account);
		}
		
		return null;
	}
	//
    
}
