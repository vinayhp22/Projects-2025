package com.food.dao;

import com.food.model.Order;
import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);
    Order getOrder(int orderId);
    void updateOrder(Order order);
    void deleteOrder(int orderId);
    List<Order> getAllOrdersByUser(int userId);
    List<Order> getAllOrders();
}