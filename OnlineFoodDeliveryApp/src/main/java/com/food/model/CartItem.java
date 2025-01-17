package com.food.model;

public class CartItem {
    private int itemId;
    private int resturantId;
    private String name;
    private int quantity;
    private double price;

    public CartItem(int itemId, int resturantId, String name, int quantity, double price) {
        this.itemId = itemId;
        this.resturantId = resturantId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getResturantId() {
        return resturantId;
    }

    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }

    @Override
    public String toString() {
        return "CartItem [itemId=" + itemId + ", resturantId=" + resturantId + ", name=" + name + ", quantity="
                + quantity + ", price=" + price + "]";
    }



}