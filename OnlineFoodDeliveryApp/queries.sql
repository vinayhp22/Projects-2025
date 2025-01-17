Create database online_food_delivery;

use online_food_delivery;

CREATE TABLE online_food_delivery.User (
UserID INT AUTO_INCREMENT PRIMARY KEY,
Username VARCHAR(255) NOT NULL,
Password VARCHAR(255) NOT NULL,  
Email VARCHAR(255) NOT NULL UNIQUE,
Address TEXT,
Role ENUM('Customer', 'RestaurantAdmin', 'SystemAdmin', 'DeliveryAgent') NOT NULL,
CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
LastLoginDate DATETIME
);

CREATE TABLE Restaurant (
RestaurantID INT AUTO_INCREMENT PRIMARY KEY,
Name VARCHAR(255) NOT NULL,
CuisineType VARCHAR(100),
DeliveryTime INT,  
Address TEXT,
AdminUserID INT,
Rating DECIMAL(3, 2),
IsActive BOOLEAN DEFAULT TRUE,
ImagePath VARCHAR(255),
FOREIGN KEY (AdminUserID) REFERENCES User(UserID)
);

CREATE TABLE Menu (
MenuID INT AUTO_INCREMENT PRIMARY KEY,
RestaurantID INT,
ItemName VARCHAR(255) NOT NULL,
Description TEXT,
Price DECIMAL(10, 2) NOT NULL,
IsAvailable BOOLEAN DEFAULT TRUE,
ImagePath VARCHAR(255), 
FOREIGN KEY (RestaurantID) REFERENCES Restaurant(RestaurantID)
);

CREATE TABLE OrderTable (
OrderID INT AUTO_INCREMENT PRIMARY KEY,
UserID INT,
RestaurantID INT,
OrderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
TotalAmount DECIMAL(10, 2) NOT NULL,
Status ENUM('Pending', 'Delivered', 'Cancelled') NOT NULL,
PaymentMethod ENUM('UPI', 'COD', 'debit card', 'credit card'),
FOREIGN KEY (UserID) REFERENCES User(UserID),
FOREIGN KEY (RestaurantID) REFERENCES Restaurant(RestaurantID)
);

CREATE TABLE OrderItem (
OrderItemID INT AUTO_INCREMENT PRIMARY KEY,
OrderID INT,
MenuID INT,
Quantity INT NOT NULL,
ItemTotal DECIMAL(10, 2) NOT NULL,
FOREIGN KEY (OrderID) REFERENCES OrderTable(OrderID),
FOREIGN KEY (MenuID) REFERENCES Menu(MenuID)
);

CREATE TABLE OrderHistory (
OrderHistoryID INT AUTO_INCREMENT PRIMARY KEY,
UserID INT,
OrderID INT,
OrderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
TotalAmount DECIMAL(10, 2),
Status ENUM('Delivered', 'Cancelled', 'Returned') NOT NULL,
FOREIGN KEY (UserID) REFERENCES User(UserID),
FOREIGN KEY (OrderID) REFERENCES OrderTable(OrderID)
);

-- Customer user
INSERT INTO User (Username, Password, Email, Address, Role, CreatedDate, LastLoginDate)
VALUES ('vinay', 'vinay', 'john@example.com', 'BTM Layout, Bengaluru', 'Customer', NOW(), NOW());

-- Restaurant Admin user
INSERT INTO User (Username, Password, Email, Address, Role, CreatedDate, LastLoginDate)
VALUES ('admin_btm_spice', 'admin123', 'admin_btm_spice@example.com', 'BTM Layout, Bengaluru', 'RestaurantAdmin', NOW(), NOW());

-- System Admin user
INSERT INTO User (Username, Password, Email, Address, Role, CreatedDate, LastLoginDate)
VALUES ('sys_admin', 'adminSys123', 'sys_admin@example.com', 'HSR Layout, Bengaluru', 'SystemAdmin', NOW(), NOW());

-- Delivery Agent user
INSERT INTO User (Username, Password, Email, Address, Role, CreatedDate, LastLoginDate)
VALUES ('delivery_agent1', 'delivery123', 'delivery1@example.com', 'HSR Layout, Bengaluru', 'DeliveryAgent', NOW(), NOW());

-- Customer user
INSERT INTO User (Username, Password, Email, Address, Role, CreatedDate, LastLoginDate)
VALUES ('sahana', 'sahana', 'jane@example.com', 'HSR Layout, Bengaluru', 'Customer', NOW(), NOW());

-- Restaurants in BTM Layout
INSERT INTO Restaurant (Name, CuisineType, DeliveryTime, Address, AdminUserID, Rating, IsActive, ImagePath)
VALUES ('BTM Spice Hub', 'North Indian', 30, 'BTM Layout, 2nd Stage, Bengaluru', 1, 4.5, TRUE, '/images/btm_spice_hub.jpg');

INSERT INTO Restaurant (Name, CuisineType, DeliveryTime, Address, AdminUserID, Rating, IsActive, ImagePath)
VALUES ('Coastal Feast', 'Seafood', 25, 'BTM Layout, 1st Stage, Bengaluru', 2, 4.6, TRUE, '/images/coastal_feast.jpg');

