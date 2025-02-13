<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Smart Expense Tracker</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            text-align: center;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 18px;
            color: white;
            background: #007bff;
            text-decoration: none;
            border-radius: 5px;
            margin: 10px;
        }
        .btn:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to Your Dashboard</h1>
        <p>Manage your expenses, set budgets, and track your spending.</p>
        <a href="addExpense.jsp" class="btn">Add Expense</a>
        <a href="viewReports.jsp" class="btn">View Reports</a>
        <a href="setBudget.jsp" class="btn">Set Budget</a>
        <a href="logout" class="btn">Logout</a>
    </div>
</body>
</html>
