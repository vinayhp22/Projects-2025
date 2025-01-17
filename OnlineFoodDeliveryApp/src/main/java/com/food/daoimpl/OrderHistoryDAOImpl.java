package com.food.daoimpl;

import com.food.dao.OrderHistoryDAO;
import com.food.model.OrderHistory;
import com.food.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {

    @Override
    public void addOrderHistory(OrderHistory orderHistory) {
        String sql = "INSERT INTO OrderHistory (UserID, OrderID, OrderDate, TotalAmount, Status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderHistory.getUserId());
            pstmt.setInt(2, orderHistory.getOrderId());
            pstmt.setTimestamp(3, new java.sql.Timestamp(orderHistory.getOrderDate().getTime()));
            pstmt.setDouble(4, orderHistory.getTotalAmount());
            pstmt.setString(5, orderHistory.getStatus());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public OrderHistory getOrderHistory(int orderHistoryId) {
        String sql = "SELECT * FROM OrderHistory WHERE OrderHistoryID = ?";
        OrderHistory orderHistory = null;
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderHistoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    orderHistory = extractOrderHistoryFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return orderHistory;
    }

    @Override
    public void updateOrderHistory(OrderHistory orderHistory) {
        String sql = "UPDATE OrderHistory SET UserID = ?, OrderID = ?, OrderDate = ?, TotalAmount = ?, Status = ? WHERE OrderHistoryID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderHistory.getUserId());
            pstmt.setInt(2, orderHistory.getOrderId());
            pstmt.setTimestamp(3, new java.sql.Timestamp(orderHistory.getOrderDate().getTime()));
            pstmt.setDouble(4, orderHistory.getTotalAmount());
            pstmt.setString(5, orderHistory.getStatus());
            pstmt.setInt(6, orderHistory.getOrderHistoryId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public void deleteOrderHistory(int orderHistoryId) {
        String sql = "DELETE FROM OrderHistory WHERE OrderHistoryID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderHistoryId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
    }

    @Override
    public List<OrderHistory> getOrderHistoriesByUser(int userId) {
        List<OrderHistory> orderHistories = new ArrayList<>();
        String sql = "SELECT * FROM OrderHistory WHERE UserID = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OrderHistory orderHistory = extractOrderHistoryFromResultSet(rs);
                    orderHistories.add(orderHistory);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with more robust error handling
        }
        return orderHistories;
    }

    private OrderHistory extractOrderHistoryFromResultSet(ResultSet rs) throws SQLException {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderHistoryId(rs.getInt("OrderHistoryID"));
        orderHistory.setUserId(rs.getInt("UserID"));
        orderHistory.setOrderId(rs.getInt("OrderID"));
        orderHistory.setOrderDate(rs.getTimestamp("OrderDate"));
        orderHistory.setTotalAmount(rs.getDouble("TotalAmount"));
        orderHistory.setStatus(rs.getString("Status"));
        return orderHistory;
    }
}