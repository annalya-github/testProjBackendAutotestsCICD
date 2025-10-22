FROM maven:3.9.9-eclipse-temurin-21
WORKDIR /app

# корневой и модульный POM-ы
COPY pom.xml /app/pom.xml
COPY api-autotests/pom.xml /app/api-autotests/pom.xml

# кэш зависимостей для тестового модуля
RUN mvn -q -B -f /app/api-autotests/pom.xml -DskipTests dependency:go-offline

# исходники тестов
COPY api-autotests/src /app/api-autotests/src

# базовый URL берём из переменной окружения
ENV BASE_URL=http://localhost:8080

# запускаем тесты; кладём отчёты в /reports (чтобы потом вытащить из контейнера)
ENTRYPOINT mvn -q -B -f /app/api-autotests/pom.xml -DbaseUrl=${BASE_URL} test && \
           mkdir -p /reports && cp -r /app/api-autotests/target/surefire-reports /reports
