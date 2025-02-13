<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reviews</title>
</head>
<body>
    <h2>Leave a Review</h2>
    <form action="/reviews/add" method="post">
        <input type="hidden" name="propertyId" value="${propertyId}">
        Rating: <input type="number" name="rating" min="1" max="5"><br>
        Comment: <textarea name="comment"></textarea><br>
        <input type="submit" value="Submit Review">
    </form>
</body>
</html>
