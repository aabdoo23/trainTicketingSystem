# trainTicketingSystem

The Train Ticketing System is a Java-based application designed with the Swing library to serve as GUI to provide a simple and efficient way for customers to purchase train tickets. The system allows users to search for available trains, select their desired route and travel dates, and purchase tickets online.

Features

Search for available trains based on the start and end stations and the travel date.
Book and purchase train tickets.
View train schedules and ticket prices.
Manage bookings and cancellations.
Store user information securely.
Getting Started

Clone the repository or download the project files.
Open the project in your preferred Java IDE.
Compile and run the project.
Dependencies

Java 8 or higher
MySQL database
Database Setup

Install MySQL and create a new database.
Create tables using the following SQL queries:
Users table:
CREATE TABLE Users (
id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
phone VARCHAR(20) NOT NULL,
address VARCHAR(100) NOT NULL
);
Trains table:
CREATE TABLE Trains (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
source_station VARCHAR(50) NOT NULL,
destination_station VARCHAR(50) NOT NULL,
departure_time TIME NOT NULL,
arrival_time TIME NOT NULL,
duration VARCHAR(10) NOT NULL,
price DECIMAL(10,2) NOT NULL
);
Bookings table:
CREATE TABLE Bookings (
id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT NOT NULL,
train_id INT NOT NULL,
booking_date DATE NOT NULL,
travel_date DATE NOT NULL,
no_of_tickets INT NOT NULL,
total_price DECIMAL(10,2) NOT NULL,
CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
CONSTRAINT fk_trains FOREIGN KEY (train_id) REFERENCES Trains(id) ON DELETE CASCADE
);
Update the MySQL connection details in the database.properties file.
Contributing
We welcome contributions to the Train Ticketing System project. If you would like to contribute, please follow these steps:

Fork the repository.
Create a new branch for your changes.
Make your changes and commit them.
Push your changes to your fork.
Submit a pull request.
License
The Train Ticketing System is released under the MIT License. See LICENSE for more information.
