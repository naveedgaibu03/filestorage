# File Storage System

## Overview

This project is a file storage system with features such as file upload/download, user authentication, file sharing, and versioning. The project is built using Java, Spring Boot, Spring Security, Thymeleaf, and Hibernate.

## Features

- User Authentication: Register and login functionality.
- File Upload:Users can upload files.
- File Download: Users can download their uploaded files.
- File Sharing: Users can share files with other users.
- File Versioning: Version control for uploaded files.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Hibernate
- Thymeleaf
- Bootstrap (for UI styling)
- JPA
- H2 Database

## Prerequisites

- JDK 17 or later
- Maven 3.6.3 or later
- Git

## Setup Instructions

1. Clone the repository:
   ----bash
   git clone https://github.com/naveedgaibu03/filestorage.git
   cd filestorage
2. Install dependencies and package the application:
   ---mvn clean install

3. Run the application:
   ---mvn spring-boot:run


4. Access the application:
   Open your web browser and go to http://localhost:8080.

   You can register a new user or log in with an existing user.

Database Configuration
     The application uses H2 database for simplicity. The database schema and initial data are configured using the data.sql 
      file located in src/main/resources.
      



1. How to Use
    Register a New User:
    Navigate to http://localhost:8080/register to create a new user account.

2.Login:
  Navigate to http://localhost:8080/login to log in with your credentials.

3. Upload Files:
   After logging in, navigate to http://localhost:8080/files/upload to upload files.

4. View Files:
  Navigate to http://localhost:8080/files to view the list of uploaded files.

5. Download Files:
   Click on a file in the list to download it.


Bootstrap and jQuery
   Bootstrap:
   bootstrap.min.css is located in the src/main/resources/static/css directory.

jQuery:
   jquery.slim.min.js is located in the src/main/resources/static/js directory.




This README.md file provides clear instructions for setting up and using the file storage system project, along with details on the project structure and how to configure the database.

