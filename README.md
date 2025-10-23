# 📚 BookAI — Intelligent Book Recommendation System

**BookAI** is a microservice-based system that provides intelligent, AI-powered book recommendations.  
Built with **Spring Boot**, **Eureka**, and **Docker**, it combines modular microservices for scalability and flexibility.

---

## 🏗️ Architecture

The project follows a **microservices architecture**:

```
BookAI/
├── user-service/           # Handles user data and preferences
├── book-service/           # Manages book catalog
├── ai-service/             # AI engine for personalized recommendations
├── eureka-server/          # Service discovery (Eureka)
├── docker-compose.yml      # Orchestrates all containers
└── pom.xml                 # Root Maven configuration
```

---

## 🚀 Features

- 👤 **User Management** – Create and manage users, preferences, and book lists  
- 📘 **Book Catalog** – CRUD operations for books  
- 🤖 **AI Recommendations** – Personalized book suggestions based on user behavior  
- 🔍 **Service Discovery** – Eureka for automatic registration and service lookup  
- 🐳 **Docker Support** – Run all microservices in containers effortlessly  

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-------------|
| Language | Java 17 |
| Framework | Spring Boot |
| Database | PostgreSQL |
| Service Discovery | Eureka |
| Containerization | Docker, Docker Compose |
| Build Tool | Maven |
| AI Integration | Ollama / Local LLM |

---

## 📋 Prerequisites

Make sure you have:

- ☕ **Java 17+**
- 🐋 **Docker & Docker Compose**
- 🧰 **Maven 3.6+**

---

## 🏃‍♂️ Quick Start

### 🐳 Using Docker (Recommended)

```bash
# Clone the repository
git clone https://github.com/qsheker/BookAI.git
cd BookAI

# Start all services
docker-compose up -d
```

### ⚙️ Manual Setup

Run each service manually in separate terminals:

```bash
# Start Eureka Server first
cd eureka-server
mvn spring-boot:run

# Start other services
cd ../user
mvn spring-boot:run

cd ../book
mvn spring-boot:run

cd ../ai-service
mvn spring-boot:run
```

---

## 🌐 Services & Ports

| Service | Port | Description |
|----------|------|-------------|
| Eureka Server | **8761** | Service discovery dashboard |
| User Service | **8081** | Manages users |
| Book Service | **8082** | Manages books |
| AI Service | **8083** | Provides book recommendations |

---



## 📚 API Endpoints

### 🧍 User API
<img width="1531" height="642" alt="user-api-assets" src="https://github.com/user-attachments/assets/25d598cd-0b24-4358-b55c-ce3c2a58908d" />

### 📖 Book API

<img width="1591" height="420" alt="book-api-assets" src="https://github.com/user-attachments/assets/5503875c-c5c3-4d44-b9f6-7dc339702d1c" />


### 🧠 AI API

<img width="1541" height="259" alt="ai-service-assets" src="https://github.com/user-attachments/assets/2c5698d1-6f37-429a-8559-72526b41e8ca" />

### ⚙️ Eureka server
<img width="1900" height="905" alt="image" src="https://github.com/user-attachments/assets/66c930d1-f41f-41fb-a977-3f52d2adcb76" />

---

## 🗄️ Database Schema

### `users` Table
| Column | Type | Description |
|--------|------|-------------|
| id | BIGSERIAL | Primary key |
| username | VARCHAR | User’s name |
| email | VARCHAR | Email |
| created_at | TIMESTAMP | Creation time |
| updated_at | TIMESTAMP | Updated time |

### `books` Table
| Column | Type | Description |
|--------|------|-------------|
| id | BIGSERIAL | Primary key |
| title | VARCHAR | Book title |
| author | VARCHAR | Book author |
| price | DECIMAL | Book price |
| description | TEXT | Book description |
| created_at | TIMESTAMP | Creation time |
| updated_at | TIMESTAMP | Updated time |

### `user_books` Table
| Column | Type | Description |
|--------|------|-------------|
| id | BIGSERIAL | Primary key |
| user_id | BIGINT | Linked user |
| book_id | BIGINT | Linked book |

---

## 🤖 Recommendation System

The AI Service uses **Ollama** (or any LLM) to generate book suggestions based on:
- User’s read books & genres
- Similar users’ reading patterns
- Book descriptions & popularity

Example output format:
```json
[
  {
    "id": 10,
    "title": "The Silmarillion",
    "author": "J.R.R. Tolkien",
    "price": 14.99,
    "description": "A mythic history of Middle-earth and the First Age."
  }
]
```

---

## 🐛 Troubleshooting

### Common Issues
- **Eureka not showing services:** make sure all services have correct `eureka.client.service-url.defaultZone`
- **Database errors:** verify PostgreSQL is running and credentials are correct

### Logs
```bash
docker-compose logs -f
```

---

## 📝 Environment Variables

Create a `.env` file with the following content:

```
# Postgres
POSTGRES_USER=username
POSTGRES_PASSWORD=password
POSTGRES_DB=postgres
POSTGRES_PORT=5432

# PgAdmin
PGADMIN_DEFAULT_EMAIL= example@example.com
PGADMIN_DEFAULT_PASSWORD= password

# User Service
USER_SERVICE_PORT=port

# Spring
SPRING_DATASOURCE_URL=jdbc:postgresql://${POSTGRES_HOST}:${POSTGRES_PORT}/${POSTGRES_DB}
SPRING_DATASOURCE_USERNAME=${POSTGRES_USER}
SPRING_DATASOURCE_PASSWORD=${POSTGRES_PASSWORD}
SERVER_PORT=${USER_SERVICE_PORT}
```

---

## 🤝 Contributing

1. Fork the project  
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)  
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)  
4. Push to the branch (`git push origin feature/AmazingFeature`)  
5. Open a Pull Request  

---

## 📄 License

This project is licensed under the **MIT License**.

---

## 👨‍💻 Author

**aldik** — Java Backend Developer 🚀  
GitHub:  [@qsheker](https://github.com/qsheker)
