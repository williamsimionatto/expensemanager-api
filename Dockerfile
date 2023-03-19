FROM openjdk:17-jdk-slim-buster AS builder

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]