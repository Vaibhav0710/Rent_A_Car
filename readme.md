# Rent_A_Car - Car Rental System

## Overview
Rent_A_Car is a menu-driven, console-based Car Rental System developed using Core Java and JDBC. This project allows administrators to manage cars and customers to rent available cars efficiently.

## Features
### Admin Features
- Add new cars
- Remove cars
- View all cars
- View all bookings
- Cancel a booking
- View all customers
- Logout

### Customer Features
- Register and log in
- View available cars for rent
- Book a car
- View booking history
- Logout

## Tech Stack
- **Programming Language**: Java (Core Java, JDBC)
- **Database**: MySQL
- **Build Tool**: Maven
- **IDE**: IntelliJ IDEA

## Database Schema
### Tables:
#### 1. `car`
| Field        | Type          | Constraints          |
|-------------|--------------|----------------------|
| car_id       | int          | PRIMARY KEY, AUTO_INCREMENT |
| model        | varchar(50)  | NOT NULL             |
| brand        | varchar(50)  | NOT NULL             |
| year         | int          | NOT NULL             |
| rental_price | decimal(10,2) | NOT NULL             |
| available    | tinyint(1)    | DEFAULT 1           |
| is_available | tinyint(1)    | DEFAULT 1           |

#### 2. `admins`
| Field     | Type         | Constraints             |
|----------|-------------|------------------------|
| id       | int         | PRIMARY KEY, AUTO_INCREMENT |
| username | varchar(50) | UNIQUE, NOT NULL        |
| password | varchar(100) | NOT NULL              |

#### 3. `customer`
| Field       | Type         | Constraints             |
|------------|-------------|------------------------|
| customer_id | int         | PRIMARY KEY, AUTO_INCREMENT |
| name        | varchar(100) | NOT NULL             |
| email       | varchar(100) | UNIQUE, NOT NULL     |
| phone       | varchar(15)  | NOT NULL             |
| password    | varchar(100) | NULL                 |

#### 4. `booking`
| Field       | Type          | Constraints             |
|------------|--------------|------------------------|
| booking_id  | int          | PRIMARY KEY, AUTO_INCREMENT |
| customer_id | int          | FOREIGN KEY (customer_id) REFERENCES customer(customer_id) |
| car_id      | int          | FOREIGN KEY (car_id) REFERENCES car(car_id) |
| rental_date | date         | NOT NULL             |
| return_date | date         | NULL                 |
| total_cost  | decimal(10,2) | NULL                 |

## Setup Instructions
### 1. Clone the repository
```sh
$ git clone https://github.com/Vaibhav0710/Rent_A_Car.git
$ cd Rent_A_Car
```

### 2. Configure Database
- Create a database in MySQL:
```sql
CREATE DATABASE carrentaldb;
USE carrentaldb;

-- Create Database
CREATE DATABASE carrentaldb;
USE carrentaldb;

-- Create Admins Table
CREATE TABLE admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Create Customers Table
CREATE TABLE customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15) NOT NULL,
    password VARCHAR(100)
);

-- Create Cars Table
CREATE TABLE car (
    car_id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(50) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    rental_price DECIMAL(10,2) NOT NULL,
    available TINYINT(1) DEFAULT 1,
    is_available TINYINT(1) DEFAULT 1
);

-- Create Bookings Table
CREATE TABLE booking (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    car_id INT NOT NULL,
    rental_date DATE NOT NULL,
    return_date DATE NULL,
    total_cost DECIMAL(10,2) NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (car_id) REFERENCES car(car_id)
);
```
- Import the schema and tables into MySQL.

### 3. Update `DBUtil.java`
- Modify database credentials in `DBUtil.java`:
```java
private static final String URL = "jdbc:mysql://localhost:3306/carrentaldb";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

### 4. Run the Project
- Compile and run the project in IntelliJ IDEA or any Java IDE.
- Execute the `main` method in `App.java` to start the application.

## Future Enhancements
- Implement a GUI using Java Swing or a web-based UI
- Add payment integration
- Implement role-based access control
- Enhance search and filter options for available cars

## Author
**Vaibhav Jain**

