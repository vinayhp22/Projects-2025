<%@page import="com.skyllx.system.dto.TechnologyListDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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

		<img alt="SkyllX Technologies Logo" src="https://www.skyllx.com/courses/assets/image/logo_black.png" />
		<nav>
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="addtechnology?id=${dto.id}">Add Technology</a></li>
				<li><a href="updateProfile?userId=${dto.userId}">Update
						Profile</a></li>
				<li><a href="AboutUs.jsp">About Us</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="ContactUs.jsp">Contact Us</a></li>
				<li><a class="userId">Welcome, ${dto.userId}</a></li>
				<li><img id="profilePic" src="" /></li>
				<!-- <span>Welcome, <span id="userId"></span></span> -->
			</ul>
		</nav>
	</header>

	<main>
		<h1 style="color: navy;" align="center">Technology(s) List</h1>
		<h2>
			Name: <a style="color: gray;"> ${dto.userId}</a>
		</h2>
		<h2>
			Email: <a style="color: gray;"> ${dto.email}</a>
		</h2>
		<h2>
			Total no. of technologies : <a style="color: gray;">
				${dtos.size()}</a>
		</h2>
		<input type="text" id="search-input" onkeyup="filterTable()"
			placeholder="Search...">

		<div class="container" id="sortByName">
			<table style="width: 100%;" class="table table-striped">
				<thead>
					<tr>
						<th onclick="sortByName()">Name</th>
						<th onclick="sortByLanguage()">Language</th>
						<th onclick="sortByVersion()">Version</th>
						<th onclick="sortByOwner()">Owner</th>
						<th onclick="sortBySupportFrom()">Support From</th>
						<th onclick="sortBySupportTo()">Support To</th>
						<th onclick="sortByLicense()">License</th>
						<th onclick="sortByOSType()">OS Type</th>
						<th onclick="sortByOpenSource()">Open Source</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${dtos}" var="d">
						<tr>
							<td>${d.name}</td>
							<td>${d.language}</td>
							<td>${d.version}</td>
							<td>${d.owner}</td>
							<td>${d.supportFrom}</td>
							<td>${d.supportTo}</td>
							<td>${d.license}</td>
							<td>${d.OSType}</td>
							<td>${d.openSource}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<input type="text" id="searchInput" placeholder="Search...">
		<button onclick="search()">Search</button>
		<button onclick="restoreOrder()">Restore order</button>
	</main>


	<footer>
		<p>&copy; 2025 SkyllX Pvt Ltd. All rights reserved.</p>
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
		console.log(ppic.src);

		function filterTable() {
			var value = $('#search-input').val().toLowerCase();
			$('table tbody tr').filter(
					function() {
						$(this).toggle(
							$(this).text().toLowerCase().indexOf(value) > -1)
					});
		}

		function search() {
			var searchValue = $('#searchInput').val().trim().toUpperCase();
			$('table tbody tr').each(function() {
				var rowValue = $(this).text().toUpperCase();
				if (rowValue.indexOf(searchValue) >= 0) {
					$(this).css('background-color', 'green');
				} else {
					$(this).css('background-color', '');
				}
			});
		}

		// Define a variable to store the original order of the rows
		var originalRows = $('table tbody tr').get();

		// Get the table rows and convert them to an array
		var rows = $('table tbody tr').get();

		function sortByName() {
			// Sort the rows based on the text content of the first column
			rows.sort(function(a, b) {
				var nameA = $(a).find('td:first').text().toUpperCase();
				var nameB = $(b).find('td:first').text().toUpperCase();
				return nameA.localeCompare(nameB);
			});

			// Reorder the rows in the table
			$.each(rows, function(index, row) {
				$('table tbody').append(row);
			});
		}

		function sortByLanguage() {
			// Sort the rows based on the text content of the first column
			rows.sort(function(a, b) {
				var languageA = $(a).find('td:eq(1)').text().toUpperCase();
				var languageB = $(b).find('td:eq(1)').text().toUpperCase();
				return languageA.localeCompare(languageB);
			});

			// Reorder the rows in the table
			$.each(rows, function(index, row) {
				$('table tbody').append(row);
			});
		}

		function sortByVersion() {
			// Sort the rows based on the text content of the first column
			rows.sort(function(a, b) {
				var versionA = $(a).find('td:eq(2)').text().toUpperCase();
				var versionB = $(b).find('td:eq(2)').text().toUpperCase();
				return versionA.localeCompare(versionB);
			});

			// Reorder the rows in the table
			$.each(rows, function(index, row) {
				$('table tbody').append(row);
			});
		}

		function sortByOwner() {
			// Sort the rows based on the text content of the first column
			rows.sort(function(a, b) {
				var ownerA = $(a).find('td:eq(3)').text().toUpperCase();
				var ownerB = $(b).find('td:eq(3)').text().toUpperCase();
				return ownerA.localeCompare(ownerB);
			});

			// Reorder the rows in the table
			$.each(rows, function(index, row) {
				$('table tbody').append(row);
			});
		}

		function sortBySupportFrom() {
			// Sort the rows based on the text content of the first column
			rows.sort(function(a, b) {
				var supportFromA = $(a).find('td:eq(4)').text().toUpperCase();
				var supportFromB = $(b).find('td:eq(4)').text().toUpperCase();
				return supportFromA.localeCompare(supportFromB);
			});

			// Reorder the rows in the table
			$.each(rows, function(index, row) {
				$('table tbody').append(row);
			});
		}

		function sortBySupportTo() {
			// Sort the rows based on the text content of the first column
			rows.sort(function(a, b) {
				var supportToA = $(a).find('td:eq(5)').text().toUpperCase();
				var supportToB = $(b).find('td:eq(5)').text().toUpperCase();
				return supportToA.localeCompare(supportToB);
			});

			// Reorder the rows in the table
			$.each(rows, function(index, row) {
				$('table tbody').append(row);
			});
		}

		function sortByLicense() {
			// Sort the rows based on the text content of the first column
			rows.sort(function(a, b) {
				var licenseA = $(a).find('td:eq(6)').text().toUpperCase();
				var licenseB = $(b).find('td:eq(6)').text().toUpperCase();
				return licenseA.localeCompare(licenseB);
			});

			// Reorder the rows in the table
			$.each(rows, function(index, row) {
				$('table tbody').append(row);
			});
		}

		function sortByOSType() {
			// Sort the rows based on the text content of the first column
			rows.sort(function(a, b) {
				var OSTypeA = $(a).find('td:eq(7)').text().toUpperCase();
				var OSTypeB = $(b).find('td:eq(7)').text().toUpperCase();
				return OSTypeA.localeCompare(OSTypeB);
			});

			// Reorder the rows in the table
			$.each(rows, function(index, row) {
				$('table tbody').append(row);
			});
		}

		function sortByOpenSource() {
			// Sort the rows based on the text content of the first column
			rows.sort(function(a, b) {
				var openSourceA = $(a).find('td:eq(8)').text().toUpperCase();
				var openSourceB = $(b).find('td:eq(8)').text().toUpperCase();
				return openSourceA.localeCompare(openSourceB);
			});

			// Reorder the rows in the table
			$.each(rows, function(index, row) {
				$('table tbody').append(row);
			});
		}

		function restoreOrder() {
			// Reorder the rows in the table to the original order
			$.each(originalRows, function(index, row) {
				$('table tbody').append(row);
			});
		}
	</script>



</body>
</html>