<div align="center">

# 🚀 Task Management System

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java%2021-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Railway](https://img.shields.io/badge/Deployed%20on-Railway-0B0D0E?style=for-the-badge&logo=railway&logoColor=white)](https://railway.app/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)

A **scalable, role-based Task Management System** backend built with **Spring Boot**, **Spring Security**, and **MySQL** — designed to manage projects, assign tasks, and track progress efficiently.

This project demonstrates real-world backend engineering concepts including **authentication**, **authorization**, **REST APIs**, **database design**, and **secure cloud deployment**.

[🌐 Live Demo](https://taskmanagement-production-d515.up.railway.app/login) · [🐛 Report Bug](https://github.com/vijay78274/Task_Management/issues) · [💡 Request Feature](https://github.com/vijay78274/Task_Management/issues)

</div>

---

## 📋 Table of Contents

- [Live Demo](#-live-demo)
- [Key Features](#-key-features)
- [Tech Stack](#️-tech-stack)
- [Architecture](#️-architecture)
- [Database Schema](#️-database-schema)
- [API Endpoints](#-api-endpoints)
- [Getting Started](#-getting-started)
- [Environment Variables](#-environment-variables)
- [Admin Bootstrap Strategy](#-admin-bootstrap-strategy)
- [Roadmap](#-roadmap)
- [Contributing](#-contributing)
- [Author](#-author)

---

## 🌐 Live Demo

> The backend is live and deployed on **Railway**:

**🔗 [https://taskmanagement-production-d515.up.railway.app/login](https://taskmanagement-production-d515.up.railway.app/login)**

You can test all API endpoints using **Postman** or any REST client against this base URL.

---

## 📌 Key Features

### 🔐 Authentication & Security
- User registration and login system
- **BCrypt** password encryption
- **Spring Security** integration
- Role-based access control — `ADMIN` / `MEMBER`

### 👥 User Management *(Admin Only)*
- Default `MEMBER` role assigned on signup
- Admin user bootstrapped via environment variables at startup
- Secure login with session-based authentication
- **Admin can view all registered users**
- **Admin can update user roles and manage accounts**

### 📁 Project Management
- Create and manage multiple projects
- Assign users to projects
- Track project-level progress

### ✅ Task Management
- Create tasks under specific projects
- Assign tasks to users
- Update task status:
  - `PENDING`
  - `COMPLETED`
  - `APPROVED`
- **Filter tasks by status, assignee, or project** *(Admin)*

### 📊 Dashboard APIs
- Fetch user-specific tasks
- Track overdue and completed tasks
- Role-based data access — admins see all, members see their own

### 🧠 System Design Highlights
- Layered architecture: `Controller → Service → Repository`
- DTO-based request/response handling
- Centralized Spring Security configuration
- Database normalization via **JPA / Hibernate**
- Environment-based configuration (Railway-ready)

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java 21 |
| Framework | Spring Boot |
| Security | Spring Security |
| ORM | Spring Data JPA / Hibernate |
| Database | MySQL |
| Build Tool | Maven |
| Deployment | Railway |

---

## ⚙️ Architecture

```
Client Request
      │
      ▼
┌─────────────────────┐
│   Spring Security   │  ← Authentication & Role-based Authorization
└─────────┬───────────┘
          │
          ▼
┌─────────────────────┐
│  Controller Layer   │  ← Handles HTTP requests, maps routes
└─────────┬───────────┘
          │
          ▼
┌─────────────────────┐
│    Service Layer    │  ← Business logic, DTO transformation
└─────────┬───────────┘
          │
          ▼
┌─────────────────────┐
│  Repository Layer   │  ← JPA interfaces for DB access
└─────────┬───────────┘
          │
          ▼
┌─────────────────────┐
│   MySQL Database    │  ← Hosted on Railway
└─────────────────────┘
```

---

## 🗄️ Database Schema

```
┌──────────────┐       ┌──────────────┐       ┌──────────────┐
│    users     │       │   projects   │       │    tasks     │
├──────────────┤       ├──────────────┤       ├──────────────┤
│ id (PK)      │──┐    │ id (PK)      │──┐    │ id (PK)      │
│ username     │  │    │ name         │  │    │ title        │
│ email        │  │    │ description  │  │    │ description  │
│ password     │  └───▶│ created_by   │  └───▶│ project_id   │
│ role         │       └──────────────┘       │ assigned_to  │
└──────────────┘                              │ status       │
                                              │ due_date     │
                                              └──────────────┘

Roles: ADMIN | MEMBER
Task Status: PENDING | COMPLETED | APPROVED
```

---

## 📡 API Endpoints

### 🔓 Auth Endpoints (Public)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/auth/register` | Register a new user (defaults to `MEMBER`) |
| `POST` | `/api/auth/login` | Login and start session |

### 👥 User Endpoints *(ADMIN only)*

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/users` | Get all registered users |
| `GET` | `/api/users/{id}` | Get a specific user by ID |
| `PUT` | `/api/users/{id}/role` | Update a user's role |
| `DELETE` | `/api/users/{id}` | Delete a user account |

### 📁 Project Endpoints *(Authenticated)*

| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| `GET` | `/api/projects` | ALL | Get all accessible projects |
| `POST` | `/api/projects` | ADMIN | Create a new project |
| `PUT` | `/api/projects/{id}` | ADMIN | Update a project |
| `DELETE` | `/api/projects/{id}` | ADMIN | Delete a project |

### ✅ Task Endpoints *(Authenticated)*

| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| `GET` | `/api/tasks` | ALL | Get tasks for the logged-in user |
| `GET` | `/api/tasks?status={status}` | ADMIN | Filter tasks by status (`PENDING` / `COMPLETED` / `APPROVED`) |
| `GET` | `/api/tasks?assignee={userId}` | ADMIN | Filter tasks by assigned user |
| `GET` | `/api/tasks?project={projectId}` | ADMIN | Filter tasks by project |
| `POST` | `/api/tasks` | ADMIN | Create a task under a project |
| `PUT` | `/api/tasks/{id}` | ALL | Update task status |
| `DELETE` | `/api/tasks/{id}` | ADMIN | Delete a task |

### 📊 Dashboard Endpoints *(Authenticated)*

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/dashboard/my-tasks` | Get tasks assigned to current user |
| `GET` | `/api/dashboard/overdue` | Fetch all overdue tasks |
| `GET` | `/api/dashboard/summary` | Role-based task summary stats |

---

## 🚀 Getting Started

### Prerequisites

Ensure the following are installed on your system:

- [Java 21](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/install.html)
- [MySQL](https://dev.mysql.com/downloads/) (local) or a [Railway](https://railway.app/) MySQL instance
- [Git](https://git-scm.com/)

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/vijay78274/Task_Management.git
cd Task_Management
```

### 2️⃣ Create the Local Database

```sql
CREATE DATABASE task_manager;
```

### 3️⃣ Configure `application.properties`

Update `src/main/resources/application.properties` with your local MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task_manager
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4️⃣ Set Environment Variables

```bash
export ADMIN_USERNAME=admin123
export ADMIN_PASSWORD=your_strong_password
```

### 5️⃣ Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

The server starts at: **`http://localhost:8080`**

---

## 🌱 Environment Variables

| Variable | Description |
|----------|-------------|
| `ADMIN_USERNAME` | Bootstrap admin username |
| `ADMIN_PASSWORD` | Bootstrap admin password |
| `MYSQLHOST` | MySQL host (Railway or local) |
| `MYSQLPORT` | MySQL port (default: `3306`) |
| `MYSQLDATABASE` | Database name (e.g., `railway`) |
| `MYSQLUSER` | MySQL username |
| `MYSQLPASSWORD` | MySQL password |

> ⚠️ **Never hardcode credentials** in source files. Always inject via environment variables in production.

---

## 🔐 Admin Bootstrap Strategy

An admin user is automatically created on application startup using a `CommandLineRunner` bean. This guarantees at least one admin always exists without manual DB seeding.

```java
// Runs once on startup
if (adminUser does not exist) {
    create user with ADMIN role using env vars
}
```

**Default Dev Credentials** *(must be overridden via env vars in production)*

| Field | Value |
|-------|-------|
| Username | `admin123` |
| Password | `your_password` |
| Role | `ADMIN` |

> ⚠️ These are **development-only** defaults. Always use environment variables in production.

---

## 🗺️ Roadmap

- [x] User registration & login
- [x] BCrypt password encryption
- [x] Role-based access control (ADMIN / MEMBER)
- [x] Project CRUD
- [x] Task assignment & status tracking
- [x] Admin — Users section (view, update role, delete)
- [x] Admin — Task filtering (by status, assignee, project)
- [x] Dashboard APIs
- [x] Railway cloud deployment
- [ ] JWT stateless authentication
- [ ] Swagger / OpenAPI documentation
- [ ] Unit & integration tests (JUnit + Mockito)
- [ ] Email notifications for task deadlines
- [ ] Pagination on task/user/project lists
- [ ] Docker support

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

1. Fork the project
2. Create your feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m 'Add: your feature description'`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a Pull Request

---

## 👨‍💻 Author

**Vijay Singh**

[![GitHub](https://img.shields.io/badge/GitHub-vijay78274-181717?style=flat-square&logo=github)](https://github.com/vijay78274)
[![Email](https://img.shields.io/badge/Email-vijaynayal328@gmail.com-D14836?style=flat-square&logo=gmail&logoColor=white)](mailto:vijaynayal328@gmail.com)

---

<div align="center">

⭐ **If you found this project helpful, give it a star on [GitHub](https://github.com/vijay78274/Task_Management)!** ⭐

*Built with ❤️ using Spring Boot*

</div>
