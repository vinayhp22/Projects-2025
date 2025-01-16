<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Us</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
}

h1 {
	text-align: center;
	margin-top: 50px;
	color: #007bff;
}

form {
	margin: 50px auto;
	max-width: 500px;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
}

label {
	display: block;
	margin-bottom: 10px;
	font-weight: bold;
	color: #333;
}

input[type="text"], input[type="email"], textarea {
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	border-radius: 5px;
	border: none;
	background-color: #f9f9f9;
	box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.2);
}

input[type="submit"] {
	display: block;
	margin: 0 auto;
	padding: 10px 20px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #0062cc;
}

body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
}

header {
	background-color: #333;
	color: #fff;
	padding: 20px;
	display: flex;
	align-items: center;
	justify-content: space-between;
}

header img {
	height: 50px;
}

nav ul {
	list-style: none;
	display: flex;
}

nav li {
	margin-left: 20px;
}

nav a {
	color: #fff;
	text-decoration: none;
	font-weight: bold;
}

main {
	height: 500px;
	padding: 50px;
}

nav a:hover {
	color: #ffc107;
	background-color: #555;
	border-radius: 5px;
}

.active {
	background-color: #4caf50;
}

footer {
	background-color: #333;
	color: #fff;
	padding: 20px;
	text-align: center;
}
</style>
</head>
<body>
	<header>
		<img alt="SkyllX Technologies Logo" src="https://www.skyllx.com/courses/assets/image/logo_black.png" />
		<nav>
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="register">Register</a></li>
				<li><a href="signIn">SignIn</a></li>
				<li><a href="AboutUs.jsp">About Us</a></li>
				<li><a href="#">Services</a></li>
				<li class="active"><a href="ContactUs.jsp">Contact Us</a></li>
			</ul>
		</nav>
	</header>
	<main>
		<h1>Contact Us</h1>
		<form method="post" action="submit-form.jsp">
			<label for="name">Name:</label> <input type="text" name="name"
				id="name" required> <label for="email">Email:</label> <input
				type="email" name="email" id="email" required> <label
				for="message">Message:</label>
			<textarea name="message" id="message" rows="5" cols="30" required></textarea>

			<input type="submit" value="Submit">
		</form>
	</main>
	<footer>
		<p>&copy; 2025 SkyllX pvt ltd. All rights reserved.</p>
		<p>Developed by: Skyllx Team</p>
	</footer>
</body>
</html>
