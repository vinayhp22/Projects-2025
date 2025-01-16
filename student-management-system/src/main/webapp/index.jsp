<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" 
  integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" 
  crossorigin="anonymous">
<title>SkyllX Technologies Pvt Ltd || Home</title>

<style>
* {
  box-sizing: border-box;
}

html, body {
  height: 100%;
  margin: 0;
  font-family: 'Arial', sans-serif;
}

body {
  background-color: #f4f7f6;
  display: flex;
  flex-direction: column;
}

header {
  background-color: #4CAF50;
  color: #fff;
  padding: 20px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

header img {
  height: 50px;
  margin-right: 15px;
}

nav ul {
  list-style: none;
  display: flex;
  align-items: center;
  padding: 0;
}

nav li {
  margin-left: 20px;
}

nav a {
  color: #fff;
  text-decoration: none;
  font-weight: bold;
  transition: all 0.3s ease;
}

nav a:hover {
  color: #ffc107;
  background-color: #555;
  border-radius: 5px;
  padding: 5px 10px;
}

nav img {
  height: 30px;
  width: 40px;
  border-radius: 50px;
}

main {
  padding: 50px;
  max-width: 1200px;
  margin: 0 auto;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
  flex-grow: 1;
}

main h1 {
  color: #4CAF50;
  text-align: center;
}

main h3 {
  color: #ff6f61;
  text-align: center;
  margin-bottom: 30px;
}

main p {
  font-size: 18px;
  line-height: 1.6;
  text-align: justify;
}

footer {
  background-color: #333;
  color: #fff;
  padding: 20px;
  text-align: center;
}

footer p {
  margin: 5px;
}

footer a {
  color: #ffc107;
  text-decoration: none;
}

footer a:hover {
  text-decoration: underline;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 10px 20px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.3s ease;
}

button:hover {
  background-color: #45a049;
}

@media (max-width: 768px) {
  header img {
    height: 40px;
  }

  nav ul {
    flex-direction: column;
    align-items: center;
  }

  nav li {
    margin-bottom: 15px;
  }

  nav a {
    font-size: 18px;
  }

  main {
    padding: 20px;
  }
}

@media (max-width: 576px) {
  main h1 {
    font-size: 24px;
  }

  main h3 {
    font-size: 20px;
  }

  main p {
    font-size: 16px;
  }
}
</style>

</head>
<body>

<header>
  <img alt="SkyllX Technologies Logo" src="https://www.skyllx.com/courses/assets/image/logo_black.png" />
  <nav>
    <ul>
      <li class="active"><a href="index.jsp">Home</a></li>
      <li><a href="register">Register</a></li>
      <li><a href="signIn">Sign In</a></li>
      <li><a href="AboutUs.jsp">About Us</a></li>
      <li><a href="#">Services</a></li>
      <li><a href="ContactUs.jsp">Contact Us</a></li>
      <li><a>Welcome, Guest</a></li>
      <li><img id="profilePic" src=""/></li>
    </ul>
  </nav>
</header>

<main>
  <h1>Welcome to SkyllX Technologies Pvt Ltd</h1>
  <h3>${sign_in_success}${signUpSuccess}</h3>
  <h3 style="color: orange;">${signUpEmailConfirmation}</h3>
  <p>Welcome to SkyllX Technologies Pvt Ltd, your trusted partner for all your software development needs. 
     Our team of experienced developers is dedicated to delivering high-quality, scalable solutions that meet your unique business requirements.</p>
  <button>Get Started</button>
</main>

<footer>
  <p>&copy; 2025 SkyllX Technologies Pvt Ltd. All rights reserved.</p>
  <p>Developed by: Skyllx Team | <a href="https://www.skyllx.com" target="_blank">Visit our website</a></p>
</footer>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" 
  integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" 
  crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" 
  integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" 
  crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" 
  integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" 
  crossorigin="anonymous"></script>

<script type="text/javascript">
  var profilePicUrl = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";
  document.getElementById("profilePic").src = profilePicUrl;
</script>

</body>
</html>
