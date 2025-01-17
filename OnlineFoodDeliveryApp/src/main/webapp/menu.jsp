<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.food.model.Menu"%>
<%@ page
	import="java.util.List, com.food.model.Restaurant, com.food.model.User"%>

<html>
<head>
<title>Menu</title>
    <link rel="stylesheet" href="css/menu.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
	<header>
		<h2>Welcome to Online Food Delivery</h2>
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
	<h1>Menu</h1>
	<div class="menu-container">
		<%
		List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
		if (menuList != null) {
			for (Menu menuItem : menuList) {
		%>
		<div class="menu-item">
			<h3><%=menuItem.getItemName()%></h3>
			<p><%=menuItem.getDescription()%></p>
			<p>
				Price:
				<%=menuItem.getPrice()%></p>
			<%
            if (loggedInUser != null) {
            %>
                <form action="cart" method="post">
                    <input type="hidden" name="itemId" value="<%=menuItem.getMenuId()%>">
                        Quantity: <input type="number" name="quantity" value="1" min="1" class="quantity-input">
                        <input type="submit" value="Add to Cart" class="add-to-cart-btn">
                        <input type="hidden" name="action" value="add">
                </form>
            <%
            } else {
            %>
                <form action="login.jsp">
                    <input type="hidden" name="itemId" value="<%=menuItem.getMenuId()%>">
                        Quantity: <input type="number" name="quantity" value="1" min="1" class="quantity-input">
                        <input type="submit" value="Add to Cart" class="add-to-cart-btn">
                        <input type="hidden" name="action" value="add">
                </form>
            <%
            }
            %>
		</div>
		<%
		}
		} else {
		%>
		<p>No menu items available.</p>
		<%
		}
		%>
	</div>
    <footer>
        <p>&copy; 2025 SkyllX Online Food Delivery</p>
    </footer>
</body>
</html>