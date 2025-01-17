package com.food.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/online_food_delivery"; // Replace with your DB URL
    private static final String USER = "root"; // Replace with your DB username
    private static final String PASSWORD = "root"; // Replace with your DB password

    static {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL JDBC driver");
        }
    }

    /**
     * Gets a connection to the database.
     *
     * @return Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Closes the provided connection.
     *
     * @param conn the connection to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
