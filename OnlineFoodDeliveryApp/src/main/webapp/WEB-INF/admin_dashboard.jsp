<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .container {
            width: 80%;
            margin: auto;
        }
        .section {
            margin: 20px 0;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .section h2 {
            margin: 0 0 10px;
            color: #555;
        }
        .section a {
            display: inline-block;
            margin: 10px 0;
            padding: 10px 20px;
            text-decoration: none;
            color: #fff;
            background-color: #007BFF;
            border-radius: 5px;
        }
        .section a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Admin Dashboard</h1>
    <div class="container">

        <!-- Section for Managing Restaurants -->
        <div class="section">
            <h2>Manage Restaurants</h2>
            <a href="addRestaurant">Add Restaurant</a>
            <a href="viewRestaurants">View All Restaurants</a>
        </div>

        <!-- Section for Managing Menu Items -->
        <div class="section">
            <h2>Manage Menu</h2>
            <a href="addMenu">Add Menu Item</a>
            <a href="viewMenus">View All Menu Items</a>
        </div>

        <!-- Section for Managing Orders -->
        <div class="section">
            <h2>Manage Orders</h2>
            <a href="viewOrders">View All Orders</a>
        </div>

        <!-- Section for Admin Account -->
        <div class="section">
            <h2>Admin Account</h2>
            <a href="logout">Logout</a>
        </div>

    </div>
</body>
</html>
