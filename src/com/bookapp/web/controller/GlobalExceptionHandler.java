package com.bookapp.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

//aop, throws advice
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handle404() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("404");
		return mv;
	}

	@ExceptionHandler(DataAccessException.class)
	public ModelAndView handleDbError(HttpServletRequest req, Exception ex) {
		logger.info(ex.getMessage());
		logger.info(req.getRequestURI());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dberror");
		return mv;
	}
}