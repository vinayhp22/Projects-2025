<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/form.css">
</head>
<body>
    <h2>Register</h2>
    <form action="register" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address"><br><br>

        <input type="submit" value="Register">
    </form>
</body>
</html>