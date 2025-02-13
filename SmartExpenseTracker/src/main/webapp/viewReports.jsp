<!DOCTYPE html>
<%@page import="java.util.Map"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Graphical Reports - Smart Expense Tracker</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
        h2 {
            color: #333;
        }
        canvas {
            max-width: 100%;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 18px;
            color: white;
            background: #007bff;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }
        .btn:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Category-wise Spending</h2>
        <canvas id="expenseChart"></canvas>
        <a href="expenses" class="btn">Back to Dashboard</a>
    </div>
    
    <%
        Map<String, Double> categoryWiseSpends = (Map<String, Double>) request.getAttribute("categoryWiseSpends");
        String labels = "[";
        String amounts = "[";
        if (categoryWiseSpends != null) {
            for (Map.Entry<String, Double> entry : categoryWiseSpends.entrySet()) {
                labels += "'" + entry.getKey() + "',";
                amounts += entry.getValue() + ",";
            }
            if (!categoryWiseSpends.isEmpty()) {
                labels = labels.substring(0, labels.length() - 1);
                amounts = amounts.substring(0, amounts.length() - 1);
            }
        }
        labels += "]";
        amounts += "]";
    %>
    
    <script>
        const ctx = document.getElementById('expenseChart').getContext('2d');
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: <%= labels %>,
                datasets: [{
                    label: 'Expenses by Category',
                    data: <%= amounts %>,
                    backgroundColor: 'rgba(54, 162, 235, 0.5)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: { beginAtZero: true }
                }
            }
        });
    </script>
</body>
</html>
