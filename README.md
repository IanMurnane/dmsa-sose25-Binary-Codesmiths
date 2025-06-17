# ComplexSoftwareArchitectures

### Running the Application

To run the project locally:

1. Start the **Eureka Server** (`localhost:8761`).
    - mvn clean install
    - mvn spring-boot:run
2. Start each microservice
   - `Config`, `Booking`, `Payment`, `Rating`, `User`, `Vehicle`
3. Ensure each service registers with Eureka (visible in Eureka dashboard- http://localhost:8761/) .
4. Start React frontend (`npm install` & `npm start`)
5. Access app at http://localhost:3000, backend runs at http://localhost:8080, 8082, 8083 etc.
6. Use Postman to test the REST APIs (see endpoints in the wiki).
