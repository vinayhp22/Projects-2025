package com.food.daoimpl;

import com.food.dao.UserDAO;
import com.food.model.User;
import com.food.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO User (Username, Password, Email, Address, Role) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword()); // Ensure this is hashed
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getRole());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public User getUser(int userId) {
        String sql = "SELECT * FROM User WHERE UserID = ?";
        User user = null;
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = extractUserFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE User SET Username = ?, Password = ?, Email = ?, Address = ?, Role = ? WHERE UserID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword()); // Ensure this is hashed
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getRole());
            pstmt.setInt(6, user.getUserId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public void deleteUser(int userId) {
        String sql = "DELETE FROM User WHERE UserID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = extractUserFromResultSet(rs);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return users;
    }


    @Override
    public User getUserByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM User WHERE Username = ?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("UserID"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password")); // Ensure passwords are hashed in production
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                user.setRole(rs.getString("Role"));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return user;
    }



    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("UserID"));
        user.setUsername(rs.getString("Username"));
        user.setPassword(rs.getString("Password"));
        user.setEmail(rs.getString("Email"));
        user.setAddress(rs.getString("Address"));
        user.setRole(rs.getString("Role"));
        return user;
    }
}