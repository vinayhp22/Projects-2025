<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Restaurants</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Restaurant List</h1>

    <a href="admin?action=dashboard">Back to Dashboard</a>
    <a href="add_restaurant.jsp">Add New Restaurant</a>

    <table border="1">
        <thead>
            <tr>
                <th>Restaurant ID</th>
                <th>Name</th>
                <th>Cuisine Type</th>
                <th>Address</th>
                <th>Rating</th>
                <th>Active</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="restaurant" items="${restaurants}">
                <tr>
                    <td>${restaurant.restaurantId}</td>
                    <td>${restaurant.name}</td>
                    <td>${restaurant.cuisineType}</td>
                    <td>${restaurant.address}</td>
                    <td>${restaurant.rating}</td>
                    <td>${restaurant.isActive ? "Yes" : "No"}</td>
                    <td>
                        <a href="viewRestaurants?action=edit&id=${restaurant.restaurantId}">Edit</a>
                        <a href="viewRestaurants?action=delete&id=${restaurant.restaurantId}"
                        onclick="return confirm('Are you sure you want to delete this restaurant?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
