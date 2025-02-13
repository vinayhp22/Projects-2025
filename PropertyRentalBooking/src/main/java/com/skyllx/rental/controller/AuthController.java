package com.skyllx.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.skyllx.rental.model.User;
import com.skyllx.rental.service.UserService;

@Controller
public class AuthController {
	
    @Autowired
    private UserService userService;

    
    @GetMapping("/register")
    public String registerUser() {
    	return "register";
	}
    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login.jsp";
    }
}
