# Rating Microservice

This microservice handles all operations related to ratings in the mobility platform.

## Features

- Create ratings for vehicles and providers
- Get ratings by ID, user ID, vehicle ID, booking ID, or provider ID
- Update existing ratings
- Delete ratings
- Calculate average ratings for vehicles and providers

## API Endpoints

### Create a new rating
- **POST** `/api/ratings`

### Get all ratings
- **GET** `/api/ratings`

### Get rating by ID
- **GET** `/api/ratings/{id}`

### Update a rating
- **PUT** `/api/ratings/{id}`

### Delete a rating
- **DELETE** `/api/ratings/{id}`

### Get ratings by user ID
- **GET** `/api/ratings/user/{userId}`

### Get ratings by vehicle ID
- **GET** `/api/ratings/vehicle/{vehicleId}`

### Get ratings by booking ID
- **GET** `/api/ratings/booking/{bookingId}`

### Get ratings by provider ID
- **GET** `/api/ratings/provider/{providerId}`

### Get average rating by vehicle ID
- **GET** `/api/ratings/vehicle/{vehicleId}/average`

### Get average rating by provider ID
- **GET** `/api/ratings/provider/{providerId}/average`

## Setup

1. Create a PostgreSQL database named `mobility_ratingdb`
2. Configure the database connection in `application.properties`
3. Run the application: `mvn spring-boot:run`

## Technologies

- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Lombok 