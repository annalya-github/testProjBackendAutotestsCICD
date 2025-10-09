# -------- stage 1: build (собираем jar внутри контейнера) --------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
# кэш зависимостей
COPY pom.xml .
RUN mvn -q -B -DskipTests dependency:go-offline
# сборка проекта
COPY src ./src
RUN mvn -q -B -DskipTests package
# -------- stage 2: runtime (лёгкий образ для запуска) --------
FROM eclipse-temurin:21-jre
WORKDIR /app
# копируем готовый jar
COPY --from=build /app/target/*.jar app.jar
# порт приложения
EXPOSE 8080
# JVM/SPRING опции можно задавать через переменную окружения
ENV JAVA_OPTS=""
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]