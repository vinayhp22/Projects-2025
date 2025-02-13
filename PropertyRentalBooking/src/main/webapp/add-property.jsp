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
    <style>
        /* General Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            color: #333;
            line-height: 1.6;
        }

        /* Header Styles */
        header {
            background-color: #007BFF;
            color: #fff;
            padding: 1rem 0;
            text-align: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        header h1 {
            font-size: 2.5rem;
            margin-bottom: 0.5rem;
        }

        header nav {
            margin-top: 1rem;
        }

        header nav a {
            color: #fff;
            text-decoration: none;
            margin: 0 1rem;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        header nav a:hover {
            color: #ffdd57;
        }

        /* Form Section */
        section {
            max-width: 600px;
            margin: 2rem auto;
            padding: 2rem;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }

        label {
            font-weight: bold;
            margin-bottom: 0.5rem;
            color: #555;
        }

        input[type="text"],
        input[type="number"],
        textarea,
        input[type="file"] {
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1rem;
            transition: border-color 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="number"]:focus,
        textarea:focus,
        input[type="file"]:focus {
            border-color: #007BFF;
            outline: none;
        }

        textarea {
            resize: vertical;
            min-height: 100px;
        }

        button[type="submit"] {
            padding: 0.75rem;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        /* Footer Styles */
        footer {
            text-align: center;
            padding: 1rem 0;
            background-color: #007BFF;
            color: #fff;
            margin-top: 2rem;
            box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
    <header>
        <h1>Add a New Property</h1>
        <nav>
            <a href="dashboard">Dashboard</a>
            <a href="properties/list">View Properties</a>
            <a href="logout">Logout</a>
        </nav>
    </header>

    <section>
        <form action="properties/add" method="post" enctype="multipart/form-data">
            <label for="title">Property Title:</label>
            <input type="text" id="title" name="title" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>

            <label for="price">Price per Night ($):</label>
            <input type="number" id="price" name="price" step="0.01" required>

            <label for="image">Upload Property Image:</label>
            <input type="file" id="image" name="image" accept="image/*">

            <button type="submit">Add Property</button>
        </form>
    </section>

    <footer>
        <p>&copy; 2025 Property Rental Booking</p>
    </footer>
</body>
</html>