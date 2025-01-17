<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/form.css">
</head>
<body>
    <h2>Login</h2>
    <form action="login" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" value="Login">
    </form>
    <% if (request.getAttribute("errorMessage") != null) { %>
        <p><%= request.getAttribute("errorMessage") %></p>
    <% } %>
</body>
</html>