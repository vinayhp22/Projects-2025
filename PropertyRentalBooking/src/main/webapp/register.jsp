<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<style type="text/css">
/* General Reset */
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f4;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

/* Register Container */
.register-container {
	background-color: #fff;
	padding: 2rem;
	border-radius: 10px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
	max-width: 400px;
	width: 100%;
	text-align: center;
}

.register-container h2 {
	font-size: 2rem;
	margin-bottom: 1.5rem;
	color: #333;
}

/* Register Form */
.register-form {
	display: flex;
	flex-direction: column;
	gap: 1rem;
}

.form-group {
	display: flex;
	flex-direction: column;
	text-align: left;
}

.form-group label {
	font-weight: bold;
	margin-bottom: 0.5rem;
	color: #555;
}

.form-control {
	padding: 0.75rem;
	border: 1px solid #ddd;
	border-radius: 5px;
	font-size: 1rem;
	transition: border-color 0.3s ease;
}

.form-control:focus {
	border-color: #007BFF;
	outline: none;
}

/* Role Dropdown */
select.form-control {
	appearance: none;
	background-color: #fff;
}

/* Register Button */
.btn-register {
	padding: 0.75rem;
	background-color: #007BFF;
	color: #fff;
	border: none;
	border-radius: 5px;
	font-size: 1rem;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.btn-register:hover {
	background-color: #0056b3;
}

/* Login Link */
.login-link {
	margin-top: 1rem;
	color: #555;
}

.login-link a {
	color: #007BFF;
	text-decoration: none;
	font-weight: bold;
	transition: color 0.3s ease;
}

.login-link a:hover {
	color: #0056b3;
}
</style>
</head>
<body>
	<div class="register-container">
		<h2>Register</h2>
		<form action="register" method="post" class="register-form">
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					id="username" name="username" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input type="email" id="email"
					name="email" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					id="password" name="password" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="role">Role:</label> <select id="role" name="role"
					class="form-control" required>
					<option value="OWNER">Owner</option>
					<option value="RENTER">Renter</option>
				</select>
			</div>
			<div class="form-group">
				<input type="submit" value="Register" class="btn-register">
			</div>
		</form>
		<p class="login-link">
			Already have an account? <a href="login">Login</a>
		</p>
	</div>
</body>
</html>