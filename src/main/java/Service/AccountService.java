package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*; //(Connection, PreparedStatement, ResultSet)

public class AccountDAO {
	
	//helper method, used by primary methods to look up an account and see if it exists
	public Account getAccountByUsername(String username) {
		Connection connection = ConnectionUtil.getConnection();
		
		try {
			String sql = "SELECT * FROM account WHERE username = ?";
			
			//debugging:
	    	//System.out.println("dao file, try block, username: " + username);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			//debugging:
	    	//System.out.println("dao file, try block, ResultSet: " + rs);
			
			if(!rs.next()) {
				return null;
			}
			
			while(rs.next()) {
				Account account = new Account(rs.getInt("account_id"),
						rs.getString("username"), rs.getString("password"));
				
				return account;
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	//end of helper method
	
	//helper method
	public Account insertAccount(Account account) {
		Connection connection = ConnectionUtil.getConnection();
		
		try {
			String sql = "INSERT INTO account (username, password) values (?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.setString(2, account.getPassword());
			
			preparedStatement.executeUpdate();
			
			ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
			//debugging:
			//System.out.println("dao, account obj: " + account);
			System.out.println("dao, pkResultSet: " + pkeyResultSet);
			if(!pkeyResultSet.next()) {
				System.out.println("dao, pkResultSet.next(): false");
			}
			
			if(pkeyResultSet.next()) {
				int generatedId = (int) pkeyResultSet.getLong(1);
				//debugging:
				System.out.println("dao, generatedId: " + generatedId);
				
				return new Account(generatedId, account.getUsername(), account.getPassword());
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	//
		
}
