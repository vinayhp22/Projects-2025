<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.skyllx.rental.model.Property" %>

<%
    Property property = (Property) request.getAttribute("property");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Delete Property</title>
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
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* Delete Property Container */
        .delete-container {
            background-color: #fff;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }

        .delete-container h2 {
            font-size: 2rem;
            margin-bottom: 1.5rem;
            color: #333;
        }

        .delete-container p {
            font-size: 1.1rem;
            margin-bottom: 1.5rem;
            color: #555;
        }

        .delete-container p b {
            color: #007BFF;
        }

        /* Delete Form */
        .delete-form {
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }

        .delete-form input[type="submit"] {
            padding: 0.75rem;
            background-color: #ff4d4d;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .delete-form input[type="submit"]:hover {
            background-color: #cc0000;
        }

        /* Cancel Link */
        .cancel-link {
            display: inline-block;
            margin-top: 1rem;
            color: #007BFF;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        .cancel-link:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="delete-container">
        <h2>Delete Property</h2>
        <p>Are you sure you want to delete the property <b>${property.title}</b>?</p>

        <form action="remove-property" method="post" class="delete-form">
            <input type="hidden" name="id" value="${property.id}">
            <input type="submit" value="Delete">
        </form>

        <br>
        <a href="../dashboard" class="cancel-link">Cancel</a>
    </div>
</body>
</html>