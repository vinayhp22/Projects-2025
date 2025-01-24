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
<script>
function validateLocation()
{  
var location=document.form.location.value;  
console.log("location: "+location);
var status=false;
 
		if (location=="" || location==null)
		{
		document.getElementById('lc').innerHTML="*Please select the location";
		status=false;  
		}else{
			document.getElementById('lc').innerHTML="";
			status=true;
			}
		console.log("status: "+status);
		return status;
}
</script>
</head>
<body style="margin-top:0%;margin-bottom: 5%">
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
			<a href="AdminLoginSuccess.jsp" class="btn btn-sm"
					style="background-color: turquoise;">Home</a></div>
			<div class="navbar-left" class="w3-bar w3-border">
			<p class="navbar-text" style="color:white;font-size: 15px;">Welcome, ${sessionDTO.name}</p>
			</div>
		</div>
</nav>
<div class="card-body" style="margin-left: 0%;margin-right: 0%;"> <!-- background-color:pink; -->
	<form name="form" action="viewData" onsubmit="return validateLocation()" class="container center" style="margin-right: 50px;">
	<span id="lc" style="color:red;margin-left: 25%;font-size: 13px;"></span>
	<div class="row" style="margin-bottom: 40px;margin-left:auto;margin-right:auto;"> <!-- background-color:yellow; -->
		<div class="col-md-3 mt-md-0 mt-3" style="margin-left: 28%;">
			<select name="location" id="location" class="form-select shadow required" onkeyup="enableSubmit()" aria-label="Default select example">
				<option value="">Location</option>
				<option value="RajajiNagar">RajajiNagar</option>
				<option value="E.city">E.city</option>
				<option value="VijayaNagar">VijayaNagar</option>
				<option value="BTM">BTM</option>
			</select>
		</div>
		<div class="col-md-3 mt-md-0 mt-2">
			<input type="submit" value="Search" id="OtpSubmitBtn" class="btn w-60 shadow" style="background-color: turquoise;text-align: center;">
		</div>
	</div>
	</form>
	
<div style="margin-left: 27%;">
<b style="color:red;">${error}</b><b style="color:blue;">${size}</b></div>
<div class="row" style="margin-left: 0%;"></div> <!--background-color:blue;  -->
<table class="table w-auto table-bordered" style="margin-left:auto;margin-right:auto;">
<thead style="text-align: center;font-size: 12px;"> <!-- class="table-dark" -->
<tr>
<!-- <th scope="col">#</th> -->
<th scope="col" style="background-color: turquoise;">Location</th>
<th scope="col" style="background-color: turquoise;">Vehicle-type</th>
<th scope="col" style="background-color: turquoise;">Engine-type</th>
<th scope="col" style="background-color: turquoise;">Classification</th>
<th scope="col" style="background-color: turquoise;">Term</th>
<th scope="col" style="background-color: turquoise;">Price</th>
<th scope="col" style="background-color: turquoise;">Discount</th>
</tr>
  </thead>
<tbody style="text-align: center;font-size: 12px;">
<c:forEach items="${list}" var="dto">
<tr>
<!-- <th scope="row"></th> -->
<td>${dto.location}</td>
<td>${dto.vehicleType}</td>
<td>${dto.engineType}</td>
<td>${dto.classification}</td>
<td>${dto.term}</td>
<td>${dto.price}</td>
<td>${dto.discount}</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
<%-- <div>
	<form name="form" action="viewData" onsubmit="return validateLocation()">
	<span id="lc" style="color:red;"></span><br/>
<input type="text" name="location"/>
<input class="btn w-40" style="background-color: turquoise; margin-right: 80px; font-weight: bold;" type="submit" value="Search"/>
	</form>
	</div> --%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<div class="footer">
		<small style="color:white;">&copy; Created by: SkyllX Team,</small><small style="color:white;">&ensp;info@skyllx.com</small><small style="color:green;"> | Last login time: ${sessionDTO.loginTime}</small>
	</div>
</body>
</html>