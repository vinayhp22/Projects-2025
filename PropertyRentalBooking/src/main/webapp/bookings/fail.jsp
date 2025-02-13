<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Booking Failed</title>
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

        /* Booking Failed Container */
        .booking-failed-container {
            background-color: #fff;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
            text-align: center;
        }

        .booking-failed-container h2 {
            font-size: 2rem;
            margin-bottom: 1rem;
            color: #ff4d4d;
        }

        .booking-failed-container p {
            font-size: 1.1rem;
            margin-bottom: 1.5rem;
            color: #555;
        }

        .booking-failed-container a {
            display: inline-block;
            margin: 0 0.5rem;
            padding: 0.75rem 1.5rem;
            background-color: #007BFF;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .booking-failed-container a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="booking-failed-container">
        <h2>Booking Failed</h2>
        <p>Sorry, there was an issue with your booking. The property may no longer be available for the selected dates.</p>

        <a href="../property-list.jsp">Try Again</a>
        <a href="../dashboard.jsp">Go to Dashboard</a>
    </div>
</body>
</html>