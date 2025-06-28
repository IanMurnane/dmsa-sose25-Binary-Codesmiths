FROM eclipse-temurin:21-jre-alpine
#Sets the working directory inside the container
WORKDIR /app    
#Copies pre-built JAR file (from target/) into app.jar  (already ran mvn clean package -DskipTests locally)
COPY target/usermicroservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
