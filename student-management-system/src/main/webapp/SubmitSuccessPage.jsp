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

    <title>Form Submitted Successfully</title>
</head>
<body>
    <h1>Form Submission Successful</h1>
    
    <p>Thank you, <strong>${name}</strong>! Your form has been successfully submitted.</p>
    
    <p>Your email: <strong>${email}</strong></p>
    <p>Your message: <strong>${message}</strong></p>
    
    <a href="/submit-form">Submit another form</a>
</body>
</html>
