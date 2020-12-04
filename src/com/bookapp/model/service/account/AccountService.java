package com.bookapp.model.service.account;

import com.bookapp.model.dao.account.Account;

public interface AccountService {
	public Account getAccount(String username, String password);
	public void addAccount(Account account);
	public boolean verifyDetails(String username, String password);
}