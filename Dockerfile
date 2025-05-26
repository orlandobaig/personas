# ------------ STAGE 1: build the application ------------
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copy the Maven wrapper and project files
COPY . .

# Give permission to execute the Maven wrapper
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# ------------ STAGE 2: run the application ------------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the built jar from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port Spring Boot uses
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
