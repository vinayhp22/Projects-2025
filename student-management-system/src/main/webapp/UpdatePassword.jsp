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
		<img alt="SkyllX Technologies Logo" src="https://www.skyllx.com/courses/assets/image/logo_black.png" />
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

		<h1 style="color: red;">${error}</h1>
		<h1>Update Password</h1>

		<form method="post" action="updatePassword">
			<div class="form-group">
				<label for="userId">UserId</label> <input type="text"
					class="form-control" id="userName" name="userId"
					value="${dto.userId}" onblur="userNameValidation()"> <a
					id="userNameValidationMessage" style="color: red"></a> <a
					id="userNameFromDB" style="color: red;"></a>
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" id="password" name="password"
					value="${dto.password}" onblur="passwordValidation()"> <input
					type="checkbox" onclick="showPassword()"> Show Password
				<p>
					Time remaining: <a id="timer"></a> seconds
				</p><a id="timer_message" style="color: red;"></a>
			</div>
			<div class="form-group">
				<label for="password">New Password</label> <input type="password"
					class="form-control" id="newPassword" name="newPassword"
					onblur="passwordValidation()"> <a
					id="passwordValidationMessage" style="color: red"></a> <input
					type="checkbox" onclick="showNewPassword()"> Show Password
			</div>
			<div class="form-group">
				<label for="confirm_password">Confirm New Password</label> <input
					type="password" class="form-control" id="confirmNewPassword"
					name="confirmNewPassword" onblur="passwordComparision()"> <a
					id="passwordCompareMessage" style="color: red"></a>
			</div>
			<input type="submit" value="Update Password" id="submit"
				disabled="disabled">
		</form>
	</main>
	<footer>
		<p>&copy; 2025 SkyllX Pvt Ltd. All rights reserved.</p>
		<p>Developed by: Skyllx Team</p>
	</footer>
	<script type="text/javascript">
		function showPassword() {
			if (password.type === "password") {
				password.type = "text";
			} else {
				password.type = "password";
			}
		}
		function showNewPassword() {
			if (newPassword.type === "password") {
				newPassword.type = "text";
			} else {
				newPassword.type = "password";
			}
		}
		// Get the input elements
		const name = document.getElementById('userName');
		const password = document.getElementById('password');
		const newPassword = document.getElementById('newPassword');
		const confirmNewPassword = document
				.getElementById('confirmNewPassword');

		// Add event listeners to the input fields
		name.addEventListener('input', checkFields);
		password.addEventListener('input', checkFields);
		newPassword.addEventListener('input', checkFields);
		confirmNewPassword.addEventListener('input', checkFields);

		// Function to check if all fields are filled
		function checkFields() {
			if (name.value !== '' && password.value !== ''
					&& newPassword.value !== ''
					&& confirmNewPassword.value !== '') {
				document.getElementById('submit').disabled = false;
			} else {
				document.getElementById('submit').disabled = 'disabled';
			}
		}

		function userNameValidation() {
			var uservalue = name.value;
			console.log(uservalue);
			if (uservalue != null && uservalue != "" && uservalue.length > 3
					&& uservalue.length < 30) {
				console.log('userName is valid');
				document.getElementById('userNameValidationMessage').innerHTML = '';

				const xhr = new XMLHttpRequest();
				xhr.open("GET",
						"http://localhost:8082/xworkz-vinayhp-cm/signinuserid/"
								+ uservalue);
				xhr.send();

				xhr.onload = function() {
					console.log(this);
					document.getElementById("userNameFromDB").innerHTML = this.responseText;
				}
			} else {
				console.log('invalid userName');
				document.getElementById('userNameValidationMessage').innerHTML = 'Plese enter valid name min 4 and max 30 character';
			}
		}

		function passwordValidation() {
			var passwordValue = newPassword.value;
			var confirmPasswordvalue = confirmNewPassword.value;
			if (passwordValue != null && passwordValue != ""
					&& passwordValue.length > 4 && passwordValue.length < 12) {
				console.log('password is valid');
				document.getElementById('passwordValidationMessage').innerHTML = '';
			} else {
				console.log('password is invalid');
				document.getElementById('passwordValidationMessage').innerHTML = 'Plese enter valid password';
			}
		}

		function passwordComparision() {
			var passwordValue = newPassword.value;
			var confirmPasswordvalue = confirmNewPassword.value;
			if (passwordValue == confirmPasswordvalue) {
				console.log('Both passwords are same');
				document.getElementById('passwordCompareMessage').innerHTML = '';
			} else {
				console.log('Both passwords are not same');
				document.getElementById('passwordCompareMessage').innerHTML = 'Both password and confirm password must be same';
			}
		}
		
		// Set the duration of the OTP in seconds
		const otpDuration = 60;

		// Calculate the expiry time
		const expiryTime = new Date().getTime() + otpDuration * 1000;

		// Start the timer
		const timerId = setInterval(() => {
		  // Calculate the remaining time
		  const remainingTime = Math.floor((expiryTime - new Date().getTime()) / 1000);

		  if (remainingTime < 0) {
		    // Clear the timer
		    clearInterval(timerId);
		    // Disable the OTP field
		    document.getElementById("password").disabled = true;
		    document.getElementById("timer_message").innerHTML = "Password expired, generate another password"
		  } else {
		    // Update the timer display
		    document.getElementById("timer").textContent = remainingTime;
		  }
		}, 1000);

	</script>
</body>
</html>
