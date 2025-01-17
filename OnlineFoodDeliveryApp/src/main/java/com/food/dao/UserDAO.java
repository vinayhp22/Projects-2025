package com.food.dao;

import com.food.model.User;
import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUser(int userId);
    void updateUser(User user);
    void deleteUser(int userId);
    List<User> getAllUsers();
    User getUserByUsername(String username);
}