package com.food.daoimpl;

import com.food.dao.OrderItemDAO;
import com.food.model.OrderItem;
import com.food.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO OrderItem (OrderID, MenuID, Quantity, ItemTotal) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setDouble(4, orderItem.getItemTotal());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        String sql = "SELECT * FROM OrderItem WHERE OrderItemID = ?";
        OrderItem orderItem = null;
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderItemId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    orderItem = extractOrderItemFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return orderItem;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        String sql = "UPDATE OrderItem SET OrderID = ?, MenuID = ?, Quantity = ?, ItemTotal = ? WHERE OrderItemID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setDouble(4, orderItem.getItemTotal());
            pstmt.setInt(5, orderItem.getOrderItemId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        String sql = "DELETE FROM OrderItem WHERE OrderItemID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderItemId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public List<OrderItem> getOrderItemsByOrder(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM OrderItem WHERE OrderID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OrderItem orderItem = extractOrderItemFromResultSet(rs);
                    orderItems.add(orderItem);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return orderItems;
    }

    private OrderItem extractOrderItemFromResultSet(ResultSet rs) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(rs.getInt("OrderItemID"));
        orderItem.setOrderId(rs.getInt("OrderID"));
        orderItem.setMenuId(rs.getInt("MenuID"));
        orderItem.setQuantity(rs.getInt("Quantity"));
        orderItem.setItemTotal(rs.getDouble("ItemTotal"));
        return orderItem;
    }
}