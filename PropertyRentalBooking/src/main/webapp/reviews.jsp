<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reviews</title>
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

        /* Reviews Container */
        .reviews-container {
            background-color: #fff;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
        }

        .reviews-container h2 {
            font-size: 2rem;
            margin-bottom: 1.5rem;
            color: #333;
            text-align: center;
        }

        /* Review Form */
        .review-form {
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

        .form-group input[type="number"],
        .form-group textarea {
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1rem;
            transition: border-color 0.3s ease;
        }

        .form-group input[type="number"]:focus,
        .form-group textarea:focus {
            border-color: #007BFF;
            outline: none;
        }

        .form-group textarea {
            resize: vertical;
            min-height: 100px;
        }

        /* Submit Button */
        .btn-submit {
            padding: 0.75rem;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn-submit:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="reviews-container">
        <h2>Leave a Review</h2>
        <form action="reviews/add" method="post" class="review-form">
            <input type="hidden" name="propertyId" value="${param.propertyId}">

            <div class="form-group">
                <label>Rating:</label>
                <input type="number" name="rating" min="1" max="5" required>
            </div>

            <div class="form-group">
                <label>Comment:</label>
                <textarea name="comment" required></textarea>
            </div>

            <button type="submit" class="btn-submit">Submit Review</button>
        </form>
    </div>
</body>
</html>