-- ============================================================
-- Profile Management System - Database Setup Script
-- Run these commands step by step in MySQL Workbench
-- ============================================================

-- Step 1: Create the database
CREATE DATABASE user;

-- Step 2: Select the database to use
USE user;

-- Step 3: Create the profile table
-- id         → auto-increments so MySQL assigns it automatically
-- name       → stores the user's full name
-- email      → stores the user's email address
-- password   → stores the user's password

CREATE TABLE profile (
    id       INT PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(100),
    email    VARCHAR(100),
    password VARCHAR(100)
);

-- Step 4: Verify the table was created
SHOW TABLES;

-- Step 5: (Optional) Check the table structure
DESCRIBE profile;

-- ============================================================
-- After running the Java program, you can verify inserted data
-- by running the SELECT query below in MySQL Workbench:
-- ============================================================

-- View all inserted profiles
SELECT * FROM profile;
