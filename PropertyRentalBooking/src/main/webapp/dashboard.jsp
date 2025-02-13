<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.skyllx.rental.model.User"%>
<%
User user = (User) session.getAttribute("loggedInUser");
%>
<!DOCTYPE html>
<html>
<head>
<title>Dashboard</title>
<style type="text/css">
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

/* Dashboard Container */
.dashboard-container {
	max-width: 1200px;
	margin: 0 auto;
	padding: 2rem;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

/* Header */
header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 2rem;
}

header h2 {
	font-size: 2rem;
	color: #007BFF;
}

.logout-link {
	color: #007BFF;
	text-decoration: none;
	font-weight: bold;
	transition: color 0.3s ease;
}

.logout-link:hover {
	color: #0056b3;
}

/* Tables */
table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 1.5rem;
}

table th, table td {
	padding: 1rem;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

table th {
	background-color: #007BFF;
	color: #fff;
	font-weight: bold;
}

table tr:hover {
	background-color: #f1f1f1;
}

.property-image {
	width: 100px;
	height: 80px;
	border-radius: 5px;
	object-fit: cover;
}

.no-image {
	font-style: italic;
	color: #888;
}

.action-link {
	color: #007BFF;
	text-decoration: none;
	font-weight: bold;
	transition: color 0.3s ease;
}

.action-link:hover {
	color: #0056b3;
}

/* Links */
.add-property-link, .browse-link {
	display: inline-block;
	padding: 0.75rem 1.5rem;
	background-color: #007BFF;
	color: #fff;
	text-decoration: none;
	border-radius: 5px;
	transition: background-color 0.3s ease;
}

.add-property-link:hover, .browse-link:hover {
	background-color: #0056b3;
}
</style>

</head>
<body>
	<div class="dashboard-container">
		<header>
			<h2>Welcome, ${loggedInUser.username}!</h2>
			<nav>
				<a href="logout" class="logout-link">Logout</a>
			</nav>
		</header>

		<c:choose>
			<c:when test="${loggedInUser.role eq 'OWNER'}">
				<section class="owner-section">
					<h3>Your Properties</h3>
					<table>
						<thead>
							<tr>
								<th>Sl. No</th>
								<th>Image</th>
								<th>Title</th>
								<th>Description</th>
								<th>Price per Night ($)</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="property" items="${properties}">
								<tr>
									<!-- Serial Number starts from 1 -->
									<td>${status.index + 1}</td>
									<td><c:choose>
											<c:when test="${not empty property.image}">
												<img src="properties/image?id=${property.id}"
													alt="Property Image" class="property-image">
											</c:when>
											<c:otherwise>
												<span class="no-image">No Image</span>
											</c:otherwise>
										</c:choose></td>
									<td>${property.title}</td>
									<td>${property.description}</td>
									<td>${property.pricePerNight}</td>
									<td><a href="properties/edit?id=${property.id}"
										class="action-link">Edit</a> <a
										href="properties/delete?id=${property.id}" class="action-link">Delete</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<br> <a href="add-property.jsp" class="add-property-link">Add
						New Property</a>
				</section>
			</c:when>

			<c:when test="${loggedInUser.role eq 'RENTER'}">
				<section class="renter-section">
					<h3>Your Bookings</h3>
					<table>
						<thead>
							<tr>
								<th>Sl. No</th>
								<th>Property</th>
								<th>Owner</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="booking" items="${bookings}">
								<tr>
									<td>${booking.id}</td>
									<td>${booking.property.title}</td>
									<td>${booking.property.owner.username}</td>
									<td>${booking.startDate}</td>
									<td>${booking.endDate}</td>
									<td><a
										href="reviews.jsp?propertyId=${booking.property.id}"
										class="action-link">Review</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<br> <a href="properties/list" class="browse-link">Browse
						Properties</a>
				</section>
			</c:when>
		</c:choose>
	</div>
</body>
</html>