<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Register</h2>
    <form action="register" method="post">
        Username: <input type="text" name="username"><br>
        Email: <input type="email" name="email"><br>
        Password: <input type="password" name="password"><br>
        Role: 
        <select name="role">
            <option value="OWNER">Owner</option>
            <option value="RENTER">Renter</option>
        </select>
        <br>
        <input type="submit" value="Register">
    </form>
</body>
</html>