# ---------- stage 1: build ----------
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# важный момент: копируем Оба POM-а (корневой + модульный)
COPY pom.xml /app/pom.xml
COPY backend/pom.xml /app/backend/pom.xml

# кэшим зависимости
RUN mvn -q -B -f /app/backend/pom.xml -DskipTests dependency:go-offline

# исходники модуля и сборка
COPY backend/src /app/backend/src
RUN mvn -q -B -f /app/backend/pom.xml -DskipTests package

# ---------- stage 2: runtime ----------
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/backend/target/*.jar /app/app.jar
EXPOSE 8080

# Health-probe
HEALTHCHECK --interval=5s --timeout=3s --start-period=10s --retries=20 \
  CMD curl -fsS http://localhost:8080/actuator/health | grep -q '"status":"UP"' || exit 1

ENTRYPOINT ["java","-jar","/app/app.jar"]
