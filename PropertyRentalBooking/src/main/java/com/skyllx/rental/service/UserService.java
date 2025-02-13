package com.skyllx.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyllx.rental.dao.UserRepository;
import com.skyllx.rental.model.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        userRepository.save(user);
    }
    
    public User authenticateUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password); // or userRepository.authenticateUser(email, password)
    }

}
