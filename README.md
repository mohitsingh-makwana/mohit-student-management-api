# Student Management API

A backend REST API built using Spring Boot for managing academic operations such as students, teachers, departments, classrooms, courses, assignments, notices, notes, submissions, and marks.

The main purpose of this project is to provide a centralized backend system for educational institutions while implementing real-world backend development concepts using Java and Spring Boot.

---

## 📌 Project Overview

Student Management API is a backend application designed to manage academic and institutional data through RESTful APIs.

The application provides APIs for managing:

- Departments
- Classrooms
- Students
- Teachers
- Coordinators
- Courses
- Notices
- Notes
- Assignments
- Assignment submissions
- Marks

The project follows a layered architecture to maintain separation of concerns and improve code maintainability.

Along with CRUD operations, the project implements real-world backend concepts such as JWT authentication, DTOs, mapper classes, validation, global exception handling, logging, pagination, Swagger/OpenAPI documentation, and JPA/Hibernate relationships.

---

## 🚀 Key Features

- Student Management
- Teacher Management
- Department Management
- Classroom Management
- Coordinator Management
- Course Management
- Notice Management
- Notes Management
- Assignment Management
- Assignment Submission Management
- Marks Management
- JWT-based Authentication
- BCrypt Password Hashing
- Input Validation
- Global Exception Handling
- DTO Pattern
- Mapper Pattern
- Pagination
- Logging
- Swagger/OpenAPI Documentation
- Stateless Authentication
- JPA/Hibernate Entity Relationships

---

## 🛠️ Technology Stack

| Technology | Purpose |
|------------|---------|
| Java | Programming Language |
| Spring Boot | Backend Framework |
| Spring Data JPA | Database Access |
| Hibernate | ORM |
| MySQL | Relational Database |
| Spring Security | Application Security |
| JWT | Stateless Authentication |
| BCrypt | Password Hashing |
| Swagger/OpenAPI | API Documentation |
| Maven | Dependency Management |
| Git | Version Control |
| GitHub | Source Code Management |

---

# 🏗️ Architecture

The application follows a **Layered Architecture** to maintain separation of concerns and improve maintainability.
                        Client ->  Controller -> Service -> Repository -> MySQL
                    
Supporting Components
 DTO
 Mapper
 Security
 Validation
 Exception Handling
 Logging
Main Layers

Controller Layer

Handles HTTP requests and responses and delegates requests to the service layer.

Service Layer

Contains the business logic of the application and coordinates operations between controllers and repositories.

Repository Layer

Handles database operations using Spring Data JPA.

Entity Layer

Contains JPA entities that represent the database tables.

DTO Layer

Defines the data used for API requests and responses instead of directly exposing entity objects.

Mapper Layer

Handles conversion between DTOs and entities.

Security Layer

Handles authentication and JWT-based security.

Exception Layer

Provides centralized exception handling using @RestControllerAdvice.

🔄 Request Flow

A typical request flows through the application as follows:

Client
  |
  v
Controller
  |
  v
Validation
  |
  v
Service
  |
  v
Mapper
  |
  v
Repository
  |
  v
Database
  |
  v
Entity
  |
  v
Mapper
  |
  v
Response DTO
  |
  v
Client
Example: Creating a Student
POST /students
      |
      v
StudentController
      |
      v
Validation
      |
      v
StudentService
      |
      v
StudentMapper
      |
      v
StudentRepository
      |
      v
     MySQL
      |
      v
Student Entity
      |
      v
StudentMapper
      |
      v
Student Response DTO
🗄️ Database Design

The application uses MySQL as the relational database.

The project contains the following major entities:

Department
ClassRoom
Student
Teacher
Coordinator
Course
Notice
Note
AssignmentIssued
AssignmentUpload
Marks

Each entity represents a specific business responsibility and is connected with other entities through appropriate relationships.

ER Diagram

Add your ER diagram image here if you have one.

![ER Diagram](path-to-your-er-diagram.png)
🔗 Entity Relationships

The major relationships in the system are:

Department
   |
   +---- ClassRoom
   |
   +---- Teacher
   |
   +---- Coordinator
   |
   +---- Course


ClassRoom
   |
   +---- Student
   |
   +---- Notice
   |
   +---- Note
   |
   +---- AssignmentIssued
   |
   +---- Course


Teacher
   |
   +---- Course
   |
   +---- AssignmentIssued


Coordinator
   |
   +---- ClassRoom


