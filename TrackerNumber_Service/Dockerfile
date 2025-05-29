# Use the Maven image with OpenJDK 17 as the base
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project file to download dependencies
COPY pom.xml .

# Download project dependencies
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the Spring Boot application, skipping tests
# This will create the JAR file in the 'target' directory
RUN mvn clean package -DskipTests

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# Move the built JAR file from 'target/' to the current working directory ('/app')
# and rename it to 'app.jar' for consistent execution
RUN mv target/*.jar app.jar

# Command to run the Spring Boot application when the container starts
CMD ["java","-jar","app.jar"]
