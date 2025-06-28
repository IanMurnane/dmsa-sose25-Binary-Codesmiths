# Use a lightweight Java 21 runtime
FROM eclipse-temurin:21-jre-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the built JAR into the image
COPY target/config-server-0.0.1-SNAPSHOT.jar app.jar

# Expose the default port used by the Config Server
EXPOSE 8888

# Run the Config Server
ENTRYPOINT ["java", "-jar", "app.jar"]
