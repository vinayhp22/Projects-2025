package com.skyllx.rental.controller;

import com.skyllx.rental.model.User;
import com.skyllx.rental.service.BookingService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/bookings")
@Slf4j
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public String bookProperty(@RequestParam ("propertyId") Long propertyId,
                               @RequestParam ("startDate") String startDate,
                               @RequestParam ("endDate") String endDate,
                               HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login.jsp";
        }

        boolean booked = bookingService.bookProperty(propertyId, user, LocalDate.parse(startDate), LocalDate.parse(endDate));
        if (booked) {
        	log.info("Booked the Property");
            return "redirect:/bookings/success.jsp";
        } else {
        	log.info("Error in Booking the Property");
            return "redirect:/bookings/fail.jsp";
        }
    }
}
