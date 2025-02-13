package com.skyllx.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skyllx.rental.model.User;
import com.skyllx.rental.service.BookingService;
import com.skyllx.rental.service.PropertyService;
import com.skyllx.rental.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PropertyService propertyService;
    
    @Autowired
    private BookingService bookingService;

    @GetMapping("/login")
    public String login() {
    	return "login";
	}
    
    @PostMapping("/login")
    public String login(@RequestParam ("username") String username, 
                        @RequestParam("password") String password, 
                        HttpSession session, 
                        RedirectAttributes redirectAttributes, Model model) {
        User user = userService.authenticateUser(username, password);

        if (user != null) {
            session.setAttribute("loggedInUser", user);
            if(user.getRole().equals("OWNER")) {
            	model.addAttribute("properties", propertyService.getAllProperties());            	
            } else if (user.getRole().equals("RENTER")) {
            	model.addAttribute("bookings", bookingService.getUserBookings(user.getId()));    
            }
            return "dashboard"; // Redirect to dashboard after login
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid email or password!");
            return "redirect:/login.jsp"; // Redirect back to login page
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clears session data
    	return "redirect:/login.jsp";
	}
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
    	User user = (User) session.getAttribute("loggedInUser");
        if(user!=null && user.getRole().equals("OWNER")) {
        	model.addAttribute("properties", propertyService.getAllProperties());            	
        } else if (user!=null && user.getRole().equals("RENTER")) {
        	model.addAttribute("bookings", bookingService.getUserBookings(user.getId()));    
        }
        return "dashboard"; // Redirect to dashboard after login
	}
    
}
