FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY target/backend.jar app.jar
COPY .env .env

EXPOSE 8643

ENTRYPOINT ["java", "-jar", "app.jar"]
