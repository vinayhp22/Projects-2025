<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Available Properties</title>
</head>
<body>
    <header>
        <h1>Available Properties</h1>
        <nav>
            <a href="../dashboard.jsp">Dashboard</a>
            <a href="add-property.jsp">Add New Property</a>
            <a href="logout">Logout</a>
        </nav>
    </header>

    <section>
        <table border="1">
            <tr>
                <th>Image</th>
                <th>Title</th>
                <th>Description</th>
                <th>Price per Night ($)</th>
                <th>Action</th>
            </tr>
            <c:forEach var="property" items="${properties}">
                <tr>
                    <td>
                        <c:if test="${not empty property.image}">
                            <img src="image?id=${property.id}" width="100" height="80" alt="Property Image">
                        </c:if>
                        <c:if test="${empty property.image}">
                            <span>No Image</span>
                        </c:if>
                    </td>
                    <td>${property.title}</td>
                    <td>${property.description}</td>
                    <td>${property.pricePerNight}</td>
                    <td>
                        <a href="property-details.jsp?id=${property.id}">View</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </section>

    <footer>
        <p>&copy; 2025 Property Rental Booking</p>
    </footer>
</body>
</html>
