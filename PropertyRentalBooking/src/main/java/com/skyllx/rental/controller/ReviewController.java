package com.skyllx.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skyllx.rental.model.Review;
import com.skyllx.rental.model.User;
import com.skyllx.rental.service.BookingService;
import com.skyllx.rental.service.ReviewService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/reviews")
@Slf4j
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private BookingService bookingService;

	@PostMapping("/add")
	public String addReview(@RequestParam("propertyId") Long propertyId, @RequestParam("rating") int rating,
			@RequestParam("comment") String comment, Model model, HttpSession session) {
		
		User user = (User) session.getAttribute("loggedInUser");
		log.info("User Name: "+user.getUsername());
		
		Review review = reviewService.addReview(propertyId, rating, comment);
		if (review != null) {
        	model.addAttribute("bookings", bookingService.getUserBookings(user.getId()));    
			return "dashboard";
		} else {
			return "redirect:/reviews.jsp";
		}
	}
}
