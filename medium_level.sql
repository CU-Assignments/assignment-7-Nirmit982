CREATE DATABASE Inventory;
USE Inventory;

CREATE TABLE Product (
    ProductID INT PRIMARY KEY,
    ProductName VARCHAR(100),
    Price DOUBLE,
    Quantity INT
);
