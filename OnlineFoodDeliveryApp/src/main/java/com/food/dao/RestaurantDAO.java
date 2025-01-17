package com.food.dao;

import com.food.model.Restaurant;
import java.util.List;

public interface RestaurantDAO {
    void addRestaurant(Restaurant restaurant);
    Restaurant getRestaurantById(int restaurantId);
    void updateRestaurant(Restaurant restaurant);
    void deleteRestaurant(int restaurantId);
    List<Restaurant> getAllRestaurants();
}