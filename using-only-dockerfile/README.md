# Using Only Dockerfile

This project demonstrates a **multi-stage Dockerfile** for a Spring Boot application. The Dockerfile:

- Builds the project using Maven.
- Packages the app into a JAR.
- Uses a lightweight JRE for the runtime image.
- Exposes port `8090` (matches `server.port` in `application.properties`).

---

## Dockerfile

```dockerfile
# -------- Stage 1: Build the project --------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build project
RUN mvn clean package -DskipTests

# -------- Stage 2: Run the application --------
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the configured port
EXPOSE 8090

# Start the Spring Boot application
ENTRYPOINT ["java","-jar","app.jar"]
```

## Build and Run the Spring Boot Docker Image

```bash
docker build -t dockerize-spring-boot .

docker run -p 8090:8090 dockerize-spring-boot
```

- `-t dockerize-spring-boot` sets the **name of the Docker image** you are building.
- `.` specifies the **build context**, i.e., the current directory where your `Dockerfile` and project files are located.
