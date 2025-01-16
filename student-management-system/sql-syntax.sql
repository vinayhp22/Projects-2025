create database student_mngt_sys;

use student_mngt_sys;

CREATE TABLE users_signup (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    mobile BIGINT,
    password VARCHAR(255) NOT NULL,
    agreement BOOLEAN NOT NULL,
    attempts INT DEFAULT 0,
    locked BOOLEAN DEFAULT FALSE,
    reset_pwd BOOLEAN DEFAULT FALSE,
    createdBy VARCHAR(255),
    createdOn DATETIME,
    updatedBy VARCHAR(255),
    updatedOn DATETIME,
    profile_pic_name VARCHAR(255)
);

select * from users_signup;

CREATE TABLE technology (
    stockId BIGINT AUTO_INCREMENT PRIMARY KEY,
    id INT NOT NULL,
    name VARCHAR(255),
    language VARCHAR(255),
    version VARCHAR(10),
    owner VARCHAR(255),
    support_from VARCHAR(255),
    support_to VARCHAR(255),
    license VARCHAR(255),
    OS_type VARCHAR(255),
    open_source BOOLEAN,
    INDEX id_idx (id),
    CONSTRAINT fk_users_signup FOREIGN KEY (id)
        REFERENCES users_signup (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

select * from technology;

