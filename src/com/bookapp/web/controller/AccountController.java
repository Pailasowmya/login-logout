package com.bookapp.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookapp.model.dao.account.Account;
import com.bookapp.model.dao.account.AccountType;
import com.bookapp.model.service.account.AccountService;

@Controller
public class AccountController {
	
	private AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping("addaccount")
	public String addAccountGet(ModelMap map) {
		map.addAttribute("account", new Account());
		return "addaccount";
	}
	
	@PostMapping("addaccount")
	public String signupPost(@ModelAttribute("account") Account account, HttpServletRequest req) {
		Integer id = Integer.parseInt(req.getParameter("id"));
		if(id == 0) {
			accountService.addAccount(account);
		}
		return "redirect:/allbooks";
	}
	
	
	@ModelAttribute("accountType")
	public AccountType[] accountType() {
		return AccountType.values();
	}
	
}
