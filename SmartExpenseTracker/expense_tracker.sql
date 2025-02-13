CREATE DATABASE expense_tracker;

USE expense_tracker;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255)
);

CREATE TABLE categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO categories (name) VALUES 
('Food'),
('Travel'),
('Shopping'),
('Entertainment'),
('Bills'),
('Health'),
('Education'),
('Miscellaneous');

CREATE TABLE expenses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    description VARCHAR(255),
    date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

CREATE TABLE budgets (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    monthly_limit DECIMAL(10,2) NOT NULL,
    month_year DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

CREATE TABLE recurring_transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    description VARCHAR(255),
    frequency ENUM('WEEKLY', 'MONTHLY', 'YEARLY') NOT NULL,
    next_due_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

CREATE TABLE reports (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Insert users (passwords should be hashed in a real application)
INSERT INTO users (username, password) VALUES 
('vinay', 'password123'),
('rudresh', 'securepass');

-- Insert expenses
INSERT INTO expenses (user_id, category_id, amount, description, date) VALUES
(1, 1, 250.00, 'Groceries', '2024-02-01'),
(1, 2, 100.00, 'Bus fare', '2024-02-02'),
(2, 3, 500.00, 'Online shopping', '2024-02-03');

-- Insert budgets
INSERT INTO budgets (user_id, category_id, amount, month_year) VALUES
(1, 1, 2000.00, '2024-02-01'),
(1, 2, 1000.00, '2024-02-01');

-- Insert recurring transactions
INSERT INTO recurring_transactions (user_id, category_id, amount, description, frequency, next_due_date) VALUES
(1, 5, 500.00, 'Netflix Subscription', 'MONTHLY', '2024-02-15'),
(2, 5, 1000.00, 'Electricity Bill', 'MONTHLY', '2024-02-20');


