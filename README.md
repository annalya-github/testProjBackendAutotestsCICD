# 🧩 TestProj — Spring Boot CRUD + API Test Automation + Docker + CI/CD

👩‍💻 Author: Anna Lyashutina — QA Automation Engineer

Hi! I'm Anna, a QA Automation Engineer passionate about building reliable and transparent QA solutions.
This personal project demonstrates the complete testing lifecycle of a backend application: from developing a Spring Boot service to automating API tests and setting up a CI/CD pipeline with GitHub Actions.

---

## 🚀 About the Project

TestProj is a learning project that brings together:

- A Spring Boot backend with a REST API and a minimal Thymeleaf UI;
- Automated API tests using RestAssured;
- A Docker environment for isolated execution;
- CI/CD with GitHub Actions, where each deployment automatically builds an image, starts a container, and runs smoke tests.

---

## 🧱 Technology Stack

| Category | Technologies |
| --- | --- |
| Language | Java 21 |
| Framework | Spring Boot 3 (Web, Data JPA, Actuator) |
| Database | H2 (in-memory) |
| UI | Thymeleaf + Bootstrap 5 |
| Test automation | RestAssured + JUnit 5 |
| Build tool | Maven |
| Containerization | Docker (multi-stage build) |
| CI/CD | GitHub Actions |

---

## 📊 Application Features

The application manages cards (`Card`) with the following fields:

- `id` — unique identifier
- `title` — title
- `description` — description
- `status` — status (`OPEN`, `IN_PROGRESS`, `DONE`)

### Interfaces

- REST API: `http://localhost:8080/cards` — CRUD operations (GET, POST, PUT, DELETE)
- Web UI: `http://localhost:8080/ui/cards` — a card table with `Add` and `View` buttons

---

## ⚙️ Running Locally

### 🧱 Option 1 — Without Docker

```bash
# Build the project
mvn clean package

# Start the application
java -jar backend/target/backend-0.0.1-SNAPSHOT.jar
```

### 🐳 Option 2 — With Docker

```bash
# Build the image
docker build -t cards-backend:local ./backend

# Start the container
docker run -p 8080:8080 cards-backend:local
```

Once started, the application is available at:
👉 http://localhost:8080/ui/cards

---

## 🧪 Automated API Tests (RestAssured)

The separate `api-autotests` module contains tests that verify REST API behavior.

### Test Scenarios

| Request | Verification |
| --- | --- |
| GET /cards | Retrieve the list of cards |
| POST /cards | Create a new card |
| PUT /cards/{id} | Update a card |
| DELETE /cards/{id} | Delete a card |
| /actuator/health | Check service availability |

### 🔧 Running the Tests

```bash
cd api-autotests
mvn test -DbaseUrl=http://localhost:8080
```

---

## 🔄 CI/CD Workflow

GitHub Actions automatically runs the pipeline on every push to `main`:

1. 🧱 Build the backend Docker image
2. 🚀 Start the backend container
3. 🧪 Run API tests (RestAssured)
4. 📊 Save Surefire / Allure reports
5. 🧹 Stop containers and clean up

---

## 🌟 Why This Project Matters

- ✅ Demonstrates the complete QA workflow — from backend development to automated tests and CI/CD
- ✅ Applies practical DevOps techniques (Docker + GitHub Actions)
- ✅ Shows how to integrate automated tests into a development pipeline
- ✅ Provides a minimal, extensible foundation that can be adapted for other personal projects or demos

---

## 📈 Project Roadmap

| Milestone | Status |
| --- | --- |
| Basic Spring Boot CRUD backend | ✅ Completed |
| REST API integration | ✅ Completed |
| Dockerfile and local execution | ✅ Completed |
| Automated tests with RestAssured | 🚧 In progress |
| GitHub Actions CI/CD pipeline | ✅ Completed |
| Allure reports | 🕗 Planned |
| UI tests with Playwright / Selenium | 🕗 Planned |

---

## 💬 Contact

📩 Email: annlyashutina@gmail.com  
🔗 LinkedIn: [linkedin.com/in/anna-lyashutina](https://www.linkedin.com/in/anna-lyashutina)

---

✨ This README will be updated as the project evolves. Stay tuned!
