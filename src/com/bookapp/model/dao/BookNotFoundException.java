package com.bookapp.model.dao;

public class BookNotFoundException extends RuntimeException{
	public BookNotFoundException(String msg) {
		super(msg);
	}
}