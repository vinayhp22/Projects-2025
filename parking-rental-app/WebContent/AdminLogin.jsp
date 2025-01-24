<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html long="en">
<head>
<!-- required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">

<!-- CSS link -->
<link rel="stylesheet" href="style.css">
<title>Rental-Parking App</title>
<style>
.selector-for-some-widget {
	box-sizing: content-box;
}
</style>
<style>
.footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: black;
	text-align: center;
	height: 5%;
	font-size: 14px;
}
</style>
<script>
	function validateform() {
		var email1 = document.myform.email.value;
		var password = document.myform.password.value;
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z]{2,4})+$/;
		var status = false;

		if (email1 == "" && password.length < 1) {
			document.getElementById('form').innerHTML = "*Please enter the credential";
			document.getElementById('email1').innerHTML = "";
			document.getElementById('password1').innerHTML = "";
			status = false;
		} else {
			document.getElementById('form').innerHTML = "";
			if (email1 == "") {
				document.getElementById('email1').innerHTML = "*Please enter email-id";
				status = false;
			} else if (!filter.test(email1)) {
				document.getElementById('email1').innerHTML = "*Please enter valid email-id";
				status = false;
			} else {
				document.getElementById('email1').innerHTML = "";
				status = true;
			}
			if (password.length < 1) {
				document.getElementById('password1').innerHTML = "*Please enter Password";
				status = false;
			} else if (password.length < 6) {
				document.getElementById('password1').innerHTML = "*Min length is 6 characters";
				status = false;
			} else {
				document.getElementById('password1').innerHTML = "";
			}
		}
		return status;
	}
</script>
<script>
	function emailValidation() {
		var email1 = document.myform.email.value;
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z]{2,4})+$/;
		var status = false;

		if (email1 == "") {
			document.getElementById('emailError').innerHTML = "*Please enter the email-id";
			document.getElementById('form').innerHTML = "";
			status = false;
		} else if (!filter.test(email1)) {
			document.getElementById('emailError').innerHTML = "*Please enter the valid email-id";
			document.getElementById('form').innerHTML = "";
			status = false;
		} else {
			document.getElementById('emailError').innerHTML = "";
			document.getElementById('form').innerHTML = "";
			status = true;
		}
		return status;
	}
</script>
<script>
	function validatePassword() {
		var password = document.myform.password.value;
		var status = false;

		if (password.length < 1) {
			document.getElementById('password').innerHTML = "*Please enter the Password";
			document.getElementById('form').innerHTML = "";
			status = false;
		} else if (password.length < 6) {
			document.getElementById('password').innerHTML = "*Min length must be 6 characters";
			document.getElementById('form').innerHTML = "";
			status = false;
		} else {
			document.getElementById('password').innerHTML = "";
			document.getElementById('form').innerHTML = "";
		}
		return status;
	}
</script>
<script>
	function clearData() {

		document.getElementById('form').innerHTML = "";
		document.getElementById('email').innerHTML = "";
		document.getElementById('password').innerHTML = "";
	}
</script>
</head>	
<body style="background-color: ;">
	<nav class="navbar fixed-top navbar-dark" style="height: 50px;background-color: black;">
		<div class="container-fluid">
			<img src="logo.png" class="w3-bar w3-border" width="85"
				height="40">
			<div class="navbar-left" class="w3-bar w3-border" style="margin-top: -5px;">
				<a href="Landing.jsp" class="btn btn-sm"
					style="background-color: turquoise;color:black">Home</a>
			</div>
		</div>
	</nav>

	<%-- 	<div class="login" style="background-color: white;">
		<div style="color: black;" class="text-center">
			<h4>Sign-in Form</h4>
		</div>
		<div class="tab-content">
			<div class="tab-pane fade show active" id="pills-login"
				role="tabpanel" aria-labelledby="tab-login">
				<form name="myform" method="post" action="adminLogin"
					onsubmit="return validateform()" class="row g-3 needs-validation"
					novalidate>
<span id="form" style="color:red;"></span><br/>	
<h5 style=color:red;>${error}</h5>
					<div>
						<label class="form-label" for="email">Email address</label> 
						<input type="email" id="email" name="email" class="form-control" onchange="emailValidation()"/>
						<div class="invalid-feedback" id="emailError">Please provide a valid email</div>
						<div class="form-group was-validated" id="validEmail"></div>
						
					</div>

					<div class="form-group was-validated">
						<label class="form-label" for="password">Password</label>
						<div class="invalid-feedback">Please enter your password</div>
						<input class="form-control" type="password" id="password"
							name="password">

					</div>
					<input class="btn w-50"
						style="background-color: turquoise; margin-right: 80px; font-weight: bold;"
						type="submit" value="SIGN IN">
				</form>
			</div>
		</div>
	</div> --%>

	<div class="container">
		<div class="row justify-content-center mt-5" >
			<div class="col-lg-4 col-md-8 col-sm-8" style="margin-top:-5%;">
				<div class="card shadow " >
					<div class="card-title text-center border-bottom" style="background-color: turquoise;">
						<h3 class="p-2" style="color: black;">Admin Login</h3>
					</div>
					<div class="card-body">
						<form action="adminLogin" method="post" onsubmit="return validateform()" name="myform">
						<span id="form" style="color:red;"></span><span style=color:red;>${error}</span>
							<div class="mb-4 shadow"> 
								<input type="text" class="form-control" id="email" name="email" placeholder="Email address"/>
								<span id="email1" style="color:red;"></span>
							</div>
							<div class="mb-4 shadow">
								<input type="password" class="form-control" id="password" name="password" placeholder="Password"/>
								<span id="password1" style="color:red;"></span>
							</div>
							<div class="d-grid shadow" style="margin-left: 10%;margin-right: 10%;">
							<button type="submit" class="btn w-60" style="background-color: turquoise;text-align: center;"><b>Login</b></button>
							</div>
						<div class="mb-3"></div>
						<div class="mb-3" style="text-align: center;">
						<small><a href="Landing.jsp" id="fpassword" style="a{text-decoration: none;}a:hover {text-decoration: underline;}">Forgot password?</a></small>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
		<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
	<div class="footer">
		<small style="color:white;"style="color:white;">&copy; Created by: SkyllX Team,</small><small style="color:white;">&ensp;info@skyllx.com</small>
	</div>
</body>
</html>