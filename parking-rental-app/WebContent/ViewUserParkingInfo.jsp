<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
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

<title>Display tag Pagination and Sorting Example in JSP</title>
<link rel="stylesheet" href="css/displaytag.css" type="text/css">
<link rel="stylesheet" href="css/screen.css" type="text/css">
<link rel="stylesheet" href="css/site.css" type="text/css">

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
	function validateLocation() {
		var location = document.form.location.value;
		console.log("location: " + location);
		var status = false;

		if (location == "" || location == null) {
			document.getElementById('lc').innerHTML = "*Please select the location";
			status = false;
		} else {
			document.getElementById('lc').innerHTML = "";
			status = true;
		}
		console.log("status: " + status);
		return status;
	}
</script>
</head>
<body style="margin-top: 10%; margin-bottom: 5%">
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
				<a href="AdminLoginSuccess.jsp" class="btn btn-sm"
					style="background-color: turquoise;">Home</a>
			</div>
			<div class="navbar-left" class="w3-bar w3-border">
				<p class="navbar-text" style="color: white; font-size: 15px;">Welcome,
					${sessionDTO.name}</p>
			</div>
		</div>
	</nav>
<!-- 	<div class="card-body" style="margin-left: 0%; margin-right: 0%;">
		background-color:pink;
		<form name="form" action="userParkingData"
			onsubmit="return validateLocation()" class="container center"
			style="margin-right: 50px;">
			<span id="lc" style="color: red; margin-left: 25%; font-size: 13px;"></span>
			<div class="row"
				style="margin-left: auto; margin-right: auto; margin-bottom: 40px;">
				background-color:yellow;
				<div class="col-md-3 mt-md-0 mt-3" style="margin-left: 28%;">
					<select name="location" id="location"
						class="form-select shadow required" onkeyup="enableSubmit()"
						aria-label="Default select example">
						<option value="">Location</option>
						<option value="RajajiNagar">RajajiNagar</option>
						<option value="E.city">E.city</option>
						<option value="VijayaNagar">VijayaNagar</option>
						<option value="BTM">BTM</option>
					</select>
				</div>
				<div class="col-md-3 mt-md-0 mt-2">
					<input type="submit" value="Search" id="SubmitBtn"
						class="btn w-60 shadow"
						style="background-color: turquoise; text-align: center;">
				</div>
			</div>
		</form>
		</div> -->

      <div id='tab1' class="tab_content" style="display: block; width: 100%;margin-left:10px;" >
            <h4 style="color: red;">User Parking Info</h4>
          <display style="font-size: 15
          px;">
            <display:table class="table w-auto table-bordered" name="${upList}" pagesize="5"
                           export="true" sort="list" uid="one" style="font-size: 12px;">
           
                <display:column property="location" title="Location"
                                sortable="true" headerClass="sortable" />
                <display:column property="vehicleNo" title="VehicleNo"
                                sortable="true" headerClass="sortable" />
                <display:column property="vehicleType" title="VehicleType"
                                sortable="true" headerClass="sortable" />
                <display:column property="engineType" title="EngineType"
                                sortable="true" headerClass="sortable" />
                <display:column property="classification" title="Classification"
                                sortable="true" headerClass="sortable" />
                <display:column property="term" title="Term"
                                sortable="true" headerClass="sortable" />
                <display:column property="price" title="Price"
                                sortable="true" headerClass="sortable" />
                <display:column property="discount" title="Discount"
                                sortable="true" headerClass="sortable" />
                <display:column property="totalAmount" title="TotalAmount"
                                sortable="true" headerClass="sortable" />
                <display:column property="payment" title="Payment"
                                sortable="true" headerClass="sortable" />
                <display:column property="createdDate" title="CreatedDate"
                                sortable="true" headerClass="sortable" />
                <display:column property="updatedDate" title="UpdatedDate"
                                sortable="true" headerClass="sortable" />  
<%-- 				<display:column property="isActive" title="isActive"
                                sortable="true" headerClass="sortable" /> --%>                                                                                                                           
            </display:table>
            </display>
        </div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<div class="footer">
		<small style="color: white;">&copy; Created by: SkyllX Team,
		 </small><small style="color: white;">&ensp;info@skyllx.com</small><small
			style="color: green;"> | Last login time:
			${sessionDTO.loginTime}</small>
	</div>
</body>
</html>