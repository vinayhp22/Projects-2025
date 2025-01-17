package com.food.daoimpl;

import com.food.dao.OrderDAO;
import com.food.model.Order;
import com.food.model.consts.PaymentMethod;
import com.food.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public void addOrder(Order order) {
        String sql = "INSERT INTO OrderTable (UserID, RestaurantID, OrderDate, TotalAmount, Status, PaymentMethod) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getRestaurantId());
            pstmt.setTimestamp(3, new java.sql.Timestamp(order.getOrderDate().getTime()));
            pstmt.setDouble(4, order.getTotalAmount());
            pstmt.setString(5, order.getStatus());
            pstmt.setString(6, order.getPaymentMethod().name());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public Order getOrder(int orderId) {
        String sql = "SELECT * FROM OrderTable WHERE OrderID = ?";
        Order order = null;
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    order = extractOrderFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        String sql = "UPDATE OrderTable SET UserID = ?, RestaurantID = ?, OrderDate = ?, TotalAmount = ?, Status = ?, PaymentMethod = ? WHERE OrderID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getRestaurantId());
            pstmt.setTimestamp(3, new java.sql.Timestamp(order.getOrderDate().getTime()));
            pstmt.setDouble(4, order.getTotalAmount());
            pstmt.setString(5, order.getStatus());
            pstmt.setString(6, order.getPaymentMethod().name());
            pstmt.setInt(7, order.getOrderId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM OrderTable WHERE OrderID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public List<Order> getAllOrdersByUser(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM OrderTable WHERE UserID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Order order = extractOrderFromResultSet(rs);
                    orders.add(order);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return orders;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM OrderTable";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Order order = extractOrderFromResultSet(rs);
                    orders.add(order);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return orders;    }

    private Order extractOrderFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("OrderID"));
        order.setUserId(rs.getInt("UserID"));
        order.setRestaurantId(rs.getInt("RestaurantID"));
        order.setOrderDate(rs.getTimestamp("OrderDate"));
        order.setTotalAmount(rs.getDouble("TotalAmount"));
        order.setStatus(rs.getString("Status"));
        order.setPaymentMethod(PaymentMethod.valueOf(rs.getString("PaymentMethod")));
        return order;
    }
}