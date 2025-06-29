# Complex Software Architectures

### Setup the Database

#### Manually

- Type: PostgreSQL
- DB: mobility_userdb
- User: postgres
- Pass: test123

#### OR using Docker

```
docker network create postgres_network
docker run --name my_postgres --network postgres_network -e POSTGRES_USER=postgres  -e POSTGRES_PASSWORD=test123 -e POSTGRES_DB=mobility_userdb -p 5432:5432  -d postgres:latest
docker run --name my_pgadmin  --network postgres_network -e PGADMIN_DEFAULT_EMAIL=admin@admin.com   -e PGADMIN_DEFAULT_PASSWORD=admin  -p 5050:80  -d dpage/pgadmin4
```

### Running the Application

Run the project locally. _**(Requires JDK 21)**_

1. Start the **ConfigServer**       
    - mvn clean install
    - mvn spring-boot:run    

    Note: The configuration files are maintained in a separate Git repository. This repository is directly linked to the Config Server used in this project.
    🔗 Configuration Repository: [instant-mobility-config](https://github.com/mariasaleem11228/instant-mobility-config.git)

2. Start the **Eureka Server**.
    - mvn clean install
    - mvn spring-boot:run
3. Start each microservice
   - `Booking`, `Payment`, `Rating`, `User`, `Vehicle`
4. Ensure each service registers with Eureka (visible in Eureka dashboard- http://localhost:8761/) .
5. Start React frontend (`npm install` & `npm start`)
6. Access app at http://localhost:3000, backend runs at http://localhost:8080, 8081, 8082 etc.
7. Use Postman to test the REST APIs (see endpoints in the wiki).
