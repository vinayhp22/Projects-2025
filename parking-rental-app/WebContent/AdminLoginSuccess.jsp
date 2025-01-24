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
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
<nav class="navbar fixed-top navbar-dark bg-dark" style="height:50px;">
		<div class="container-fluid">
		<div class="navbar-header">
			<img src="logo.png" class="w3-bar w3-border" width="85"
				height="40" style="margin-top: -20px">
				</div>
			<div class="w3-bar w3-border" style="margin-left: 780px;margin-top: -20px;">
			<a href="AdminLogin.jsp" class="btn btn-sm"
					style="background-color: turquoise;">Sign-out</a></div>
			<div class="navbar-left" class="w3-bar w3-border">
			<p class="navbar-text" style="color:white;font-size: 15px;">Welcome, ${sessionDTO.name}</p>
			</div>
		</div>
</nav>
<div> 
<button class="btn" type="submit" onclick="window.location.href='onStart';" style="background-color: turquoise;color:black;position:absolute; top:15%; left:1%;">Update Parking Info</button>
<button class="btn" type="submit" onclick="window.location.href='AdminViewData.jsp';" style="background-color: turquoise;color:black;position:absolute; top:25%; left:1%;">View Parking Info</button>
<button class="btn" type="submit" onclick="window.location.href='userParkingInfo';" style="background-color: turquoise;color:black;position:absolute; top:35%; left:1%;">View UserParking Info</button>
</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<div class="footer">
		<small style="color:white;">&copy; Created by: SkyllX Team,</small><small style="color:white;">&ensp;info@skyllx.com</small><small style="color:green;"> | Last login time: ${sessionDTO.loginTime}</small>
	</div>
</body>
</html>