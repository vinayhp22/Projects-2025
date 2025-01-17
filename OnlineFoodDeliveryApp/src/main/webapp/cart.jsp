<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.Map, com.food.model.Cart, com.food.model.CartItem"%>
<%@ page
	import="java.util.List, com.food.model.Restaurant, com.food.model.User"%>
<html>
<head>
<title>Shopping Cart</title>
<link rel="stylesheet" href="css/cart.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/home.css">
</head>
<body>
	<header>
		<h1>Online Food Delivery</h1>
		<nav>
			<a href="home">Home</a>
			<%
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			if (loggedInUser != null) {
			%>
				<span>Welcome, <%=loggedInUser.getUsername()%>!
				</span>
				<!-- <a href="cart">View Cart</a> --!>
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
	<h1>Your Shopping Cart</h1>
	<div class="cart-items">
		<%
		//HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null && !cart.getItems().isEmpty()) {
			for (CartItem item : cart.getItems().values()) {
		%>
		<div class="cart-item">
			<h3><%=item.getName()%></h3>
			<p>
				&#x20B9;
				<%=item.getPrice()%></p>
			<form action="cart" method="post">
				<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
				<label>Quantity: <input type="number" name="quantity"
					value="<%=item.getQuantity()%>" min="1">
				</label> <input type="submit" name="action" value="update"
					class="update-btn"> <input type="submit" name="action"
					value="remove" class="remove-btn">
			</form>
		</div>

		<%
		}

		} else {
		%>
		<p>Your cart is empty.</p>
		<%
		}
		%>


		<%-- Add More Items Button --%>
		<a href="menu?restaurantId=<%=session.getAttribute("resturantId") %>" class="btn add-more-items-btn">Add More Items</a>

		<%-- Proceed to Checkout Button --%>
		<%
		if (session.getAttribute("cart") != null) {
		%>
		<form action="checkout.jsp" method="post">
			<input type="submit" value="Proceed to Checkout"
				class="btn proceed-to-checkout-btn">
		</form>
		<%
		}
		%>
	</div>
    <footer>
        <p>&copy; 2025 SkyllX Online Food Delivery</p>
    </footer>
</body>
</html>