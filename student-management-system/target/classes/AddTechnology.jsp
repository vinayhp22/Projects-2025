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
<title>X-workz || Home</title>
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
	padding: 50px;
}

nav a:hover {
	color: #ffc107;
	background-color: #555;
	border-radius: 5px;
}

.active {
	background-color: #4caf50;
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

		<img alt="SkyllX Technologies Logo" src="https://www.skyllx.com/courses/assets/image/logo_black.png" />
		<nav>
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="listtechnology?id=${dto.id}">List Technologies</a></li>
				<li><a href="updateProfile?userId=${dto.userId}">Update Profile</a></li>
				<li><a href="AboutUs.jsp">About Us</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="ContactUs.jsp">Contact Us</a></li>
				<li><a class="userId">Welcome, ${getUserId}</a></li>
				<li><img id="profilePic" src="" /></li>
			</ul>
		</nav>
	</header>
	<main>
		<h1 style="color: green;" align="center">${addTechSuccess}</h1>
		<h5 style="color: red;" align="center">
			<c:forEach items="${errors}" var="e">
				<br>${e.message}</c:forEach>
		</h5>
		<div class="container mt-5">
			<h1>Add Technology Form</h1>
			<form action="addtechnology?id=${id}" method="post">
				<div class="form-group">
					<label for="name">Name</label> <input type="text"
						class="form-control" name="name" id="name" value="${tlDto.name}">
				</div>
				<div class="form-group">
					<label for="language">Language</label> <input type="text"
						class="form-control" id="language" name="language"
						value="${tlDto.language}">
				</div>
				<div class="form-group">
					<label for="version">Version</label> <input type="number"
						class="form-control" id="version" name="version"
						value="${tlDto.version}" onchange="setTwoNumberDecimal()" min="0"
						max="1000" step="0.01" value="0.00">
				</div>
				<div class="form-group">
					<label for="owner">Owner</label> <input type="text"
						class="form-control" id="owner" name="owner"
						value="${tlDto.owner}">
				</div>
				<div class="form-group">
					<label for="supportFrom">Support From</label> <input type="text"
						class="form-control" id="supportFrom" name="supportFrom"
						value="${tlDto.supportFrom}">
				</div>
				<div class="form-group">
					<label for="supportTo">Support To</label> <input type="text"
						class="form-control" id="supportTo" name="supportTo"
						value="${tlDto.supportTo}" />
				</div>
				<div class="form-group">
					<label for="license">License</label> <input type="text"
						class="form-control" id="license" name="license"
						value="${tlDto.license}" />
				</div>
				<div class="form-group">
					<label for="OSType">OSType</label> <input type="text"
						class="form-control" id="OSType" name="OSType"
						value="${tlDto.OSType}" />
				</div>
				<div class="form-group">
					<label for="openSource">Open Source: (if yes, click on
						check box)</label> <input type="checkbox" id="openSource"
						name="openSource"/>
				</div>
				<button type="submit" class="btn btn-primary" id="submit"
					disabled="disabled">ADD</button>
			</form>
		</div>
	</main>


	<footer>
		<p>&copy; 2025 SkyllX pvt ltd. All rights reserved.</p>
		<p>Developed by: Skyllx Team</p>
	</footer>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
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

		var ppic = document.getElementById("profilePic")
		console.log(ppic.src)
		ppic.src = "downloadPic?profilePic=" + pic
		console.log(ppic.src)

		// Get the input elements
		const name = document.getElementById('name');
		const language = document.getElementById('language');
		const version = document.getElementById('version');
		const owner = document.getElementById('owner');
		const supportFrom = document.getElementById('supportFrom');
		const supportTo = document.getElementById('supportTo');
		const license = document.getElementById('license');
		const openSource = document.getElementById('openSource');
		const OSType = document.getElementById('OSType');
		const submit = document.getElementById('submit')

		// Add event listeners to the input fields
		name.addEventListener('input', checkFields);
		language.addEventListener('input', checkFields);
		version.addEventListener('input', checkFields);
		owner.addEventListener('input', checkFields);
		supportFrom.addEventListener('input', checkFields);
		supportTo.addEventListener('input', checkFields);
		license.addEventListener('input', checkFields);
		openSource.addEventListener('input', checkFields);
		OSType.addEventListener('input', checkFields);

		// Function to check if all fields are filled
		function checkFields() {
			if (name.value !== '' && language.value !== ''
					&& version.value !== '' && owner !== ''
					&& supportFrom.value !== '' && license.value !== ''
					&& openSource !== '' && OSType.value !== '') {
				submit.disabled = false;
			} else {
				submit.disabled = true;
			}
		}

		function setTwoNumberDecimal(event) {
			this.value = parseFloat(this.value).toFixed(5);
		}
	</script>

</body>
</html>