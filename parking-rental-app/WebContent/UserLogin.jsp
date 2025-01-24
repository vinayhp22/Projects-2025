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
<script>
function validateEmailField(){
	console.log("validateEmailField() logged-in");
	var email=document.getElementById("email").value;
	if (email.trim() === "" || email === null){
		console.log("email field is empty");	
		document.getElementById("emailDisplay").innerHTML="*Please generate otp";
		return false;
	}
	document.getElementById("emailDisplay").innerHTML="";
	return true;
}
</script>
<script>
function enableSubmit(){
	console.log("enableSubmit() logged-in");
let input = document.getElementById('email'); // Enter your class name for a required field, this should also be reflected within your form fields.
console.log("input: "+input);
let btn = document.querySelector('input[type="submit"]');
console.log("btn: "+btn);
let isValid = true;
//for (var i = 0; i < inputs.length; i++){
//let changedInput = inputs[i];
let changedInput = input;
console.log("changedInput: "+changedInput);
if (changedInput.value.trim() === "" || changedInput.value === null){
isValid = false;
//break;
//}
}
btn.disabled = !isValid;
console.log("btn.disabled: "+btn.disabled);
}
</script>
<script>
function enableOTPSubmit(){
	console.log("enableOTPSubmit() logged-in");
let input = document.getElementById('otp'); // Enter your class name for a required field, this should also be reflected within your form fields.
console.log("input: "+input);
let btn = document.getElementById('OtpSubmitBtn');
console.log("btn: "+btn);
let isValid = true;
//for (var i = 0; i < inputs.length; i++){
//let changedInput = inputs[i];
let changedInput = input;
console.log("changedInput: "+changedInput);
if (changedInput.value.trim() === "" || changedInput.value === null){
isValid = false;
//break;
//}
}
btn.disabled = !isValid;
console.log("btn.disabled: "+btn.disabled);
}
</script>
<script> 
function EmailAjax(){
console.log("EmailAjax logged-in");
var email=document.getElementById("email").value;
var url="http://localhost:8080/parking-rental-app/validateEmailForOPT/"+email;
console.log(url);
const xmlHttp=new XMLHttpRequest();
xmlHttp.open("GET", url);
xmlHttp.send();
xmlHttp.onload=function(){
	console.log(this.responseText);
	document.getElementById("emailDisplay").innerHTML=this.responseText;	
}
}

function OTPAjax(){
console.log("OTPAjax logged-in");
var otp=document.getElementById("otp").value;
var email=document.getElementById("email").value;
if (email.trim() === "" || email === null){
	console.log("email field is empty");	
	document.getElementById("emailDisplay").innerHTML="*Please generate otp";
	return false;
}
var url="http://localhost:8080/parking-rental-app/validateOTP/"+email+"/"+otp;
//var url="http://localhost:8080/parking-rental-app/validateOTP/"+otp;
console.log(url);
const xmlHttp=new XMLHttpRequest();
xmlHttp.open("POST", url);
xmlHttp.send();
xmlHttp.onload=function(){
	console.log(this.responseText);
	document.getElementById("OtpDisplay").innerHTML=this.responseText;	
}
}
</script>
<script>
    var mins=.1;
    var secs=mins*60;
    function countdown() {
        setTimeout('Decrement()',60);
    }
    function Decrement() {
        if(document.getElementById) {
            minutes=document.getElementById("minutes");
            seconds=document.getElementById("seconds");
            if(seconds<59) {
                seconds.value=secs;
            }
            else {
                minutes.value=getminutes();
                seconds.value=getseconds();
            }
            if(mins<1) {
                minutes.style.color="red";
                seconds.style.color="red";
            }
            if(mins<0) {
                alert('time up');
                minutes.value=0;
                seconds.value=0;
            }
            else {
                secs--;
                setTimeout('Decrement()',1000);
            }
        }
    }
 
    function getminutes() {
        mins=Math.floor(secs/60);
        return mins;
    }
 
    function getseconds() {
        return secs-Math.round(mins*60);
    }
</script>
</head>
<body>
	<nav class="navbar fixed-top navbar-dark bg-dark" style="height: 50px;">
		<div class="container-fluid">
			<img src="logo.png" class="w3-bar w3-border" width="85" height="40">
			<div class="navbar-left" class="w3-bar w3-border" style="margin-top: -5px;">
				<a href="UserHome.jsp" class="btn btn-sm"
					style="background-color: turquoise;">Home</a>
			</div>
		</div>
	</nav>
<!-- 				    <div style="display: flex; width:80%;
                justify-content:center; padding-top: 0%;">
       			 Time Left ::
			    </div>
			    <div style="display: flex; width:80%;
			                justify-content:center; padding-top: 0%;">
			        <input id="minutes" type="text" style="width: 2%; border: none; font-size: 16px;
			                      font-weight: bold; color: black;">
			        <font size="5">
			            :
			        </font>
			        <input id="seconds" type="text" style="width: 2%; border: none; font-size: 16px;
			                      font-weight: bold; color: black;">
			    </div> -->
		<div class="container">
		<div class="row justify-content-center mt-5" >
			<div class="col-lg-6 col-md-8 col-sm-8" style="margin-top:-5%;">
			<span style="color:blue;margin-right: 20%;"><b>${generateOtpSuccess}</b></span>
			<span style="color:red;margin-left: -20%;"><b>${generateOtpError}</b></span>
			<span style="color:red	;margin-right: 20%;"><b>${acctLocked}</b></span>
			<span style="color:red	;margin-left: -20%;"><b>${otpExpired}</b></span>
				<div class="card shadow" style="width: 400px;">
					<div class="card-title text-center border-bottom" style="background-color: turquoise;">
						<h3 class="p-2" style="color: black;">User Login</h3>
					</div>
					<div class="card-body">
				
<form action="generateOTPAndLogin" method="post" class="container center" style="margin-right: 50px">
<span style="color:red;font-size: 12px;" id="emailDisplay">${emailError}</span> 
						<div class="row">
							<div class="col-md-7 mt-md-0 mt-3" > 
								<input type="text"  class="form-control shadow" onkeyup="enableSubmit()" id="email" name="email" value="${mail}" placeholder="Enter email address" onchange="EmailAjax()"/>
								<span id="email" style="color:red;"></span>
							</div>
							<div class="col-md-4 mt-md-0 mt-3">
							<input type="submit" name="generateOtp" value="Generate OTP" class="btn w-60 shadow" style="background-color: turquoise;text-align: center;" disabled><!--  Generate OTP</button> -->
							</div>
						</div>	
<br/><span style="color:red;font-size: 12px;" id="OtpDisplay">${otpError}</span>
						<div class="row">
							<div class="col-md-7 mt-md-0 mt-3"> 
								<input type="text" class="form-control shadow" id="otp" name="otp" onclick="validateEmailField()"  onkeyup="enableOTPSubmit()" placeholder="Enter OTP" onchange="OTPAjax()"/>
								<span id="otp" style="color:red;"></span>
							</div>
							<div class="col-md-4 mt-md-0 mt-2">
								<input type="submit" name="login" value="Login" id="OtpSubmitBtn" class="btn w-60 shadow" style="background-color: turquoise;text-align: center;" disabled>
								<!-- <a href="userLoginSuccess" class="btn w-60 shadow" style="background-color: turquoise;text-align: center;">send</a> -->
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<div class="footer">
	<small style="color: white;">&copy;Created by: </small><small style="color: white;">SkyllX Team,</small><small style="color: white;">&ensp;info@skyllx.com</small>
	</div>
</body>
</html>