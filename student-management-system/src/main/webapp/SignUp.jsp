<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Registration || Skyllx</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
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

.flag-icon {
	width: 30px;
	height: 20px;
	display: inline-block;
	background-size: contain;
	background-repeat: no-repeat;
	margin-right: 5px;
}
</style>
</head>
<body>
	<header>
		<img alt="SkyllX Technologies Logo" src="https://www.skyllx.com/courses/assets/image/logo_black.png" />
		<nav>
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a class="active" href="register">Register</a></li>
				<li><a href="signIn">SignIn</a></li>
				<li><a href="AboutUs.jsp">About Us</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="ContactUs.jsp">Contact Us</a></li>
			</ul>
		</nav>
	</header>
	<main>
		<marquee style="background-color: red;">SignUp, enjoy hassle
			free training</marquee>

		<h5 style="color: red;" align="center">
			<c:forEach items="${errors}" var="e">
				<br>${e.message}</c:forEach>
		</h5>
		<div class="container mt-5">
			<h1>Trainee Registration Form</h1>
			<form action="register" method="post">
				<div class="form-group">
					<label for="userId">UserId</label> <input type="text"
						class="form-control" id="userName" name="userId"
						value="${registerdto.userId}" onblur="userNameValidation()">
					<a id="userNameValidationMessage" style="color: red"></a> <a
						id="userNameFromDB" style="color: red;"></a>
				</div>
				<div class="form-group">
					<label for="email">Email address</label> <input type="email"
						class="form-control" id="emailId" name="email"
						value="${registerdto.email}" onblur="emailValidation()"> <a
						id="emailValidationMessage" style="color: red"></a> </a> <a
						id="userEmailFromDB" style="color: red;"></a>
				</div>
				<div class="form-group">
					<label for="mobile">Mobile</label> <input type="number"
						class="form-control" id="mobileNo" name="mobile"
						value="${registerdto.mobile}" onblur="mobileValidation()">
					<a id="mobileValidationMessage" style="color: red"></a> <a
						id="mobileFromDB" style="color: red"></a>
				</div>
				<div class="form-group">
					<label for="password">Password</label> <input type="password"
						class="form-control" id="password" name="password"
						value="${registerdto.password}" onblur="passwordValidation()">
					<a id="passwordValidationMessage" style="color: red"></a> <input
						type="checkbox" onclick="showPassword()"> Show Password
				</div>
				<div class="form-group">
					<label for="confirm_password">Confirm Password</label> <input
						type="password" class="form-control" id="userConfirmPassword"
						name="confirmPassword" onblur="passwordComparision()"> <a
						id="passwordCompareMessage" style="color: red"></a>
				</div>
				<div class="form-group">
					<label for="agreement">I agree to the terms and conditions:
					</label> <input type="checkbox" name="agreement" id="agreement"
						onclick="onAgreement()" disabled="disabled">
				</div>
				<button type="submit" class="btn btn-primary" id="submit"
					disabled="disabled">Register</button>
			</form>
		</div>
	</main>
	<footer>
		<p>&copy; 2025 SkyllX Pvt Ltd. All rights reserved.</p>
		<p>Developed by: Skyllx Team</p>
	</footer>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

	<script>
		function showPassword() {
			var x = document.getElementById("password");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}

		// Get the input elements
		const agreement = document.getElementById('agreement');
		const name = document.getElementById('userName');
		const email = document.getElementById('emailId');
		const mobile = document.getElementById('mobileNo');
		const password = document.getElementById('password');
		const confirmPassword = document.getElementById('userConfirmPassword');

		// Add event listeners to the input fields
		name.addEventListener('input', checkFields);
		email.addEventListener('input', checkFields);
		mobile.addEventListener('input', checkFields);
		password.addEventListener('input', checkFields);
		confirmPassword.addEventListener('input', checkFields);

		// Function to check if all fields are filled
		function checkFields() {
			if (name.value !== '' && email.value !== ''
					&& password.value !== '' && mobile !== ''
					&& confirmPassword.value !== '') {
				agreement.disabled = false;
			} else {
				agreement.disabled = true;
			}
		}

		function onAgreement() {
			var agree = document.getElementById('agreement');
			console.log(agree.checked);
			if (agree.checked) {
				document.getElementById('submit').disabled = false;
				agree.disabled = false;
			} else {
				document.getElementById('submit').disabled = 'disabled';
			}
		}

		function userNameValidation() {
			var user = document.getElementById('userName');
			var uservalue = user.value;
			console.log(uservalue);
			if (uservalue != null && uservalue != "" && uservalue.length > 3
					&& uservalue.length < 30) {
				console.log('userName is valid');
				document.getElementById('userNameValidationMessage').innerHTML = '';
				console.log(user);
				console.log(uservalue);

				const xhr = new XMLHttpRequest();
				xhr.open("GET",
						"http://localhost:8082/xworkz-vinayhp-cm/userId/"
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
						"http://localhost:8082/xworkz-vinayhp-cm/email/"
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

		function mobileValidation() {
			var userMobile = document.getElementById('mobileNo');
			var mobileValue = userMobile.value;
			console.log(mobileValue);
			if (mobileValue != null && mobileValue != ""
					&& mobileValue.length == 10) {
				console.log('mobile is valid');
				document.getElementById('mobileValidationMessage').innerHTML = '';

				const xhr = new XMLHttpRequest();
				xhr.open("GET",
						"http://localhost:8082/xworkz-vinayhp-cm/mobile/"
								+ mobileValue);
				xhr.send();

				xhr.onload = function() {
					console.log(this);
					document.getElementById("mobileFromDB").innerHTML = this.responseText;
				}
			} else {
				console.log('mobile is invalid');
				document.getElementById('mobileValidationMessage').innerHTML = 'Plese enter valid Mobile Number';
			}
		}

		function passwordValidation() {
			var password = document.getElementById('password');
			var confirmPassword = document
					.getElementById('userConfirmPassword');
			var passwordValue = password.value;
			var confirmPasswordvalue = confirmPassword.value;
			console.log(passwordValue);
			if (passwordValue != null && passwordValue != ""
					&& passwordValue.length > 4 && passwordValue.length < 12) {
				console.log('password is valid');
				document.getElementById('passwordValidationMessage').innerHTML = '';
			} else {
				console.log('password is invalid');
				if (passwordValue == "") {
					document.getElementById('passwordValidationMessage').innerHTML = 'Plese enter the password';
				}else if(passwordValue.length < 4){
					document.getElementById('passwordValidationMessage').innerHTML = 'Password should be greater than 4 characters';
				}else if(passwordValue.length > 12){
					document.getElementById('passwordValidationMessage').innerHTML = 'Password should be less than 12 characters';
				}else{
					document.getElementById('passwordValidationMessage').innerHTML = 'Plese enter the password';					
				}
			}
		}

		function passwordComparision() {
			var passwordValue = password.value;
			var confirmPasswordvalue = confirmPassword.value;
			if (passwordValue == confirmPasswordvalue) {
				console.log('Both passwords are same');
				document.getElementById('passwordCompareMessage').innerHTML = '';
			} else {
				console.log('Both passwords are not same');
				document.getElementById('passwordCompareMessage').innerHTML = 'Both password and confirm password must be same';
			}
		}
	</script>
</body>

</html>