<%@page import="java.util.List"%>
<%@page import="com.skyllx.expense.model.Categories"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add Expense - Smart Expense Tracker</title>

<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
	text-align: center;
}

.container {
	max-width: 400px;
	margin: 100px auto;
	background: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
	color: #333;
}

select, input[type="number"] {
	width: 100%;
	padding: 10px;
	margin: 10px 0;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.btn {
	display: inline-block;
	width: 100%;
	padding: 10px;
	font-size: 18px;
	color: white;
	background: #007bff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.btn:hover {
	background: #0056b3;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Add Expense</h2>
		<form action="addExpense" method="post">
			<select name="category" required>
				<option value="">Select Category</option>
				<%
				for (Categories category : (List<Categories>) request.getAttribute("categories")) {
				%>
				<option value="<%=category.getName()%>"><%=category.getName()%></option>
				<%
				}
				%>
			</select> <input type="number" name="amount" placeholder="Amount" required>
			<button type="submit" class="btn">Add Expense</button>
		</form>
		<a href="expenses" class="btn" style="margin-top: 10px;">Back to
			Dashboard</a>
	</div>
</body>
</html>
