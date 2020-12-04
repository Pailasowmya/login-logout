package com.bookapp.model.dao.account;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao{

	
	private SessionFactory factory;
	
	@Autowired
	public AccountDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	//this method is used inside my dao layer....session is open by spring and im just asking for it.
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Account getAccount(String username, String password) {
		Query query = getSession().createQuery("from Account where username=:username1 and password=:password1");
		query.setParameter("username1", username);
		query.setParameter("password1", password);
		Account account = (Account) query.getSingleResult();
		return account;
	}

	@Override
	public void addAccount(Account account) {
		getSession().save(account);
	}

}
