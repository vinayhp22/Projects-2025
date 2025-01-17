package com.food.dao;

import com.food.model.OrderItem;
import java.util.List;

public interface OrderItemDAO {
    void addOrderItem(OrderItem orderItem);
    OrderItem getOrderItem(int orderItemId);
    void updateOrderItem(OrderItem orderItem);
    void deleteOrderItem(int orderItemId);
    List<OrderItem> getOrderItemsByOrder(int orderId);
}