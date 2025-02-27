# MDD System

MDD (Monde de Dév) **is a social network designed specifically for developers, aiming to facilitate connections between professionals in the field and encourage collaboration and knowledge sharing. The primary goal is to help developers find job opportunities and share their expertise through topic-based discussions and technical content.** The project will be built using Spring Boot for the backend and Angular for the frontend, ensuring a modern and scalable web application architecture.

---

## Table of Contents

- [Requirements](#requirements)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)

---

## Requirements

Before running this project, make sure you have the following installed:

- **Java 17** → This project requires Java 17 as the runtime environment. Ensure you have it installed. [Download](https://jdk.java.net/archive/)
- **Maven** → Used for managing project dependencies and building the application. [Installation Guide](https://maven.apache.org/install.html)
- **MySQL** → The backend database for storing application data. [Setup Instructions](https://openclassrooms.com/fr/courses/6971126-implementez-vos-bases-de-donnees-relationnelles-avec-sql/7152681-installez-le-sgbd-mysql)

---

## Getting Started

Follow these steps to set up and run the project.

### **Backend**

### Dependencies

#### Configure Java Environment Variables

#### Windows

1. Open Command Prompt and run:
   ```sh
   echo %JAVA_HOME%
   ```
   If nothing is displayed, proceed with the following steps:
2. Open **System Properties** > **Advanced** > **Environment Variables**
3. Under **System Variables**, click **New** and add:
   - **Variable name**: `JAVA_HOME`
   - **Variable value**: `C:\Program Files\Java\jdk-17`
4. Add `%JAVA_HOME%\bin` to the **Path** variable.

### Installing

#### Initialize the Database

#### 1. Login to MySQL

Open a terminal.

```sh
mysql -u root -p
```

(Enter your MySQL root password when prompted)

#### 2. Run the SQL script

Find the script inside the resources of the project.

```sh
source path/to/create_database.sql;
```
By default, the database is initialized with two themes and one test user.

Test User Credentials:
✉️ Email: test@test.com
🔑 Password: test!1234

### Executing program

#### Compile the project

```sh
mvn clean package
```

#### Run the application

```sh
 mvn spring-boot:run
```

The application will be available at **[http://localhost:8080](http://localhost:8080)**.


---

## API Documentation

The API documentation is available via **Swagger UI** at:

📌 **[http://localhost:8080/api/swagger-ui/index.html](http://localhost:8080/api/swagger-ui/index.html)**

It includes:

- Authentication (`/auth/register`, `/auth/login`)
- Article management (`/articles`)
- Comments system (`/comments`)
- Subscription system (`/subscriptions`)
- Theme system (`/themes`)

---
