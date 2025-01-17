<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="java.util.List, com.food.model.Restaurant, com.food.model.User"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Online Food Delivery</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/home.css">
</head>
<body>
	<header>
		<h1>Welcome to Online Food Delivery</h1>
		<nav>
			<a href="home">Home</a>
			<%
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			if (loggedInUser != null) {
			%>
				<span>Welcome, <%=loggedInUser.getUsername()%>!
				</span>
				<a href="cart">View Cart</a>
				<a href="orderHistory">View Order History</a>
				<a href="logout">Logout</a>
			<%
			} else {
			%>
				<a href="login.jsp">Login</a>
				<a href="register.jsp">Register</a>
			<%
			}
			%>

			<!-- Additional links for Profile, Admin Dashboard etc. based on user role -->
		</nav>
	</header>

	<h2>&nbsp;&nbsp;&nbsp; Featured Restaurants</h2>
	<section class="restaurant-list">

		<%-- Assuming restaurantList is the attribute name containing the list of restaurants --%>
		<%
		List<Restaurant> restaurantList = (List<Restaurant>) request.getAttribute("restaurantList");
		if (restaurantList != null) {
			for (Restaurant restaurant : restaurantList) {
		%>
		<div class="restaurant">
			<%-- Use appropriate attributes from your Restaurant model --%>
			<img src="images/<%=restaurant.getImagePath()%>"
				alt="Image of <%=restaurant.getName()%>">
			<h3><%=restaurant.getName()%></h3>
			<p><%=restaurant.getCuisineType()%>
				-
				<%=restaurant.getDeliveryTime()%>
				mins
			</p>
			<a href="menu?restaurantId=<%=restaurant.getRestaurantId()%>">View
				Menu</a>
		</div>
		<%
		}
		} else {
		%>
		<p>No restaurants available at the moment.</p>
		<%
		}
		%>
	</section>

	<footer>
		<p>&copy; 2025 SkyllX Online Food Delivery</p>
	</footer>
</body>
</html>