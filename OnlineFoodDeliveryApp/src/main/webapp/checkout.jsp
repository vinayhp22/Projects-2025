<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" href="css/form.css">
</head>
<body>
    <h2>Checkout</h2>
    <form action="checkout" method="post">
        <label for="address">Delivery Address:</label>
        <textarea id="address" name="address" required></textarea><br><br>

        <label>Payment Method:</label>
        <select name="paymentMethod">
            <option value="UPI">UPI</option>
            <option value="COD">Cash on Delivery</option>
            <option value="DEBIT_CARD">Debit Card</option>
            <option value="CREDIT_CARD">Credit Card</option>
        </select><br><br>

        <input type="submit" value="Place Order">
    </form>
</body>
</html>