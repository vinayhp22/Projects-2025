<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Orders</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
    <h1>View Orders</h1>
    <table>
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Customer Name</th>
                <th>Restaurant Name</th>
                <th>Items</th>
                <th>Total Price</th>
                <th>Order Date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.orderId}</td> <!-- Use the field name directly or the getter method -->
                    <td>${order.customerName}</td>
                    <td>${order.restaurantName}</td>
                    <td>${order.items}</td>
                    <td>${order.totalPrice}</td>
                    <td>${order.orderDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
