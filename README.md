# 🧩 TestProj — Spring Boot CRUD + Thymeleaf + Docker + CI/CD

Учебный pet-проект, демонстрирующий базовый **Full-Stack backend с UI**, реализованный на **Spring Boot 3**, с интеграцией **Thymeleaf**, **H2 DB**, **Docker**, и **GitHub Actions** для CI/CD.

---

## 🚀 Функционал

Приложение управляет карточками (Card) со следующими полями:
- `id` — идентификатор;
- `title` — заголовок;
- `description` — описание;
- `status` — статус (например, OPEN, DONE и т.д.).

Доступны два интерфейса:
- **REST API** — `http://localhost:8080/cards`  
  → поддерживает CRUD-операции (GET, POST, PUT, DELETE);
- **Web UI (Thymeleaf)** — `http://localhost:8080/ui/cards`  
  → страница с таблицей карточек, кнопкой `Add` и просмотром `View`.

---

## 🧱 Технологии

| Категория | Использовано |
|------------|---------------|
| Backend | Spring Boot 3 (Web, Data JPA, Actuator) |
| Database | H2 (in-memory) |
| View | Thymeleaf + Bootstrap 5 |
| Build | Maven |
| Runtime | Java 21 |
| Containerization | Docker (multi-stage build) |
| CI/CD | GitHub Actions (build, docker, smoke-tests) |

---

## ⚙️ Запуск локально

```bash
# 1. Собрать проект
mvn clean package

# 2. Запустить приложение
java -jar target/testProj-0.0.1-SNAPSHOT.jar
