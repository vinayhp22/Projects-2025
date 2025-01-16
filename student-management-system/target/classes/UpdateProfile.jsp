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
<title>Skyllx || Home</title>
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

nav img {
	height: 30px;
	width: 40px;
	border-radius: 50px;
}

main {
	height: 500px;
	padding: 50px;
}

nav a:hover {
	color: #ffc107;
	background-color: #555;
	border-radius: 5px;
}

.userId {
	background-color: red;
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

		<img alt="SkyllX Technologies Logo" src="https://www.skyllx.com/courses/assets/image/logo_black.png"/>
		<nav>
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="register">Register</a></li>
				<li><a href="signIn">SignIn</a></li>
				<li><a href="AboutUs.jsp">About Us</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="ContactUs.jsp">Contact Us</a></li>
				<li><a class="userId">Welcome, ${dto.userId}</a></li>
				<li><img id="profilePic" src="" /></li>
			</ul>
		</nav>
	</header>

	<main>
		<h1>Skyllx Technology Pvt Ltd - Update Profile</h1>

		<form action="updateProfile" method="post"
			enctype="multipart/form-data">
			<div class="form-group">
				<label for="email">Email address</label> <input type="email"
					class="form-control" id="emailId" name="email" value="${dto.email}"
					onblur="emailValidation()" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="mobile">Mobile</label> <input type="number"
					class="form-control" id="mobileNo" name="mobile"
					value="${dto.mobile}" onblur="mobileValidation()"> <a
					id="mobileValidationMessage" style="color: red"></a> <a
					id="mobileFromDB" style="color: red"></a>
			</div>
			<div class="form-group">
				<label for="userId">UserId</label> <input type="text"
					class="form-control" id="userName" name="userId"
					value="${dto.userId}" onblur="userNameValidation()"> <a
					id="userNameValidationMessage" style="color: red"></a> <a
					id="userNameFromDB" style="color: red;"></a>
			</div>
			<div class="form-group">
				<label for="profilePicture">Profile Picture:</label> <input
					type="file" name="profilePicture" id="profilePic">
			</div>
			<input type="submit" value="Update Profile">
		</form>
	</main>

	<footer>
		<p>&copy; 2025 SkyllX Pvt Ltd. All rights reserved.</p>
		<p>Developed by: Skyllx Team</p>
	</footer>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>

	<script type="text/javascript">
		var pic = localStorage.getItem("pic");
		console.log("localStorage : " + pic);
		if (pic == null) {
			var profilePicUrl = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";
			document.getElementById("profilePic").src = profilePicUrl;
		}

		var ppic = document.getElementById("profilePic");
		console.log(ppic.src);
		ppic.src = "downloadPic?profilePic=" + pic;
		console.log(ppic.src);

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
	</script>

</body>
</html>