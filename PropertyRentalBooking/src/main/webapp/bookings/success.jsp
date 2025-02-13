<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Booking Successful</title>
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

        /* Booking Successful Container */
        .booking-successful-container {
            background-color: #fff;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
            text-align: center;
        }

        .booking-successful-container h2 {
            font-size: 2rem;
            margin-bottom: 1rem;
            color: #28a745;
        }

        .booking-successful-container p {
            font-size: 1.1rem;
            margin-bottom: 1.5rem;
            color: #555;
        }

        .booking-successful-container a {
            display: inline-block;
            padding: 0.75rem 1.5rem;
            background-color: #007BFF;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .booking-successful-container a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="booking-successful-container">
        <h2>Booking Confirmed!</h2>
        <p>Your booking has been successfully confirmed. Enjoy your stay!</p>

        <a href="../dashboard.jsp">Go to Dashboard</a>
    </div>
</body>
</html>