<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>Payment</title>
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
<body Style="margin-top:5%;margin-bottom:5%;">
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
	<nav class="navbar fixed-top navbar-dark bg-dark" style="height: 50px;">
		<div class="container-fluid">
			<div class="navbar-header">
				<img src="logo.png" class="w3-bar w3-border" width="85"
					height="40" style="margin-top: -20px">
			</div>
			
			<div class="w3-bar w3-border"
				style="margin-left: 780px; margin-top: -20px;">
				<a href="duePayment?email=${userDto.email}" class="btn btn-sm"
					style="background-color: turquoise;">Home</a>
			</div>
			<div class="navbar-left" class="w3-bar w3-border">
			<p class="navbar-text" style="color: white;font-size: 15px;">Welcome, ${userDto.name}</p>
			</div>
		</div>
	</nav>

<div class="card-body" style="margin-top:-10%;"> <!-- background-color:pink; -->
	<div class="row">
	<span style="color: green;text-align: center;"><b>${paid}</b></span>
	<span style="color: red;text-align: center;"><b>${due}</b></span>
	<span style="color: blue;text-align: center;"><b>${noDue}</b></span>
	<span style="color: red;text-align: center;"><b>${error}</b></span>
		<table class="table w-auto table-bordered" style="margin-left:auto;margin-right:auto;">
			<thead style="text-align: center;font-size: 12px;">
				<tr>
				<th scope="col" style="background-color: turquoise;">Vehicle-No.</th>
				<th scope="col" style="background-color: turquoise;">Location</th>
				<th scope="col" style="background-color: turquoise;">Term</th>
				<th scope="col" style="background-color: turquoise;">Due-Amount</th>
				<th scope="col" style="background-color: turquoise;"></th>
				</tr>
			</thead>
			<tbody style="text-align: center;font-size: 12px;">
			<c:forEach items="${ppList}" var="record">
				<tr>
				<td>${record.vehicleNo}</td>
				<td>${record.location}</td>
				<td>${record.term}</td>
				<td>${record.totalAmount}</td>
				<td>
				<a href="payment?vehicleNo=${record.vehicleNo}" class="btn btn-sm" style="background-color: turquoise;">Pay</a>
				</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<div class="footer">
		<small style="color:white;">&copy; Created by: SkyllX Team,</small><small style="color:white;">&ensp;info@skyllx.com</small>&ensp;|<small style="color:lightgreen;">&ensp;Last login time: ${userDto.updatedDate}</small>
	</div>
</body>
</html>