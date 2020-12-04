package com.bookapp.model.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookapp.model.dao.account.Account;
import com.bookapp.model.dao.account.AccountDao;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService{

	private AccountDao accountDao;
	
	@Autowired
	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public Account getAccount(String username, String password) {
		return accountDao.getAccount(username, password);
	}

	@Override
	public void addAccount(Account account) {
		accountDao.addAccount(account);
	}

	@Override
	public boolean verifyDetails(String username, String password) {
		Account account = accountDao.getAccount(username, password);
		if(account.getUsername().equals(username) && account.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
		
	}

}