Student
   |
   +---- AssignmentUpload
   |
   +---- Marks


AssignmentIssued
   |
   +---- AssignmentUpload


Course
   |
   +---- Marks
Relationship Types
Relationship	Type
Department → ClassRoom	One-to-Many
Department → Teacher	One-to-Many
Department → Coordinator	One-to-Many
Department → Course	One-to-Many
ClassRoom → Student	One-to-Many
ClassRoom → Notice	One-to-Many
ClassRoom → Note	One-to-Many
ClassRoom → AssignmentIssued	One-to-Many
ClassRoom → Course	One-to-Many
Teacher → Course	One-to-Many
Teacher → AssignmentIssued	One-to-Many
Coordinator → ClassRoom	One-to-Many
Student → AssignmentUpload	One-to-Many
Student → Marks	One-to-Many
AssignmentIssued → AssignmentUpload	One-to-Many
Course → Marks	One-to-Many

The relationships were designed according to the requirements and assumptions of this project.

🔐 Authentication & JWT

The application uses Spring Security and JWT for stateless authentication.

Authentication Flow
                  Login Request
                       |
                       v
                 AuthController
                       |
                       v
              AuthenticationManager
                       |
                       v
              UserDetailsService
                       |
                       v
              Password Verification
                       |
                       v
                  JWT Generation
                       |
                       v
                     Client
                       |
                       v
        Authorization: Bearer <token>
                       |
                       v
            JwtAuthenticationFilter
                       |
                       v
                JWT Validation
                       |
                       v
               SecurityContext
                       |
                       v
                Protected API
Security Components
Spring Security
JWT
UserDetailsService
Custom UserDetails
AuthenticationManager
OncePerRequestFilter
SecurityContext
BCrypt Password Encoder
Stateless Session Management
Authentication Entry Point

Passwords are not stored as plain text. They are hashed using BCrypt before being stored in the database.

📦 DTO Pattern

The project uses Data Transfer Objects (DTOs) instead of directly exposing JPA entities through APIs.

Why DTOs are used
Prevent direct exposure of entity structures
Avoid exposing unnecessary or sensitive fields
Control request and response data
Keep API contracts separate from database entities
Improve maintainability
DTO Flow
Client
   |
   v
Request DTO
   |
   v
Mapper
   |
   v
Entity
   |
   v
Database

For responses:

Database
   |
   v
Entity
   |
   v
Mapper
   |
   v
Response DTO
   |
   v
Client
🔄 Mapper Pattern

Mapper classes are used to convert between DTOs and entities.

DTO  ---> Entity

Entity ---> DTO

The mapper pattern helps to:

Centralize conversion logic
Avoid duplicate conversion code
Improve code reusability
Keep controllers and services cleaner
Maintain separation between API and persistence models
🧩 Validation

The application uses Bean Validation to validate incoming request data.

Validation prevents invalid data from reaching the business logic.

Examples of validation annotations include:

@NotNull
@NotBlank

Validation helps maintain data integrity and ensures that API requests contain valid data.

⚠️ Global Exception Handling

The project uses centralized exception handling with:

@RestControllerAdvice

Instead of handling every exception separately inside each controller, common exceptions are handled centrally.

Benefits
Consistent error responses
Cleaner controllers
Better maintainability
Centralized error-handling logic

Examples include:

Resource not found
Validation errors
Constraint violations
Authentication-related exceptions
📄 Pagination

Pagination is implemented for APIs that return collections of records.

Instead of fetching all records at once, the application retrieves data in smaller pages.

Example:

GET /students?page=0&size=10

Pagination uses Spring Data JPA's:

Pageable
Page<T>
Benefits
Reduces unnecessary database data retrieval
Reduces memory usage
Improves response time
Makes APIs more scalable
Prevents large responses
📝 Logging

The application uses logging instead of System.out.println() for important application events.

Logging is used to:

Track important operations
Debug application issues
Understand request processing
Monitor important service operations

The project uses SLF4J/Logback for logging.

Example:

log.info("Fetching assignments for classroomId={} and teacherId={}",
         classroomId, teacherId);
📚 API Documentation

The project uses Swagger/OpenAPI for API documentation.

Swagger provides an interactive interface where APIs can be:

Viewed
Tested
Explored
Used to understand request and response structures

The Swagger UI can be accessed after starting the application through the configured Swagger endpoint.

🔌 API Endpoints

