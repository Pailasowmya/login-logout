package com.bookapp.model.dao.account;

public interface AccountDao {
	
	public Account getAccount(String username, String password);
	public void addAccount(Account account);

}
