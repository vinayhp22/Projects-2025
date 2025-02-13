package com.skyllx.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skyllx.expense.dao.UserDao;
import com.skyllx.expense.model.User;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AuthController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login"; // Loads login.jsp
	}

	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session) {
		User user = userDao.findUserByUsername(username);
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			session.setAttribute("loggedUser", user);
			return "expenses"; // Redirect to expenses page after login
		}
		return "redirect:/login?error=true"; // Redirect back to login on failure
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // Clear session on logout
		return "redirect:/login";
	}
}