The application contains APIs for multiple modules.

Authentication
POST /auth/login
Students
POST   /students
GET    /students
GET    /students/{id}
PUT    /students/{id}
DELETE /students/{id}
Teachers
POST   /teachers
GET    /teachers
GET    /teachers/{id}
PUT    /teachers/{id}
DELETE /teachers/{id}
Departments
POST   /departments
GET    /departments
GET    /departments/{id}
PUT    /departments/{id}
DELETE /departments/{id}
Classrooms
POST   /classrooms
GET    /classrooms
GET    /classrooms/{id}
PUT    /classrooms/{id}
DELETE /classrooms/{id}
Courses
POST   /courses
GET    /courses
GET    /courses/{id}
PUT    /courses/{id}
DELETE /courses/{id}
Assignments
POST   /assignments
GET    /assignments
GET    /assignments/{id}
PUT    /assignments/{id}
DELETE /assignments/{id}
Assignment Submissions
POST /assignment-uploads
GET  /assignment-uploads
Marks
POST /marks
GET  /marks
GET  /marks/{id}
PUT  /marks/{id}

The endpoint list above is a high-level representation. Refer to Swagger/OpenAPI documentation for the complete API specification.

📁 Project Structure

The project follows a layered package structure:

src
└── main
    └── java
        └── ...
            ├── controller
            ├── service
            ├── repository
            ├── entity
            ├── dto
            ├── mapper
            ├── exception
            └── security
                ├── config
                ├── filter
                ├── jwt
                ├── service
                ├── userdetails
                ├── dto
                └── entrypoint
⚙️ How to Run the Project
1. Clone the Repository
git clone YOUR_GITHUB_REPOSITORY_URL
2. Open the Project

Open the project in your preferred IDE:

IntelliJ IDEA
Eclipse
Spring Tool Suite
3. Configure MySQL

Create a MySQL database.

Example:

CREATE DATABASE student_management;
4. Configure Application Properties

Update the database configuration in:

src/main/resources/application.properties

Example:

spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

Configure the JWT secret through a secure configuration or environment variable.

Do not commit real database passwords or JWT secrets to GitHub.

5. Build the Project
mvn clean install
6. Run the Application
mvn spring-boot:run

Or run the main Spring Boot application class from your IDE.

🧪 Example API
Create Student
POST /students
Content-Type: application/json
Request
{
  "name": "Rahul Sharma",
  "email": "rahul@example.com"
}
Response
{
  "id": 1,
  "name": "Rahul Sharma",
  "email": "rahul@example.com"
}

The exact request and response fields depend on the DTO implementation in the project.

📌 Design Decisions
Layered Architecture

Used to separate responsibilities between controllers, services, repositories, and supporting components.

DTOs

Used to prevent direct exposure of entity structures and control API data.

Mapper Classes

Used to centralize DTO/entity conversion logic and avoid duplicate conversion code.

LAZY Fetching

Used for appropriate relationships to avoid loading associated data unnecessarily when it is not required.

Pagination

Used to avoid fetching large datasets in a single request.

JWT

Used to implement stateless authentication.

BCrypt

Used to securely hash passwords before storing them in the database.

Global Exception Handling

Used to provide consistent error responses throughout the application.

MySQL

Used as a relational database because the application contains structured data and relationships between entities.

🎯 Learning Outcomes

Through this project, I gained practical experience with:

Java backend development
Spring Boot
REST API development
Layered architecture
Spring Data JPA
Hibernate
MySQL database design
Entity relationships
DTO pattern
Mapper pattern
Bean Validation
Global exception handling
JWT authentication
Spring Security
Password hashing with BCrypt
Pagination
Logging
Swagger/OpenAPI
Maven
Git
GitHub

The project helped me understand how different backend components work together to build a maintainable REST API.

🔮 Future Improvements

The project can be extended in the future with features such as:

Role-Based Access Control (RBAC)
Refresh Token mechanism
Advanced filtering and searching
Email notifications
Docker containerization
Cloud deployment
Additional security enhancements
Automated testing

RBAC and other advanced features can be implemented in future versions/projects.

👨‍💻 Author
Mohit Singh Makwana
Java Backend Developer | Spring Boot Developer

Technologies
Java
Spring Boot
Spring Data JPA
Hibernate
MySQL
Spring Security
JWT
REST APIs
Git
GitHub

Project
If you find this project useful or interesting, feel free to explore the source code and API documentation.
