<%@page import="com.skyllx.system.service.CMSignInServiceImpl"%>
<%@page import="com.skyllx.system.dto.UserDTO"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="com.skyllx.system.service.CMSignInService"%>
<%@page import="com.skyllx.system.service.CMSignUpService"%>
<%@page import="com.skyllx.system.resources.CMSignUpResources"%>
<%@page import="com.skyllx.system.resources.CMSignInResources"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Password Reset</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
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

main {
	height: 500px;
	padding: 50px;
	padding-top: 10px;
}

main a {
	background-color: lime;
	color: red;
}

nav a:hover {
	color: #ffc107;
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
		<img alt="Xworkz Logo" src="download?fileName=xworkz_logo.png" />
		<nav>
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="register">Register</a></li>
				<li class="active"><a href="signIn">SignIn</a></li>
				<li><a href="AboutUs.jsp">About Us</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="#">Contact Us</a></li>
			</ul>
		</nav>
	</header>
	<main>

		<h1 style="color: green">${success}</h1>
		<h1 style="color: orange;">${mailReject}</h1>
		<h1 style="color: red;">${errors}</h1>
		<h1>Password Reset</h1>

		<form method="post" action="resetPassword">
			<div class="form-group">
				<label for="email">Email address</label> <input type="email"
					class="form-control" id="emailId" name="email" value="${dto.email}"
					onblur="emailValidation()"> <a id="emailValidationMessage"
					style="color: red"></a> </a> <a id="userEmailFromDB"
					style="color: red;"></a>
			</div>
			<input type="submit" value="Reset Password">
		</form>
	</main>
	<footer>
		<p>&copy; 2025 SkyllX pvt ltd. All rights reserved.</p>
		<p>Developed by: Skyllx Team</p>
	</footer>
	<script type="text/javascript">
	
	function emailValidation() {
		var userEmail = document.getElementById('emailId');
		var userEmailvalue = userEmail.value;
		console.log(userEmailvalue);
		if (userEmailvalue != null && userEmailvalue != ""
				&& userEmailvalue.length > 4 && userEmailvalue.length < 40) {
			console.log('email is valid');
			document.getElementById('emailValidationMessage').innerHTML = '';

			const xhr = new XMLHttpRequest();
			xhr.open("GET",
					"http://localhost:8082/xworkz-vinayhp-cm/signinemail/"
							+ userEmailvalue);
			xhr.send();

			xhr.onload = function() {
				console.log(this);
				document.getElementById("userEmailFromDB").innerHTML = this.responseText;
			}
		} else {
			console.log('email is not valid');
			document.getElementById('emailValidationMessage').innerHTML = 'Plese enter valid email';
		}
	}
	
<%-- 	function showLogin() {
		var popup = window.open("SignIn.jsp", "login_popup", "width=1000,height=1000");
		popup.focus();
	}
	
	<%
	// Code to handle password reset
	String message = "";
	String email = request.getParameter("email");

	CMSignInResources signInResources = new CMSignInResources();
	String checkEmail = signInResources.checkEmail(email);
	CMSignInService cmSignInService = new CMSignInServiceImpl();
	UserDTO dto = cmSignInService.findByEmail(email);

	if (dto.isReset_pwd()) {
		// Code to update the password in the database

		// If successful, display a success message
		message = "Password reset successfully. Please login with your new password.";
	} else {
		message = "";
	}
	%>

	<%
	if (!message.equals("")) {
	%>
	<script>
		alert("<%=message%>
		");
		showLogin();
	</script>
	<%
	}
	%> --%>
	</script>
</body>
</html>
