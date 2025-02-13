<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Reports - Smart Expense Tracker</title>
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
        canvas {
            max-width: 100%;
            height: 400px;
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
        <h2>Expense Reports</h2>
        <canvas id="expenseChart"></canvas>
        <script>
            fetch('/expenseData')
                .then(response => response.json())
                .then(data => {
                    const ctx = document.getElementById('expenseChart').getContext('2d');
                    new Chart(ctx, {
                        type: 'bar',
                        data: {
                            labels: data.categories,
                            datasets: [{
                                label: 'Expenses',
                                data: data.amounts,
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
                });
        </script>
        <a href="/download/csv" class="btn">Download CSV</a>
        <a href="/download/pdf" class="btn">Download PDF</a>
        <a href="expenses.jsp" class="btn">Back to Dashboard</a>
    </div>
</body>
</html>
