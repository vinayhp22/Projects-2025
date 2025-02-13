package com.skyllx.rental.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyllx.rental.dao.PropertyRepository;
import com.skyllx.rental.dao.ReviewRepository;
import com.skyllx.rental.model.Property;
import com.skyllx.rental.model.Review;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    // Add a review for a property
    public Review addReview(Long propertyId, int rating, String comment) {
        Property property = propertyRepository.findById(propertyId).orElse(null);
        if (property != null) {
            Review review = new Review();
            review.setProperty(property);
            review.setRating(rating);
            review.setComment(comment);
            return reviewRepository.save(review);
        }
        return null;
    }

    // Get reviews for a specific property
    public List<Review> getReviewsForProperty(Long propertyId) {
        return reviewRepository.findAll()
                .stream()
                .filter(review -> review.getProperty().getId().equals(propertyId))
                .toList();
    }
}