INSERT INTO Restaurant (Name, CuisineType, DeliveryTime, Address, AdminUserID, Rating, IsActive, ImagePath)
VALUES ('Cafe Delight', 'Cafe', 20, 'BTM Layout, Near Udupi Garden, Bengaluru', 3, 4.4, TRUE, '/images/cafe_delight.jpg');

-- Restaurants in HSR Layout
INSERT INTO Restaurant (Name, CuisineType, DeliveryTime, Address, AdminUserID, Rating, IsActive, ImagePath)
VALUES ('HSR Grill House', 'Barbecue', 35, 'HSR Layout, Sector 1, Bengaluru', 4, 4.3, TRUE, '/images/hsr_grill_house.jpg');

INSERT INTO Restaurant (Name, CuisineType, DeliveryTime, Address, AdminUserID, Rating, IsActive, ImagePath)
VALUES ('Authentic Andhra', 'South Indian', 25, 'HSR Layout, Sector 3, Bengaluru', 5, 4.7, TRUE, '/images/authentic_andhra.jpg');

select * from Restaurant;

-- Insert Menus --
-- BTM Spice Hub (RestaurantID: 1) --
INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (1, 'Butter Chicken', 'Creamy tomato-based curry with tender chicken', 300.00, TRUE, '/images/butter_chicken.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (1, 'Paneer Tikka', 'Grilled paneer cubes with spices', 250.00, TRUE, '/images/paneer_tikka.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (1, 'Chicken Biryani', 'Aromatic basmati rice with chicken and spices', 280.00, TRUE, '/images/chicken_biryani.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (1, 'Tandoori Roti', 'Whole wheat bread cooked in a tandoor', 40.00, TRUE, '/images/tandoori_roti.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (1, 'Gulab Jamun', 'Traditional Indian dessert soaked in syrup', 60.00, TRUE, '/images/gulab_jamun.jpg');

-- Coastal Feast (RestaurantID: 2) --
INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (2, 'Prawn Curry', 'Spicy prawn curry with coconut milk', 350.00, TRUE, '/images/prawn_curry.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (2, 'Fish Fry', 'Crispy fried fish with South Indian spices', 280.00, TRUE, '/images/fish_fry.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (2, 'Crab Masala', 'Rich and flavorful crab curry', 400.00, TRUE, '/images/crab_masala.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (2, 'Rice and Rasam', 'Steamed rice with tangy South Indian rasam', 150.00, TRUE, '/images/rice_rasam.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (2, 'Tender Coconut Payasam', 'Sweet coconut milk dessert', 100.00, TRUE, '/images/coconut_payasam.jpg');


-- Cafe Delight (RestaurantID: 3) --
INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (3, 'Veg Sandwich', 'Grilled sandwich with fresh vegetables', 120.00, TRUE, '/images/veg_sandwich.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (3, 'Cappuccino', 'Freshly brewed coffee with foam', 150.00, TRUE, '/images/cappuccino.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (3, 'Chocolate Muffin', 'Rich chocolate muffin', 90.00, TRUE, '/images/chocolate_muffin.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (3, 'Pasta Alfredo', 'Creamy white sauce pasta', 200.00, TRUE, '/images/pasta_alfredo.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (3, 'Fruit Salad', 'Seasonal fruits served fresh', 130.00, TRUE, '/images/fruit_salad.jpg');

-- HSR Grill House (RestaurantID: 4) --
INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (4, 'Chicken Tikka', 'Grilled chicken marinated with spices', 300.00, TRUE, '/images/chicken_tikka.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (4, 'Mutton Seekh Kebab', 'Spiced minced meat skewers', 350.00, TRUE, '/images/mutton_seekh_kebab.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (4, 'Grilled Paneer', 'Tandoori grilled paneer', 280.00, TRUE, '/images/grilled_paneer.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (4, 'Garlic Naan', 'Soft Indian bread with garlic flavor', 50.00, TRUE, '/images/garlic_naan.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (4, 'Phirni', 'Traditional creamy rice dessert', 120.00, TRUE, '/images/phirni.jpg');


-- Authentic Andhra (RestaurantID: 5) --
INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (5, 'Gongura Chicken', 'Tangy and spicy Andhra-style chicken curry', 320.00, TRUE, '/images/gongura_chicken.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (5, 'Andhra Veg Thali', 'Complete vegetarian meal with multiple curries', 250.00, TRUE, '/images/andhra_veg_thali.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (5, 'Natu Kodi Biryani', 'Authentic country chicken biryani', 400.00, TRUE, '/images/natu_kodi_biryani.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (5, 'Pesarattu', 'Green gram dosa with ginger chutney', 180.00, TRUE, '/images/pesarattu.jpg');

INSERT INTO Menu (RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath)
VALUES (5, 'Bobbatlu', 'Sweet stuffed flatbread', 90.00, TRUE, '/images/bobbatlu.jpg');

select * from Menu;

select * from Restaurant;
desc Restaurant;
update Restaurant set ImagePath='btm_spice_hub.jpg' where RestaurantID = 1;