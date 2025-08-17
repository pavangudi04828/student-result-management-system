-- Student Result Management System Database Setup
CREATE DATABASE student_db;
USE student_db;

-- Create students table
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    student_class VARCHAR(20),
    gender VARCHAR(10)
);

-- Create marks table  
CREATE TABLE marks (
    mark_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    subject VARCHAR(50),
    marks INT,
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);

-- Create result summary table
CREATE TABLE result_summary (
    Name VARCHAR(100),
    Student_ID INT PRIMARY KEY,
    Grade VARCHAR(5),
    Maths INT,
    Science INT,
    English INT,
    Social INT,
    Total INT,
    Average DECIMAL(5,2)
);
