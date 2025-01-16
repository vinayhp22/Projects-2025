<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- Required meta tags for bootstrap-->
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>About Us</title>
<style>
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

.active {
	background-color: #4caf50;
}

main {
	padding: 50px;
}

nav a:hover {
	color: #ffc107;
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
				<li class="active"><a href="AboutUs.jsp">About Us</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="ContactUs.jsp">Contact Us</a></li>
			</ul>
		</nav>
	</header>
	<main>
		<h1>About Us</h1>

		<p>Welcome to our website! We are a team of developers who are
			passionate about creating high-quality web applications that meet the
			needs of our clients. In this section, we would like to share some
			information about the development of some common modules that you may
			encounter on our site:</p>

		<h2>Sign Up Module</h2>

		<p>The sign up module is an essential component of any website
			that requires user registration. When designing this module, we
			focused on creating a simple and user-friendly interface that guides
			the user through the registration process step-by-step. We also
			implemented various security measures, such as password hashing and
			email verification, to ensure that user data is protected.</p>

		<h2>Sign In Module</h2>

		<p>The sign in module allows users to log in to our website
			securely. To achieve this, we use a combination of session management
			and encryption techniques to ensure that user data remains
			confidential. In addition, we also implemented measures such as
			CAPTCHA and two-factor authentication to prevent unauthorized access.</p>

		<h2>Send Mail Module</h2>

		<p>The send mail module allows users to send messages to other
			users or to the site administrators. We developed this module using
			popular email APIs, such as SMTP, to ensure that emails are delivered
			reliably and efficiently. We also implemented various spam filters
			and other security measures to prevent misuse of this feature.</p>

		<p>Thank you for visiting our website. If you have any questions
			or feedback, please do not hesitate to contact us.</p>
	</main>
	<footer>
		<p>&copy; 2025 SkyllX Pvt Ltd. All rights reserved.</p>
		<p>Developed by: Skyllx Team</p>
	</footer>
</body>
</html>
