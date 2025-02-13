CREATE DATABASE rental_platform;
USE rental_platform;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('OWNER', 'RENTER') NOT NULL
);

CREATE TABLE properties (
    id INT PRIMARY KEY AUTO_INCREMENT,
    owner_id INT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    price_per_night DECIMAL(10,2) NOT NULL,
    available_from DATE,
    available_to DATE,
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE bookings (
    id INT PRIMARY KEY AUTO_INCREMENT,
    property_id INT,
    renter_id INT,
    start_date DATE,
    end_date DATE,
    status ENUM('PENDING', 'CONFIRMED', 'CANCELLED') DEFAULT 'PENDING',
    FOREIGN KEY (property_id) REFERENCES properties(id),
    FOREIGN KEY (renter_id) REFERENCES users(id)
);

CREATE TABLE reviews (
    id INT PRIMARY KEY AUTO_INCREMENT,
    property_id INT,
    renter_id INT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    FOREIGN KEY (property_id) REFERENCES properties(id),
    FOREIGN KEY (renter_id) REFERENCES users(id)
);

CREATE TABLE messages (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sender_id INT,
    receiver_id INT,
    message TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (receiver_id) REFERENCES users(id)
);