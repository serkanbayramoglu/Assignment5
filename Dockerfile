# syntax=docker/dockerfile:1

FROM openjdk:8

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

EXPOSE 3000

CMD ["./mvnw", "spring-boot:run"]

