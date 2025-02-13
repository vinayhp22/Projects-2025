package com.skyllx.expense.controller;

import com.skyllx.expense.dao.UserDao;
import com.skyllx.expense.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RegisterController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/register")
	public String showRegisterPage() {
		return "register"; // Loads register.jsp
	}

	@PostMapping("/register")
	public void registerUser(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletResponse response) throws IOException {

		if (userDao.findUserByUsername(username) != null) {
			response.sendRedirect("register.jsp?error=true");
			return;
		}

		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(passwordEncoder.encode(password));
		newUser.setRole("USER");

		userDao.saveUser(newUser);
		response.sendRedirect("login");
	}
}
