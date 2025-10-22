# 🧩 TestProj — Spring Boot CRUD + API автотесты + Docker + CI/CD

👩‍💻 **Автор:** Анна Ляшутина — QA Automation Engineer

Привет! Я Анна, инженер по автоматизации тестирования, увлечённая созданием надёжных и прозрачных QA-решений.  
Этот проект — мой **pet-проект**, демонстрирующий полный цикл тестирования серверного приложения:  
от **разработки backend-сервиса на Spring Boot** до **автоматизации API-тестов и CI/CD-пайплайна** на GitHub Actions.

---

## 🚀 О проекте

**TestProj** — это учебный проект, который объединяет:
- **Бэкенд (Spring Boot)** с REST API и минимальным UI на Thymeleaf;
- **API автотесты** на RestAssured;
- **Docker-окружение** для изолированного запуска;
- **CI/CD** на GitHub Actions, где при каждом деплое автоматически собирается образ, поднимается контейнер и выполняются smoke-тесты.

---

## 🧱 Технологический стек

| Категория | Используемые технологии |
|------------|--------------------------|
| Язык | Java 21 |
| Framework | Spring Boot 3 (Web, Data JPA, Actuator) |
| База данных | H2 (in-memory) |
| UI | Thymeleaf + Bootstrap 5 |
| Автотесты | RestAssured + JUnit 5 |
| Сборка | Maven |
| Контейнеризация | Docker (multi-stage build) |
| CI/CD | GitHub Actions |

---

## 📊 Функциональность приложения

Приложение управляет карточками (**Card**) со следующими полями:
- `id` — уникальный идентификатор
- `title` — заголовок
- `description` — описание
- `status` — статус (`OPEN`, `IN_PROGRESS`, `DONE`)

### Интерфейсы:
- **REST API:** [`http://localhost:8080/cards`](http://localhost:8080/cards) — CRUD-операции (GET, POST, PUT, DELETE)
- **Web UI:** [`http://localhost:8080/ui/cards`](http://localhost:8080/ui/cards) — таблица карточек с кнопками `Add` и `View`

---

## ⚙️ Как запустить локально

### 🧱 Вариант 1 — без Docker

```bash
# Сборка проекта
mvn clean package

# Запуск приложения
java -jar backend/target/backend-0.0.1-SNAPSHOT.jar
```

### 🐳 Вариант 2 — через Docker

```bash
# Сборка образа
docker build -t cards-backend:local ./backend

# Запуск контейнера
docker run -p 8080:8080 cards-backend:local
```
После запуска приложение будет доступно по адресу:  
👉 [http://localhost:8080/ui/cards](http://localhost:8080/ui/cards)

---

## 🧪 API автотесты (RestAssured)

Отдельный модуль **`api-autotests`** содержит набор тестов для проверки корректности REST API.

### Проверяются сценарии

| Тип запроса | Проверка |
|--------------|-----------|
| **GET /cards** | Получение списка карточек |
| **POST /cards** | Создание новой карточки |
| **PUT /cards/{id}** | Обновление карточки |
| **DELETE /cards/{id}** | Удаление карточки |
| **/actuator/health** | Проверка доступности сервиса |

---

### 🔧 Запуск тестов

```bash
cd api-autotests
mvn test -DbaseUrl=http://localhost:8080
```
💡 В CI URL передаётся через переменную окружения `BASE_URL`.

---

## 🔄 CI/CD процесс

GitHub Actions автоматически выполняет пайплайн при каждом пуше в `main`:

1. 🧱 **Сборка Docker-образа backend**
2. 🚀 **Запуск контейнера с backend**
3. 🧪 **Запуск API-тестов (RestAssured)**
4. 📊 **Сохранение отчётов Surefire / Allure**
5. 🧹 **Остановка контейнеров и очистка**

---

## 🌟 Почему этот проект важен

✅ Показывает полный цикл QA-работы — от написания backend-логики до автотестов и CI/CD  
✅ Использует реальные DevOps-подходы (Docker + GitHub Actions)  
✅ Демонстрирует умение интегрировать автотесты в пайплайн разработки  
✅ Минималистичный, но расширяемый каркас — можно адаптировать под любой pet-проект или демо для интервью

---

## 📈 План развития проекта

| Этап | Статус |
|------|--------|
| Базовый CRUD-backend на Spring Boot | ✅ Завершено |
| REST API интеграция | ✅ Завершено |
| Dockerfile + локальный запуск | ✅ Завершено |
| RestAssured автотесты | 🚧 В процессе |
| CI/CD пайплайн на GitHub Actions | 🚧 В процессе |
| Отчёты Allure | 🕗 В планах |
| UI-тесты с Playwright / Selenium | 🕗 В планах |

---

## 💬 Контакты

📩 **Email:** [annlyashutina@gmail.com](mailto:annlyashutina@gmail.com)  
🔗 **LinkedIn:** [linkedin.com/in/anna-lyashutina](https://www.linkedin.com/in/anna-lyashutina)  
📁 **Bitbucket / GitHub:** [bitbucket.org/annlyashutina](https://bitbucket.org/annlyashutina)

---

✨ *Этот README будет обновляться по мере развития проекта. Следите за обновлениями!*
