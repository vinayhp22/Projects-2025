<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page
	import="java.util.List, com.food.model.Restaurant, com.food.model.User"%>
<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
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
				<span>Dear, <%=loggedInUser.getUsername()%>! Happy Shopping
				</span>
				<a href="cart">View Cart</a>
				<!-- <a href="orderHistory">View Order History</a> --!>
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
    <h1>Order History</h1>

    <c:if test="${empty orderHistory}">
        <p>You have no order history to display.</p>
    </c:if>

    <c:if test="${not empty orderHistory}">
        <table border="1" cellpadding="10" cellspacing="0">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Total Price</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orderHistory}">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.totalAmount}</td>
                        <td>${order.status}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
	<footer>
		<p>&copy; 2025 SkyllX Online Food Delivery</p>
	</footer>
</body>
</html>
