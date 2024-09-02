FROM gradle:8-jdk17 AS builder

WORKDIR /app

COPY . .

RUN gradle build --no-daemon

FROM eclipse-temurin:17-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
