<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome to Rental Platform</title>
<style type="text/css">
@charset "UTF-8";
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
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	text-align: center;
	background: #fff;
	padding: 2rem;
	border-radius: 10px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
	max-width: 600px;
	width: 100%;
}

header h1 {
	font-size: 2.5rem;
	margin-bottom: 1.5rem;
	color: #333;
}

nav {
	display: flex;
	justify-content: center;
	gap: 1rem;
}

.btn {
	text-decoration: none;
	padding: 0.75rem 1.5rem;
	background-color: #007BFF;
	color: #fff;
	border-radius: 5px;
	transition: background-color 0.3s ease;
}

.btn:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<div class="container">
		<header>
			<h1>Welcome to the Property Rental & Booking Platform</h1>
		</header>
		<nav>
			<a href="login.jsp" class="btn">Login</a> <a href="register.jsp"
				class="btn">Register</a> <a href="properties/list" class="btn">View
				Properties</a>
		</nav>
	</div>
</body>
</html>