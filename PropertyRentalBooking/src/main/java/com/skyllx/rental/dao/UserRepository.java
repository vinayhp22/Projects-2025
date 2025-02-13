package com.skyllx.rental.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skyllx.rental.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { 
	// Define a method to find a user by email and password
    User findByEmailAndPassword(String email, String password);
    User findByUsernameAndPassword(String username, String password);
}