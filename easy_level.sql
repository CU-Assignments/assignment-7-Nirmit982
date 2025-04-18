CREATE DATABASE Company;
USE Company;

CREATE TABLE Employee (
    EmpID INT PRIMARY KEY,
    Name VARCHAR(100),
    Salary DOUBLE
);

INSERT INTO Employee VALUES (101, 'Alice', 50000.0), (102, 'Bob', 60000.0), (103, 'Charlie', 70000.0);
