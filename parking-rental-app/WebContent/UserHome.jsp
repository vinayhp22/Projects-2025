<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html long="en">
<head>
<!-- required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

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
</head>
<body style="background-color: white;margin-bottom: 30%">
	<nav class="navbar fixed-top navbar-dark bg-dark" style="height: 50px;">
		<div class="container-fluid">
			<img src="logo.png" class="w3-bar w3-border" width="85"
				height="40">
			<div class="navbar-left" class="w3-bar w3-border" style="margin-top: -5px;">
				<a href="Landing.jsp" class="btn btn-sm"
					style="background-color: turquoise;">Home</a> 
				<a href="onRegister" class="btn btn-sm"
					style="background-color: turquoise;">Sign up</a>
				<a href="UserLogin.jsp" class="btn btn-sm"
					style="background-color: turquoise;">Sign in</a>
			</div>
		</div>
	</nav>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<div class="footer">
		<small style="color: white;" style="color:purple;">&copy;
			Created by: SkyllX Team,</small><small style="color: white;">&ensp;info@skyllx.com</small>
	</div>
</body>
</html>