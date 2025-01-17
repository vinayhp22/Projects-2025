<!DOCTYPE html>
<html>
<head>
    <title>Add Menu Item</title>
</head>
<body>
    <h1>Add a New Menu Item</h1>
    <form action="addMenu" method="post">
        <label for="restaurantId">Restaurant ID:</label><br>
        <input type="number" id="restaurantId" name="restaurantId" required><br><br>

        <label for="itemName">Item Name:</label><br>
        <input type="text" id="itemName" name="itemName" required><br><br>

        <label for="description">Description:</label><br>
        <textarea id="description" name="description" required></textarea><br><br>

        <label for="price">Price:</label><br>
        <input type="number" step="0.01" id="price" name="price" required><br><br>

        <label for="isAvailable">Is Available:</label><br>
        <select id="isAvailable" name="isAvailable">
            <option value="true">Yes</option>
            <option value="false">No</option>
        </select><br><br>

        <button type="submit">Add Menu Item</button>
    </form>
</body>
</html>
