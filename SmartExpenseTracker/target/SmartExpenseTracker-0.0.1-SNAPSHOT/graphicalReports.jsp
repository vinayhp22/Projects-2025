<!DOCTYPE html>
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
        <h2>Spending Trends</h2>
        <canvas id="expenseChart"></canvas>
        <a href="expenses.jsp" class="btn">Back to Dashboard</a>
    </div>
    <script>
        fetch('/api/expenseData') // Assume this API returns JSON data
            .then(response => response.json())
            .then(data => {
                const ctx = document.getElementById('expenseChart').getContext('2d');
                new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: data.categories,
                        datasets: [{
                            data: data.amounts,
                            backgroundColor: ['#ff6384', '#36a2eb', '#ffce56', '#4bc0c0', '#9966ff']
                        }]
                    }
                });
            });
    </script>
</body>
</html>
