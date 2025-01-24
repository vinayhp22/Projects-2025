create database parking_rental_app;

use parking_rental_app;

CREATE TABLE admin_login (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    EMAIL_ID VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    CREATED_BY VARCHAR(255),
    UPDATED_BY VARCHAR(255),
    CREATED_DATE VARCHAR(255),
    UPDATED_DATE VARCHAR(255),
    LOGIN_TIME VARCHAR(255)
);

CREATE TABLE parking_details (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    LOCATION VARCHAR(255) NOT NULL,
    VEHICLE_TYPE VARCHAR(255) NOT NULL,
    ENGINE_TYPE VARCHAR(255) NOT NULL,
    CLASSIFICATION VARCHAR(255) NOT NULL,
    TERM VARCHAR(255) NOT NULL,
    PRICE INT NOT NULL,
    DISCOUNT VARCHAR(255)
);

CREATE TABLE user_registration (
    ID INT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    EMAIL_ID VARCHAR(255) NOT NULL,
    CONTACT_NO BIGINT NOT NULL,
    CREATED_BY VARCHAR(255),
    CREATED_DATE VARCHAR(255),
    UPDATED_BY VARCHAR(255),
    UPDATE_DATE VARCHAR(255),
    OTP INT,
    COUNT INT NOT NULL,
    STATUS VARCHAR(255),
    EXPIRE_ON TIME
);

CREATE TABLE user_parking_details (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    USERID_FK INT NOT NULL,
    VEHICLE_NO VARCHAR(20) NOT NULL,
    LOCATION VARCHAR(255) NOT NULL,
    VEHICLE_TYPE VARCHAR(50) NOT NULL,
    ENGINE_TYPE VARCHAR(50) NOT NULL,
    CLASSIFICATION VARCHAR(50) NOT NULL,
    TERM VARCHAR(50) NOT NULL,
    PRICE INT NOT NULL,
    DISCOUNT INT NOT NULL,
    TOTAL_AMT INT NOT NULL,
    FILE_NAME VARCHAR(255) NOT NULL,
    PAYMENT VARCHAR(50) NOT NULL,
    ORIGINAL_FN VARCHAR(255) NOT NULL,
    CONTENT_TYPE VARCHAR(50) NOT NULL,
    CREATED_DATE DATE NOT NULL,
    UPDATED_DATE DATE NOT NULL,
    IS_ACTIVE BOOLEAN NOT NULL,
    FOREIGN KEY (USERID_FK) REFERENCES user_registration(ID)
);


INSERT INTO admin_login (NAME, EMAIL_ID, PASSWORD, CREATED_BY, UPDATED_BY, CREATED_DATE, UPDATED_DATE, LOGIN_TIME)
VALUES 
('Vinay', 'vinay@skyllx.com', 'vinay@skyllx.com', 'Admin', 'Admin', '2025-01-12', '2025-01-12', '2025-01-12 08:30:00'),
('Sahana', 'sahana@skyllx.com', 'sahana@skyllx.com', 'Admin', 'Admin', '2025-01-10', '2025-01-10', '2025-01-10 09:00:00');

INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('MG Road, Bengaluru', 'Car', 'Petrol', 'Compact', 'Monthly', 15000, '10%'),
('Koramangala, Bengaluru', 'Bike', 'Electric', 'Standard', 'Hourly', 50, '5%'),
('Indiranagar, Bengaluru', 'Car', 'Diesel', 'Luxury', 'Daily', 2000, '0%'),
('Whitefield, Bengaluru', 'Car', 'Petrol', 'SUV', 'Weekly', 8000, '15%');

INSERT INTO user_registration (ID, NAME, EMAIL_ID, CONTACT_NO, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATE_DATE, OTP, COUNT, STATUS, EXPIRE_ON)
VALUES
(1, 'Rajesh Kumar', 'rajesh.kumar@gmail.com', 9876543210, 'Admin', '2025-01-11', 'Admin', '2025-01-11', 123456, 1, 'Active', '12:30:00'),
(2, 'Aisha Patel', 'aisha.patel@yahoo.com', 9988776655, 'Admin', '2025-01-10', 'Admin', '2025-01-10', 654321, 2, 'Inactive', '14:00:00'),
(3, 'Vijay Prasad', 'vijay.prasad@outlook.com', 9876543211, 'Admin', '2025-01-09', 'Admin', '2025-01-09', 112233, 3, 'Active', '16:30:00');

