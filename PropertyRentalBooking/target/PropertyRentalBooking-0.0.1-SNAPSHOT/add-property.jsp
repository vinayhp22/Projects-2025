<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.skyllx.rental.model.User" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    HttpSession sessionObj = request.getSession(false);
    User loggedInUser = (sessionObj != null) ? (User) sessionObj.getAttribute("loggedInUser") : null;

    if (loggedInUser == null) {
        response.sendRedirect("login.jsp"); // Redirect to login if not logged in
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Property</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file -->
</head>
<body>
    <header>
        <h1>Add a New Property</h1>
        <nav>
            <a href="dashboard.jsp">Dashboard</a>
            <a href="property-list.jsp">View Properties</a>
            <a href="logout">Logout</a>
        </nav>
    </header>

    <section>
        <form action="properties/add" method="post" enctype="multipart/form-data">
            <label for="title">Property Title:</label>
            <input type="text" id="title" name="title" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>

            <label for="location">Location:</label>
            <input type="text" id="location" name="location" required>

            <label for="price">Price per Night ($):</label>
            <input type="number" id="price" name="price" step="0.01" required>

            <label for="image">Upload Property Image:</label>
            <input type="file" id="image" name="image" accept="image/*" required>

            <button type="submit">Add Property</button>
        </form>
    </section>

    <footer>
        <p>&copy; 2025 Property Rental Booking</p>
    </footer>
</body>
</html>
