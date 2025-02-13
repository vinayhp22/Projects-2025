<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.skyllx.rental.model.User"%>
<%
User user = (User) session.getAttribute("loggedInUser");
%>
<html>
<head>
<title>Dashboard</title>
</head>
<body>

	<h2>Welcome, ${loggedInUser.username}!</h2>

	<%
	if (user.getRole().equals("OWNER")) {
	%>
	<h3>Your Properties</h3>
	<table border="1">
		<tr>
			<th>Image</th>
			<th>Title</th>
			<th>Description</th>
			<th>Price per Night ($)</th>
			<th>Action</th>
		</tr>
		<c:forEach var="property" items="${properties}">
			<tr>
				<td><c:choose>
						<c:when test="${not empty property.image}">
							<img src="properties/image?id=${property.id}" width="100"
								height="80" alt="Property Image">
						</c:when>
						<c:otherwise>
							<span>No Image</span>
						</c:otherwise>
					</c:choose></td>
				<td>${property.title}</td>
				<td>${property.description}</td>
				<td>${property.pricePerNight}</td>
				<td><a href="property-details.jsp?id=${property.id}">View</a> |
					<a href="edit-property.jsp?id=${property.id}">Edit</a> | <a
					href="delete-property.jsp?id=${property.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

	<br>
	<a href="add-property.jsp">Add New Property</a>

	<h3>Your Bookings</h3>
	<table border="1">
		<tr>
			<th>Property</th>
			<th>Renter</th>
			<th>Start Date</th>
			<th>End Date</th>
		</tr>
		<c:forEach var="booking" items="${bookings}">
			<tr>
				<td>${booking.property.title}</td>
				<td>${booking.user.username}</td>
				<td>${booking.startDate}</td>
				<td>${booking.endDate}</td>
			</tr>
		</c:forEach>
	</table>

	<%
	} else if (user.getRole().equals("RENTER")) {
	%>

	<h3>Your Bookings</h3>
	<table border="1">
		<tr>
			<th>Property</th>
			<th>Owner</th>
			<th>Start Date</th>
			<th>End Date</th>
			<th>Action</th>
		</tr>
		<c:forEach var="booking" items="${bookings}">
			<tr>
				<td>${booking.property.title}</td>
				<td>${booking.property.owner.username}</td>
				<td>${booking.startDate}</td>
				<td>${booking.endDate}</td>
				<td><a href="review.jsp?propertyId=${booking.property.id}">Leave
						Review</a></td>
			</tr>
		</c:forEach>
	</table>

	<br>
	<a href="property-list.jsp">Browse Properties</a>

	<%
	}
	%>

	<br>
	<br>
	<a href="logout">Logout</a>

</body>
</html>
