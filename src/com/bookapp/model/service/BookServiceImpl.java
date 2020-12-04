package com.bookapp.model.service;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookapp.model.dao.Book;
import com.bookapp.model.dao.BookDao;

@Service(value = "bookService")
@Transactional // it is using aop (around advice) to declarative tx mgmt
public class BookServiceImpl implements BookService{

	private BookDao bookDao;
	
	@Autowired
	public BookServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public List<Book> getAllBooks() {
		
		return bookDao.getAllBooks();
	}

	@Override
	public void addBook(Book book) {
		bookDao.addBook(book);
		
	}

	@Override
	public void deleteBook(int id) {
		bookDao.deleteBook(id);
		
	}

	@Override
	public void updateBook(int id, Book book) {
		bookDao.updateBook(id, book);
		
	}

	@Override
	public Book getBookById(int id) {
		return bookDao.getBookById(id);
	}


}
