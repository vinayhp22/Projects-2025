<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Property Details</title>
</head>
<body>
    <h2>${property.title}</h2>
    <p>${property.description}</p>
    <p>Price per night: ${property.pricePerNight}</p>
    <a href="bookings.jsp?propertyId=${property.id}">Book Now</a>
</body>
</html>
