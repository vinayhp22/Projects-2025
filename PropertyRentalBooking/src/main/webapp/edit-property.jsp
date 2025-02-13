<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.skyllx.rental.model.Property" %>

<%
    Property property = (Property) request.getAttribute("property");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Property</title>
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

        /* Edit Property Container */
        .edit-container {
            background-color: #fff;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
        }

        .edit-container h2 {
            font-size: 2rem;
            margin-bottom: 1.5rem;
            color: #333;
            text-align: center;
        }

        /* Edit Form */
        .edit-form {
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
        }

        .form-group label {
            font-weight: bold;
            color: #555;
        }

        .form-group input[type="text"],
        .form-group input[type="number"],
        .form-group textarea,
        .form-group input[type="file"] {
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1rem;
            transition: border-color 0.3s ease;
        }

        .form-group input[type="text"]:focus,
        .form-group input[type="number"]:focus,
        .form-group textarea:focus,
        .form-group input[type="file"]:focus {
            border-color: #007BFF;
            outline: none;
        }

        .form-group textarea {
            resize: vertical;
            min-height: 100px;
        }

        /* Submit Button */
        .btn-update {
            padding: 0.75rem;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn-update:hover {
            background-color: #0056b3;
        }

        /* Back Link */
        .back-link {
            display: inline-block;
            margin-top: 1rem;
            color: #007BFF;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        .back-link:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="edit-container">
        <h2>Edit Property</h2>
        <form action="update-property" method="post" class="edit-form" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${property.id}">

            <div class="form-group">
                <label>Title:</label>
                <input type="text" name="title" value="${property.title}" required>
            </div>

            <div class="form-group">
                <label>Description:</label>
                <textarea name="description" required>${property.description}</textarea>
            </div>

            <div class="form-group">
                <label>Price per Night ($):</label>
                <input type="number" name="pricePerNight" value="${property.pricePerNight}" required>
            </div>

            <div class="form-group">
                <label>Upload Image:</label>
                <input type="file" name="image">
            </div>

            <button type="submit" class="btn-update">Update Property</button>
        </form>

        <br>
        <a href="../login" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>