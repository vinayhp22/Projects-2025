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
				style="margin-left: 700px; margin-top: -20px;">
				<a href="paymentDue?email=${personalData.email}" class="btn btn-sm"
					style="background-color: turquoise;">Payment</a>
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
	<span style="margin-left:16.6%;color: blue"><b>Profile</b></span>
		<table class="table w-auto table-bordered" style="margin-left:auto;margin-right:auto;">
			<thead style="text-align: center;font-size: 12px;">
				<tr>
				<th scope="col" style="background-color: turquoise;">Name</th>
				<th scope="col" style="background-color: turquoise;">Email</th>
				<th scope="col" style="background-color: turquoise;">PhoneNo</th>
				<th scope="col" style="background-color: turquoise;">CreatedBy</th>
				<th scope="col" style="background-color: turquoise;">CreatedDate</th>
				<th scope="col" style="background-color: turquoise;">UpdatedBy</th>
				<th scope="col" style="background-color: turquoise;">UpdatedDate</th>
				</tr>
			</thead>
			<tbody style="text-align: center;font-size: 12px;">
				<tr>
				<td>${personalData.name}</td>
				<td>${personalData.email}</td>
				<td>${personalData.phoneNo}</td>
				<td>${personalData.createdBy}</td>
				<td>${personalData.createdDate}</td>
				<td>${personalData.updatedBy}</td>
				<td>${personalData.updatedDate}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="row" style="margin-left:15px;">
	<span style="margin-left:-2%;color: blue;"><b>Parking details</b></span>
	<span style="margin-left:40%;color: red;">${error}</span>
		<table class="table w-auto table-bordered" style="margin-left:-14px;">
			<thead style="text-align: center;font-size: 12px;"> <!-- class="table-dark" -->
				<tr style="text-align: center;">
					<th scope="col" style="background-color: turquoise;">Location</th>
					<th scope="col" style="background-color: turquoise;">Vehicle No.</th>
					<th scope="col" style="background-color: turquoise;">Vehicle-Type</th>
					<th scope="col" style="background-color: turquoise;">Engine-Type</th>
					<th scope="col" style="background-color: turquoise;">Classi-fication</th>
					<th scope="col" style="background-color: turquoise;">Term</th>
					<th scope="col" style="background-color: turquoise;">Price</th>
					<th scope="col" style="background-color: turquoise;">Discount</th>
					<th scope="col" style="background-color: turquoise;">Total-Amt</th>
					<th scope="col" style="background-color: turquoise;">Created-Date</th>
					<th scope="col" style="background-color: turquoise;">Updated-Date</th>					
					<th scope="col" style="background-color: turquoise;">Vehicle-Picture</th>
					<th scope="col" style="background-color: turquoise;"></th>
					<th scope="col" style="background-color: turquoise;"></th>
					
				</tr>
			</thead>
			<tbody style="text-align: center;font-size: 12px;">
				<c:forEach items="${parkingData}" var="dto">
					<tr>
						<td>${dto.location}</td>
						<td>${dto.vehicleNo}</td>
						<td>${dto.vehicleType}</td>
						<td>${dto.engineType}</td>
						<td>${dto.classification}</td>
						<td>${dto.term}</td>
						<td>${dto.price}</td>
						<td>${dto.discount}</td>
						<td>${dto.totalAmount}</td>
						<td>${dto.createdDate}</td>
						<td>${dto.updatedDate}</td>
						<td>
							<a href="showFile?fileName=${dto.fileName}&contentType=${dto.contentType}" target="_blank">
								<img src="showFile?fileName=${dto.fileName}&contentType=${dto.contentType}" width="60" height="50"></a>
						</td>
						<td>
							<%-- <a href="${pageContext.request.contextPath}/parkinginfo/byVehicleNo/${dto.vehicleNo}" --%> <a href="parkinginfo?vehicleNo=${dto.vehicleNo}" class="btn btn-sm" style="background-color: turquoise;">Update</a>
						</td>
 						<td>
							<a href="deleteUserParkingData?vehicleNo=${dto.vehicleNo}" class="btn btn-sm" style="background-color: turquoise;">Delete</a>
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