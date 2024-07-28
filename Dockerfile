# Use Zulu JDK for the build stage
FROM azul/zulu-openjdk-alpine:21.0.4-jdk as builder
WORKDIR /app

# Copy the Gradle wrapper files and build.gradle to the container
COPY gradlew .
COPY gradlew.bat .
COPY gradle gradle
COPY build.gradle .


# Ensure the gradlew script has execution permissions
RUN chmod +x gradlew

# Copy the source code to the container
COPY src src

# Build the application using Gradle
RUN ./gradlew build

# Use a slim Zulu JRE base image for running the application
FROM azul/zulu-openjdk-alpine:21.0.4-jre

# Set the working directory inside the container
WORKDIR /application

# Copy the built JAR file from the builder stage to the container
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port on which the application will run (adjust if needed)
EXPOSE 8080

# Set the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
