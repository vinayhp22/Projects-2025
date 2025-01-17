<!DOCTYPE html>
<html>
<head>
    <title>Add Restaurant</title>
</head>
<body>
    <h1>Add a New Restaurant</h1>
    <form action="addRestaurant" method="post">
        <label for="name">Restaurant Name:</label><br>
        <input type="text" id="name" name="name" required><br><br>

        <label for="cuisineType">Cuisine Type:</label><br>
        <input type="text" id="cuisineType" name="cuisineType" required><br><br>

        <label for="address">Address:</label><br>
        <textarea id="address" name="address" required></textarea><br><br>

        <label for="deliveryTime">Delivery Time (in minutes):</label><br>
        <input type="number" id="deliveryTime" name="deliveryTime" required><br><br>

        <label for="adminUserId">Admin User ID:</label><br>
        <input type="number" id="adminUserId" name="adminUserId" required><br><br>

        <button type="submit">Add Restaurant</button>
    </form>
</body>
</html>
