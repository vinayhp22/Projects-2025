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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />


<title>Skyllx || Sign In</title>
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
		<img alt="SkyllX Technologies Logo" src="https://www.skyllx.com/courses/assets/image/logo_black.png" />
		<nav>
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="register">Register</a></li>
				<li class="active"><a href="signIn">SignIn</a></li>
				<li><a href="AboutUs.jsp">About Us</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="ContactUs.jsp">Contact Us</a></li>
			</ul>
		</nav>
	</header>
	<main>
		<marquee style="background-color: red;">SignIn and enjoy
			hassle free training</marquee>
		<h1 style="color: green;" align="center">${updatePasswordSuccess}</h1>
		<h5 style="color: red;" align="center">${errors}</h5>
		<div class="container mt-5">
			<h1>Sign In</h1>
			<form action="signIn" method="post">
				<div class="form-group">
					<label for="userId">UserId</label> <input type="text"
						class="form-control" id="userName" name="userId" value="${userId}"
						onchange="userNameValidation()" placeholder="&#xf007; User ID"
						style="font-family: Arial, FontAwesome"><a
						id="userNameValidationMessage" style="color: red"></a><a
						id="userNameFromDB" style="color: red;"></a>
				</div>
				<div class="form-group">
					<label for="password">Password</label> <input type="password"
						class="form-control" id="password" name="password"
						onblur="passwordValidation()" placeholder="&#xf023; Password"
						style="font-family: Arial, FontAwesome"> <a
						id="passwordValidationMessage" style="color: red"></a> <input
						type="checkbox" onclick="showPassword()"> Show Password
				</div>
				<button type="submit" class="btn btn-primary" id="signin"
					disabled="disabled">SignIn</button>
			</form>
			<a href="resetPassword">Forget Password</a>
		</div>
	</main>
	<footer>
		<p>&copy; 2025 Skyllx Technology Pvt Ltd. All rights reserved.</p>
		<p>Developed by: Skyllx Team</p>
	</footer>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
	<script>
		// Get the input elements
		const name = document.getElementById('userName');
		const password = document.getElementById('password');

		function showPassword() {
			if (password.type === "password") {
				password.type = "text";
			} else {
				password.type = "password";
			}
		}

		// Add event listeners to the input fields
		name.addEventListener('input', checkFields);
		password.addEventListener('input', checkFields);

		// Function to check if all fields are filled
		function checkFields() {
			if (name.value !== '' && password.value !== '') {
				signin.disabled = false;
			} else {
				signin.disabled = true;
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
						"http://localhost:8082/student-management-system/signinuserid/"
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
			var passwordValue = password.value;

			console.log(passwordValue);
			if (passwordValue != null && passwordValue != ""
					&& passwordValue.length > 4 && passwordValue.length < 12) {
				console.log('password is valid');
				document.getElementById('passwordValidationMessage').innerHTML = '';
			} else {
				console.log('password is invalid');
				document.getElementById('passwordValidationMessage').innerHTML = 'Plese enter valid password';
			}
		}
	</script>
</body>

</html>