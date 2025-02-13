<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Property Details</title>
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

        /* Property Details Container */
        .details-container {
            background-color: #fff;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
        }

        .details-container h2 {
            font-size: 2rem;
            margin-bottom: 1rem;
            color: #333;
            text-align: center;
        }

        .details-container p {
            font-size: 1.1rem;
            margin-bottom: 1rem;
            color: #555;
        }

        .details-container p strong {
            color: #007BFF;
        }

        /* Booking Form */
        .booking-form {
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

        .form-group input[type="date"] {
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1rem;
            transition: border-color 0.3s ease;
        }

        .form-group input[type="date"]:focus {
            border-color: #007BFF;
            outline: none;
        }

        /* Submit Button */
        .btn-book {
            padding: 0.75rem;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn-book:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="details-container">
        <h2>${property.title}</h2>
        <p>${property.description}</p>
        <p><strong>Price per night:</strong> $${property.pricePerNight}</p>

        <form action="../bookings/create" method="post" class="booking-form">
            <input type="hidden" name="propertyId" value="${param.id}">

            <div class="form-group">
                <label>Start Date:</label>
                <input type="date" name="startDate" required>
            </div>

            <div class="form-group">
                <label>End Date:</label>
                <input type="date" name="endDate" required>
            </div>

            <button type="submit" class="btn-book">Book Now</button>
        </form>
    </div>
</body>
</html>