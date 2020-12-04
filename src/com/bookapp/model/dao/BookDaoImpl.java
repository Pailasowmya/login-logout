package com.bookapp.model.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao{

	private SessionFactory factory;
	
	@Autowired
	public BookDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	//this method is used inside my dao layer....session is open by spring and im just asking for it.
	private Session getSession() {
		return factory.getCurrentSession();
	}


	@Override
	public List<Book> getAllBooks() {
		List<Book> books = getSession().createQuery("select b from Book b").list();
		return books;
	}


	@Override
	public void addBook(Book book) {
		
		 getSession().save(book);
	}


	@Override
	public void deleteBook(int id) {
		Book bookToBeDeleted = getBookById(id);
		getSession().delete(bookToBeDeleted);
	}


	@Override
	public void updateBook(int id, Book book) {
		Book bookToBeUpdated = getBookById(id);
		bookToBeUpdated.setPrice(book.getPrice());
		getSession().update(bookToBeUpdated);
		
	}


	@Override
	public Book getBookById(int id) {
		Book book = getSession().get(Book.class, id);
		return book;
	}
}