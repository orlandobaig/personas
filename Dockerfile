# ------------ STAGE 1: build the application ------------
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copy project files and give permission to run Maven wrapper
COPY . .
RUN chmod +x mvnw

# Build the Spring Boot app, skipping tests
RUN ./mvnw clean package -DskipTests

# ------------ STAGE 2: run the application ------------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
