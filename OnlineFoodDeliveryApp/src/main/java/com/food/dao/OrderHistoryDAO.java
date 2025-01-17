package com.food.dao;

import com.food.model.OrderHistory;
import java.util.List;

public interface OrderHistoryDAO {
    void addOrderHistory(OrderHistory orderHistory);
    OrderHistory getOrderHistory(int orderHistoryId);
    void updateOrderHistory(OrderHistory orderHistory);
    void deleteOrderHistory(int orderHistoryId);
    List<OrderHistory> getOrderHistoriesByUser(int userId);
}