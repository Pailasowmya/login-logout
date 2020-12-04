package com.bookapp.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bookapp.model.dao.account.Account;
import com.bookapp.model.service.account.AccountService;

@Controller
public class LoginController {

	private AccountService accountService;

	@Autowired
	public LoginController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	@GetMapping("/")
	public String home() {
		return "show";
	}

	@GetMapping("signup")
	public String signupGet(ModelMap map) {
		map.addAttribute("account", new Account());
		return "signup";
	}

	@PostMapping("signup")
	public String signupPost(@ModelAttribute("account") Account account) {
		accountService.addAccount(account);
		return "redirect:/";
	}

	@GetMapping("login")
	public String loginGet(ModelMap map) {
		map.addAttribute("account", new Account());
		return "login";
	}

	@PostMapping("login")
	public String loginPost(@ModelAttribute("account") Account account, HttpServletRequest req) {

		String username = account.getUsername();
		String password = account.getPassword();
		boolean isValid = accountService.verifyDetails(username, password);
		if (isValid) {
			Account account1 = accountService.getAccount(username, password);

			HttpSession session = req.getSession();
			session.setAttribute("account", account1);

			return "redirect:/allbooks";
		} else {
			return "redirect:/login";
		}
	}
}
