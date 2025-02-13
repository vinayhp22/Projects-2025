<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Booking</title>
</head>
<body>
    <h2>Book Property</h2>
    <form action="/bookings/create" method="post">
        <input type="hidden" name="propertyId" value="${propertyId}">
        Start Date: <input type="date" name="startDate"><br>
        End Date: <input type="date" name="endDate"><br>
        <input type="submit" value="Book Now">
    </form>
</body>
</html>
