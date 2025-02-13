package com.skyllx.rental.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skyllx.rental.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> { }