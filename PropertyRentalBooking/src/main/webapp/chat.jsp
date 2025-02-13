<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chat</title>
</head>
<body>
    <h2>Chat with ${receiverUsername}</h2>
    <form action="/chat/send" method="post">
        <input type="hidden" name="receiverId" value="${receiverId}">
        <textarea name="message"></textarea><br>
        <input type="submit" value="Send Message">
    </form>
    <h3>Messages</h3>
    <c:forEach var="message" items="${messages}">
        <p>${message.senderUsername}: ${message.text}</p>
    </c:forEach>
</body>
</html>
