<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, com.food.util.DBConnectionUtil" %>
<%
    int restaurantId = Integer.parseInt(request.getParameter("id"));
    String name = "";
    String cuisineType = "";
    String address = "";
    int deliveryTime = 0;
    boolean isActive = true;
    String imagePath = "";

    try (Connection conn = DBConnectionUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Restaurant WHERE RestaurantID = ?")) {
        pstmt.setInt(1, restaurantId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                name = rs.getString("Name");
                cuisineType = rs.getString("CuisineType");
                address = rs.getString("Address");
                deliveryTime = rs.getInt("DeliveryTime");
                isActive = rs.getBoolean("IsActive");
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
    <title>Edit Restaurant</title>
</head>
<body>
    <h2>Edit Restaurant</h2>
    <form action="updateRestaurant" method="post" enctype="multipart/form-data">
        <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
        <label>Name:</label>
        <input type="text" name="name" value="<%= name %>" required><br>
        <label>Cuisine Type:</label>
        <input type="text" name="cuisineType" value="<%= cuisineType %>" required><br>
        <label>Address:</label>
        <textarea name="address" required><%= address %></textarea><br>
        <label>Delivery Time (minutes):</label>
        <input type="number" name="deliveryTime" value="<%= deliveryTime %>" required><br>
        <label>Active:</label>
        <input type="checkbox" name="isActive" <%= isActive ? "checked" : "" %>><br>
        <label>Current Image:</label>
        <img src="images/<%= imagePath %>" alt="Restaurant Image" height="100"><br>
        <label>Upload New Image:</label>
        <input type="file" name="image"><br>
        <button type="submit">Update</button>
    </form>
</body>
</html>
