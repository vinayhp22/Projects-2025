package com.skyllx.rental.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyllx.rental.dao.BookingRepository;
import com.skyllx.rental.dao.PropertyRepository;
import com.skyllx.rental.dao.UserRepository;
import com.skyllx.rental.model.Booking;
import com.skyllx.rental.model.Property;
import com.skyllx.rental.model.User;

@Service
public class BookingService {
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private UserRepository userRepository;

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	// Book a property
    public boolean bookProperty(Long propertyId, User user, LocalDate startDate, LocalDate endDate) {
        if (isAvailable(propertyId, startDate, endDate)) {
            Property property = propertyRepository.findById(propertyId).orElse(null);
            if (property != null) {
                Booking booking = new Booking();
                booking.setProperty(property);
                booking.setUser(user);
                booking.setStartDate(startDate);
                booking.setEndDate(endDate);
                bookingRepository.save(booking);
                return true;
            }
        }
        return false;
    }

	// Check if the property is available for booking
    public boolean isAvailable(Long propertyId, LocalDate startDate, LocalDate endDate) {
        List<Booking> bookings = bookingRepository.findByPropertyId(propertyId);
        for (Booking booking : bookings) {
            if (!(endDate.isBefore(booking.getStartDate()) || startDate.isAfter(booking.getEndDate()))) {
                return false; // Not available
            }
        }
        return true;
    }

	// Get all bookings for a user
	public List<Booking> getUserBookings(Long userId) {
		return bookingRepository.findAll().stream().filter(booking -> booking.getUser().getId().equals(userId))
				.toList();
	}
}
