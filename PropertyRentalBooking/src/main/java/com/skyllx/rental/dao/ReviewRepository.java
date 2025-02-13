package com.skyllx.rental.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skyllx.rental.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> { }