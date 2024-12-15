
# Spring Boot Banking Authentication API

This project provides a robust authentication and authorization system for a banking application, featuring JWT-based authentication, role-based access control (RBAC), and CRUD operations for users, roles, and permissions.

## Features

- **JWT-Based Authentication**: Issue JWT tokens and validate them for secure access via OAuth 2.0, allow to validate and refresh token if need.
- **Role-Based Access Control (RBAC)**: Manage roles and permissions for fine-grained access control using filter chain.
- **CRUD Operations**: Manage users, roles, and permissions.
- **Global Exception Handling**: Handles exceptions like `AccessDeniedException`, `AppException`, `MethodArgumentNotValidException`, and more.
- **Custom JWT Decoder**: A scalable JWT decoder that fits into Spring Security’s filter chain for JWT validation and authentication.

## Setup

### 1. Configure Application Properties

Update the package name since I'm using this for my micro-service

Update the `application.properties` file with your own database configurations and JWT settings:

```properties
spring.application.name=BankingApp
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update

jwt.signerKey=your_secret_key
jwt.valid-duration=300  # 5 minutes
jwt.refreshable-duration=900  # 15 minutes
```

- **`jwt.signerKey`**: Secret key used to sign JWT tokens.
- **`jwt.valid-duration`**: Token validity duration in seconds.
- **`jwt.refreshable-duration`**: Time in seconds for JWT refresh token.

### 2. Database Setup

You need to create your database (e.g., MySQL, PostgreSQL, etc.). This application will automatically generate the necessary tables when it runs.

### 3. Running the Application

After configuring your application, run the Spring Boot application. On startup, an admin user will be automatically created:

- **Username**: `admin`
- **Password**: `admin`

You can use this user to log in and receive a JWT.

## API Usage

### 1. Public Endpoints

These endpoints do not require authentication via POST method:

- `/auth/login` – Login and receive JWT.
- `/auth/introspect` – Validate the JWT.
- `/auth/logout` – Invalidate the current token.
- `/auth/refresh` – Refresh the JWT.

### 2. Authenticating with JWT

To authenticate, login with the admin credentials:

```bash
POST /auth/login
{
    "username": "admin",
    "password": "admin"
}
```

You will receive a JWT token in the response. Use this JWT to authenticate further requests.

### 3. Token Introspection (Validation)

To validate a JWT, send a request to:

```bash
POST /auth/introspect
{
    "token": "your_jwt_token"
}
```

To refresh your JWT when the expired time is going soon, send a request to:

```bash
POST /auth/refresh
{
    "token": "your_jwt_token"
}
```

### 4. Secured Endpoints

Other endpoints require a valid JWT for authentication and authorization:

- **Create Permissions**:

  ```bash
  POST /permissions
  {
      "name": "CREATE_DATA",
      "description": "Permission to create"
  }
  ```

- **Create Roles**:

  ```bash
  POST /roles
  {
      "name" : "ADMIN",
      "description" : "Admin role with full access",
      "permissions" : ["READ_DATA", "UPDATE_DATA", "CREATE_DATA", "DELETE_DATA"]
  }
  ```

- **Create Users**:

  ```bash
  POST /users
  {
      "username" : "tester1005",
      "password" : "123456789",
      "firstName" : "Tester",
      "lastName" : "User",
      "dob" : "1990-09-22",
      "roles" : ["ADMIN"]
  }
  ```

