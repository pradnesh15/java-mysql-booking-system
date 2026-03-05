# Bus Ticket Booking System (Java + MySQL)

A **console-based Bus Ticket Booking System** built using **Java, JDBC, and MySQL**.
The application allows users to log in, view available seats for a bus, and book tickets while ensuring seat availability.

This project demonstrates **database integration, DAO design pattern, and seat booking logic**.

---

# Features

* User login authentication using MySQL
* Bus seat booking system
* Display available seats for a selected bus
* Prevent double booking of seats
* Database-driven booking system
* DAO-based modular structure

---

# Project Structure

```
BusTicketBooking
│
├── dao
│   ├── BookingDAO.java      # Handles ticket booking logic
│   ├── SeatDAO.java         # Displays seat availability
│   └── UserDAO.java         # Handles user login authentication
│
├── db
│   └── DBConnection.java    # MySQL database connection
│
├── main
│   └── BusTicketBooking.java # Main application entry point
│
├── model
│   └── Seat.java             # Seat model class
│
└── README.md
```

---

# Technologies Used

* Java
* JDBC
* MySQL
* SQL
* VS Code
* Git & GitHub

---

# Database Setup

Create the database:

```sql
CREATE DATABASE busbooking;
USE busbooking;
```

### Users Table

```sql
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50)
);
```

### Buses Table

```sql
CREATE TABLE buses (
    bus_id INT PRIMARY KEY,
    bus_name VARCHAR(100),
    total_seats INT
);
```

### Bookings Table

```sql
CREATE TABLE bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    bus_id INT,
    seatNum INT,
    passenger_name VARCHAR(50),
    passenger_phone VARCHAR(15),
    booking_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (bus_id) REFERENCES buses(bus_id)
);
```

---

# How the System Works

### 1. User Login

Users log in using credentials stored in the `users` table.

```
SELECT * FROM users
WHERE username = ? AND password = ?
```

If authentication succeeds, the system returns the **user_id**.

---

### 2. Display Available Seats

The system retrieves all booked seats for the selected bus:

```
SELECT seatNum
FROM bookings
WHERE bus_id = ?
```

Seats from **1–20** are compared with booked seats to display availability.

---

### 3. Book Ticket

Before booking, the system verifies that the seat is not already booked.

```
SELECT *
FROM bookings
WHERE seatNum = ? AND bus_id = ?
```

If the seat is available, the booking is inserted:

```
INSERT INTO bookings
(user_id, bus_id, seatNum, passenger_name, passenger_phone)
VALUES (?, ?, ?, ?, ?)
```

---

# Example Console Flow

```
Welcome to the Bus Booking System

Login
Enter username: user1
Enter password: ****

Login Successful

Enter Bus ID: 101

Available Seats:
1 2 3 4 5 6 7 8 9 10 ...

Enter Seat Number: 5
Enter Passenger Name: Rahul
Enter Phone: 9876543210

Seat booked successfully
```

---

# Key Concepts Implemented

* JDBC database connectivity
* PreparedStatement for SQL queries
* DAO (Data Access Object) design pattern
* Seat availability logic
* Preventing duplicate seat booking

---

# Future Improvements

* User registration system
* Admin dashboard for managing buses
* Ticket cancellation feature
* Payment integration
* GUI using JavaFX or Spring Boot web interface
* REST API based architecture

---

# Author

Pradnesh Deshpande

---

# License

This project is for learning and educational purposes.
