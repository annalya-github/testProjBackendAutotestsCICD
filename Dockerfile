# -------- stage 1: build (собираем jar внутри контейнера) --------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
# сначала отдельным слоем копируем pom.xml (для кэша зависимостей)
COPY pom.xml .
RUN mvn -q -B -DskipTests dependency:go-offline
# теперь копируем исходники и собираем
COPY src ./src
RUN mvn -q -B -DskipTests package
# -------- stage 2: runtime (лёгкий образ для запуска) --------
FROM eclipse-temurin:21-jre
WORKDIR /app
# копируем собранный jar из стадии build
COPY --from=build /app/target/*.jar app.jar
# по умолчанию Spring слушает 8080
EXPOSE 8080
# опциональные JVM/SPRING опции можно передавать через ENV/args
ENV JAVA_OPTS=""
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
