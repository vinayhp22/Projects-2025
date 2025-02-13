<%@page import="com.skyllx.rental.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
HttpSession sessionObj = request.getSession(false);
User loggedInUser = (sessionObj != null) ? (User) sessionObj.getAttribute("loggedInUser") : null;
%>
<!DOCTYPE html>
<html>
<head>
<title>Available Properties</title>
<style type="text/css">
/* General Reset */
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: 'Arial', sans-serif;
	background-color: #;
	color: #333;
	line-height: 1.6;
}

/* Header Styles */
header {
	background-color: #007BFF;
	color: #fff;
	padding: 1rem 0;
	text-align: center;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

header h1 {
	font-size: 2.5rem;
	margin-bottom: 0.5rem;
}

header nav {
	margin-top: 1rem;
}

.nav-link {
	color: #fff;
	text-decoration: none;
	margin: 0 1rem;
	font-weight: bold;
	transition: color 0.3s ease;
}

.nav-link:hover {
	color: #ffdd57;
}

/* Property List Section */
.property-list {
	padding: 2rem;
	max-width: 1200px;
	margin: 0 auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	background-color: #fff;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
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

.view-link {
	color: #007BFF;
	text-decoration: none;
	font-weight: bold;
	transition: color 0.3s ease;
}

.view-link:hover {
	color: #0056b3;
}

/* Footer Styles */
footer {
	text-align: center;
	padding: 1rem 0;
	background-color: #007BFF;
	color: #fff;
	margin-top: 2rem;
	box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>
	<header>
		<h1>Available Properties</h1>
		<nav>
			<%
			if (loggedInUser != null) {
			%>
			<a href="../dashboard" class="nav-link">Dashboard</a>
			<%
			}
			if (loggedInUser != null && loggedInUser.getRole().equals("OWNER")) {
			%>
			<a href="../add-property.jsp" class="nav-link">Add New Property</a>
			<%
			}
			if (loggedInUser != null) {
			%>
			<a href="../logout" class="nav-link">Logout</a>
			<%
			}
			if (loggedInUser == null) {
			%>
			<a href="../index.jsp" class="nav-link">Home</a>
			<%
			}
			%>
		</nav>
	</header>

	<section class="property-list">
		<table>
			<thead>
				<tr>
					<th>Image</th>
					<th>Title</th>
					<th>Description</th>
					<th>Price per Night ($)</th>
					<%
					if (loggedInUser != null && !loggedInUser.getRole().equals("OWNER")) {
					%>
					<th>Action</th>
					<%
					}
					%>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="property" items="${properties}">
					<tr>
						<td><c:if test="${not empty property.image}">
								<img src="image?id=${property.id}" alt="Property Image"
									class="property-image">
							</c:if> <c:if test="${empty property.image}">
								<span class="no-image">No Image</span>
							</c:if></td>
						<td>${property.title}</td>
						<td>${property.description}</td>
						<td>${property.pricePerNight}</td>
						<%
						if (loggedInUser != null && !loggedInUser.getRole().equals("OWNER")) {
						%>
						<td><a href="property-details?id=${property.id}"
							class="view-link">View</a></td>
					</tr>
					<%
					}
					%>
				</c:forEach>
			</tbody>
		</table>
	</section>

	<footer>
		<p>&copy; 2025 Property Rental Booking</p>
	</footer>
</body>
</html>