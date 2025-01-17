<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.*, com.food.util.DBConnectionUtil" %>

<%
    int menuId = Integer.parseInt(request.getParameter("id"));
    String itemName = "";
    String description = "";
    double price = 0.0;
    boolean isAvailable = true;
    String imagePath = "";

    try (Connection conn = DBConnectionUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Menu WHERE MenuID = ?")) {
        pstmt.setInt(1, menuId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                itemName = rs.getString("ItemName");
                description = rs.getString("Description");
                price = rs.getDouble("Price");
                isAvailable = rs.getBoolean("IsAvailable");
                imagePath = rs.getString("ImagePath");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Menu Item</title>
</head>
<body>
    <h2>Edit Menu Item</h2>
    <form action="updateMenu" method="post" enctype="multipart/form-data">
        <input type="hidden" name="menuId" value="<%= menuId %>">
        <label>Item Name:</label>
        <input type="text" name="itemName" value="<%= itemName %>" required><br>
        <label>Description:</label>
        <textarea name="description" required><%= description %></textarea><br>
        <label>Price:</label>
        <input type="number" step="0.01" name="price" value="<%= price %>" required><br>
        <label>Available:</label>
        <input type="checkbox" name="isAvailable" <%= isAvailable ? "checked" : "" %>><br>
        <label>Current Image:</label>
        <img src="<%= imagePath %>" alt="Menu Image" height="100"><br>
        <label>Upload New Image:</label>
        <input type="file" name="image"><br>
        <button type="submit">Update</button>
    </form>
</body>
</html>
