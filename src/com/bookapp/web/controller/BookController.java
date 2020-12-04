package com.bookapp.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import com.bookapp.model.dao.Book;
import com.bookapp.model.dao.BookNotFoundException;
import com.bookapp.model.dao.BookType;
import com.bookapp.model.service.BookService;

@Controller
public class BookController {
	private BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	

	@GetMapping("allbooks")
	public ModelAndView allBooks(ModelAndView mv) {
		List<Book> books = bookService.getAllBooks();
		mv.addObject("books", books);
		mv.setViewName("showAllBooks");
		return mv;
	}
	
	//add book
	@GetMapping("addbook")
	public String addBookGet(ModelMap map) {

		// we are binding a form bean (a pojo that is used to collect data from ui) with the form 
		map.addAttribute("book", new Book());
		return "addbook";
	}
	
	@PostMapping("addbook")
	public String addBookPost(@Valid @ModelAttribute(name="book") Book book, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			//hey spring if there is a validation error go back to addbook.jsp
			return "addbook";
		} else {
		if(book.getId() == 0) {
			bookService.addBook(book);
		} else {
			bookService.updateBook(book.getId(), book);
		}
		return "redirect:/allbooks";
		}
	}
	
	//delete
	@GetMapping("deletebook")
	public String delBook(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		bookService.deleteBook(id);
		return "redirect:/allbooks";
	}
	
	//updatebook
	@GetMapping("updatebook")
	public String updateBookGet(HttpServletRequest req, ModelMap map) {
		int id = Integer.parseInt(req.getParameter("id"));
		Book book = bookService.getBookById(id);
		map.addAttribute("book", book);
		return "updatebookpage";
	}
	
	
	//hey spring mvc plz execute this method for every req and put this return values of this
	//method into req scope in a variable named "booktypes"
	@ModelAttribute(value = "booktypes")
	public BookType[] bookType() {
		return BookType.values();
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ModelAndView bookNotFoundExHandler(HttpServletRequest req, Exception ex ) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("booknotfound");
		mv.addObject("error", ex.getMessage());
		return mv;
	}
}
