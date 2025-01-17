package com.food.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    private int restaurantId;
    private String name;
    private String cuisineType;
    private int deliveryTime;
    private String address;
    private int adminUserId;
    private double rating;
    private boolean isActive;
    private String imagePath;

    public Restaurant(int restaurantId, String name, String cuisineType, int deliveryTime, String address,
                      int adminUserId, double rating, boolean isActive) {
        super();
        this.restaurantId = restaurantId;
        this.name = name;
        this.cuisineType = cuisineType;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.adminUserId = adminUserId;
        this.rating = rating;
        this.isActive = isActive;
    }

    // Getter and setter for isActive
    public boolean getIsActive() {
        return isActive;
    }

}