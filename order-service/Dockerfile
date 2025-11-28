# order-service/Dockerfile
FROM gradle:9.2.1-jdk25 AS build
WORKDIR /app

# Copy wrapper + build files first to leverage Docker cache
COPY gradle gradle
COPY gradlew .
COPY build.gradle ./
RUN chmod +x ./gradlew

# Copy source and build (skip tests for faster image builds)
COPY src ./src
RUN ./gradlew --no-daemon -x test bootJar

FROM eclipse-temurin:25-jre-jammy
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/app.jar","--spring.profiles.active=docker"]
