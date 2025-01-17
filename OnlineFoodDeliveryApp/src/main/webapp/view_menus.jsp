<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Menus</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Menu List</h1>

    <a href="admin?action=dashboard">Back to Dashboard</a>
    <a href="add_menu.jsp">Add New Menu Item</a>

    <form method="get" action="viewMenus">
        <label for="restaurantId">Filter by Restaurant:</label>
        <input type="text" id="restaurantId" name="restaurantId" placeholder="Enter Restaurant ID">
        <button type="submit">Filter</button>
    </form>

    <table border="1">
        <thead>
            <tr>
                <th>Menu ID</th>
                <th>Restaurant ID</th>
                <th>Item Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Available</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="menu" items="${menus}">
                <tr>
                    <td>${menu.menuId}</td>
                    <td>${menu.restaurantId}</td>
                    <td>${menu.itemName}</td>
                    <td>${menu.description}</td>
                    <td>${menu.price}</td>
                    <td>${menu.isAvailable ? "Yes" : "No"}</td>
                    <td>
                        <a href="edit_menu.jsp?id=${menu.menuId}">Edit</a>
                        <a href="delete_menu?id=${menu.menuId}" onclick="return confirm('Are you sure you want to delete this menu item?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