INSERT INTO user_parking_details (USERID_FK, VEHICLE_NO, LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT, TOTAL_AMT, FILE_NAME, PAYMENT, ORIGINAL_FN, CONTENT_TYPE, CREATED_DATE, UPDATED_DATE, IS_ACTIVE)
VALUES
(1, 'KA01AB1234', 'MG Road, Bengaluru', 'Car', 'Petrol', 'Compact', 'Monthly', 15000, 10, 13500, 'parking_pass_ka01ab1234.jpg', 'Paid', 'original_parking_pass.jpg', 'image/jpeg', '2025-01-12', '2025-01-12', TRUE),
(2, 'KA03XY5678', 'Koramangala, Bengaluru', 'Bike', 'Electric', 'Standard', 'Hourly', 50, 5, 47.5, 'parking_ticket_ka03xy5678.jpg', 'Paid', 'original_ticket.jpg', 'image/jpeg', '2025-01-10', '2025-01-10', TRUE),
(3, 'KA05LM9876', 'Indiranagar, Bengaluru', 'Car', 'Diesel', 'Luxury', 'Daily', 2000, 0, 2000, 'parking_pass_ka05lm9876.jpg', 'Paid', 'original_parking_pass.jpg', 'image/jpeg', '2025-01-09', '2025-01-09', TRUE);

select * from admin_login;
select * from parking_details;
select * from user_registration;
select * from user_parking_details;

truncate parking_details;

-- RajajiNagar (Location), 2-Wheeler, Normal Engine, Bike Classification, 1 Day Term
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('RajajiNagar', '2-Wheeler', 'Normal', 'Bike', '1_day', 50, 10);

-- RajajiNagar (Location), 2-Wheeler, Electric Engine, Bike Classification, 7 Days Term
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('RajajiNagar', '2-Wheeler', 'Electric', 'Bike', '7_days', 60, 15);

-- RajajiNagar (Location), 4-Wheeler, Normal Engine, Car Classification, 30 Days Term
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('RajajiNagar', '4-Wheeler', 'Normal', 'BMW', '30_days', 2000, 20);

-- RajajiNagar (Location), 4-Wheeler, Electric Engine, Car Classification, 180 Days Term
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('RajajiNagar', '4-Wheeler', 'Electric', 'Renault', '180_days', 3000, 25);

-- E.city (Location), 2-Wheeler, Normal Engine, Bike Classification, 90 Days Term
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('E.city', '2-Wheeler', 'Normal', 'Bike', '90_days', 100, 12);

-- E.city (Location), 2-Wheeler, Electric Engine, Bike Classification, 1 Day Term
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('E.city', '2-Wheeler', 'Electric', 'Bike', '1_day', 70, 10);

-- E.city (Location), 4-Wheeler, Normal Engine, Car Classification, 360 Days Term
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('E.city', '4-Wheeler', 'Normal', 'Skoda', '360_days', 4000, 18);

-- E.city (Location), 4-Wheeler, Electric Engine, Car Classification, 7 Days Term
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('E.city', '4-Wheeler', 'Electric', 'Kia', '7_days', 3500, 10);

-- VijayaNagar (Location), 2-Wheeler, Normal Engine, Bike Classification, 30 Days Term
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('VijayaNagar', '2-Wheeler', 'Normal', 'Bike', '30_days', 150, 5);

-- VijayaNagar (Location), 4-Wheeler, Normal Engine, Car Classification, 15 Days Term
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('VijayaNagar', '4-Wheeler', 'Normal', 'Toyota', '15_days', 2500, 15);

-- VijayaNagar (Location), 4-Wheeler, Electric Engine, Car Classification, 90 Days Term
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('VijayaNagar', '4-Wheeler', 'Electric', 'Toyota', '90_days', 3500, 12);

-- BTM (Location), 2-Wheeler, Electric Engine, Bike Classification, 180 Days Term
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('BTM', '2-Wheeler', 'Electric', 'Bike', '180_days', 80, 8);

-- BTM (Location), 4-Wheeler, Normal Engine, Car Classification, 360 Days Term
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('BTM', '4-Wheeler', 'Normal', 'BMW', '360_days', 5000, 20);

-- BTM (Location), 4-Wheeler, Electric Engine, Car Classification, 7 Days Term
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('BTM', '4-Wheeler', 'Electric', 'Kia', '7_days', 3200, 15);

-- RajajiNagar (Location), 4-Wheeler, Normal Engine, Car Classification, 30 Days Term (Renault)
INSERT INTO parking_details (LOCATION, VEHICLE_TYPE, ENGINE_TYPE, CLASSIFICATION, TERM, PRICE, DISCOUNT)
VALUES
('RajajiNagar', '4-Wheeler', 'Normal', 'Renault', '30_days', 2700, 18);

select * from admin_login;
select * from parking_details;
select * from user_registration;
select * from user_parking_details;