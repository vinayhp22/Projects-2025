package com.food.daoimpl;

import com.food.dao.RestaurantDAO;
import com.food.model.Restaurant;
import com.food.util.DBConnectionUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAOImpl implements RestaurantDAO {

    @Override
    public void addRestaurant(Restaurant restaurant) {
        String sql = "INSERT INTO Restaurant (Name, CuisineType, DeliveryTime, Address, AdminUserID, Rating, IsActive) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getCuisineType());
            pstmt.setInt(3, restaurant.getDeliveryTime());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setInt(5, restaurant.getAdminUserId());
            pstmt.setDouble(6, restaurant.getRating());
            pstmt.setBoolean(7, restaurant.getIsActive());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        String sql = "SELECT * FROM Restaurant WHERE RestaurantID = ?";
        Restaurant restaurant = null;
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, restaurantId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    restaurant = extractRestaurantFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return restaurant;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        String sql = "UPDATE Restaurant SET Name = ?, CuisineType = ?, DeliveryTime = ?, Address = ?, AdminUserID = ?, Rating = ?, IsActive = ?, ImagePath = ? WHERE RestaurantID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getCuisineType());
            pstmt.setInt(3, restaurant.getDeliveryTime());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setInt(5, restaurant.getAdminUserId());
            pstmt.setDouble(6, restaurant.getRating());
            pstmt.setBoolean(7, restaurant.getIsActive());
            pstmt.setString(8, restaurant.getImagePath());
            pstmt.setInt(9, restaurant.getRestaurantId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        String sql = "DELETE FROM Restaurant WHERE RestaurantID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, restaurantId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM Restaurant";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Restaurant restaurant = extractRestaurantFromResultSet(rs);
                restaurants.add(restaurant);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return restaurants;
    }

    private Restaurant extractRestaurantFromResultSet(ResultSet rs) throws SQLException {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(rs.getInt("RestaurantID"));
        restaurant.setName(rs.getString("Name"));
        restaurant.setCuisineType(rs.getString("CuisineType"));
        restaurant.setDeliveryTime(rs.getInt("DeliveryTime"));
        restaurant.setAddress(rs.getString("Address"));
        restaurant.setAdminUserId(rs.getInt("AdminUserID"));
        restaurant.setRating(rs.getDouble("Rating"));
        restaurant.setActive(rs.getBoolean("IsActive"));
        restaurant.setImagePath(rs.getString("ImagePath"));
        return restaurant;
    }
}