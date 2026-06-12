# Student Management REST API

Spring Boot 3 + MongoDB CRUD REST API for Student Management | Java 17 | Maven | Internship Assignment

## Project Overview

This project is a Student Management REST API built using Spring Boot 3 and MongoDB as part of a Java Developer Internship Assignment.
The API supports full CRUD operations (Create, Read, Update, Delete) on student records stored in a MongoDB database.

### Built to Demonstrate

* REST API design with Spring Boot
* MongoDB database integration
* Clean code architecture
* Input validation and error handling
* API testing using Postman

## Features

* Create, Read, Update, Delete Students
* Filter students by course
* Search students by name
* Input validation with proper error messages
* Duplicate email prevention
* Auto timestamps (createdAt, updatedAt)
* Consistent JSON response format
* Global exception handling

##  Architecture

```text
Controller  →  Service  →  Repository  →  MongoDB
     ↓             ↓
 Request DTO   Business Logic
 Response DTO  Exception Handling
```

## 🛠️ Technologies Used

| Technology          | Purpose                       |
| ------------------- | ----------------------------- |
| Spring Boot 3.2.0   | Core Framework                |
| Spring Data MongoDB | Database Layer                |
| Java 17             | Programming Language          |
| Jakarta Validation  | Input Validation              |
| Lombok              | Boilerplate Reduction         |
| Maven               | Build & Dependency Management |
| MongoDB             | NoSQL Database                |
| Postman             | API Testing                   |

## 📂 Project Structure

```text
src/main/java
├── controller
├── service
├── repository
├── model
├── dto
├── exception
└── config
```

## ⚙️ Database Configuration

Configure MongoDB in `application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/studentdb
spring.data.mongodb.database=studentdb
server.port=8080
```

Make sure MongoDB is installed and running locally.

## 🚀 Setup Instructions

### Prerequisites

* Java 17+
* Maven 3.8+
* MongoDB
* Git
* Postman

## API Endpoints

method       URL                              Action
POST       /api/v1/products                   Create
GET        /api/v1/products                   Read all
GET        /api/v1/products/{id}              Read one
GET        /api/v1/products?category=X        Filter
PUT        /api/v1/products/{id}              Update
DELETE     /api/v1/products/{id}              Delete

## 👨‍💻 Author

**Navnath Shinde**

Java Developer Internship Assignment Submission

 
