# Meter API

## 1. About the Project

I built this project as part of the Flock Energy assignment.

The main goal is to access transformer and meter information from the existing Urja Meter Ops portal through a separate REST API.

Instead of accessing the portal directly, another application can use the APIs provided by this service.

I used Java 21 and Spring Boot to build the application.

## 2. Features

Currently, the application provides these features:

- Get transformer details with pagination.
- Get the location of a meter using its meter ID.
- Get energy readings for a meter.
- Handle invalid input and errors.
- Connect to the existing portal using its login session.

## 3. Technologies Used

- Java 21
- Spring Boot
- Maven
- Jackson
- JUnit and Mockito
- Postman

## 4. Project Structure

I divided the application into different layers to keep the code organised.

```text
src/main/java/com/flockenergy/meter_api/
│
├── client/
│   └── UrjaPortalClient.java
│
├── controller/
│   ├── MeterController.java
│   └── TransformerController.java
│
├── dto/
│   ├── ApiErrorResponse.java
│   ├── MeterEnergyDto.java
│   ├── MeterEnergyResponse.java
│   ├── MeterLocationDto.java
│   ├── MeterLocationResponse.java
│   ├── TransformerDto.java
│   └── TransformerPageResponse.java
│
├── exception/
│   ├── GlobalExceptionHandler.java
│   └── PortalException.java
│
└── service/
    ├── MeterService.java
    └── TransformerService.java
```

### What each layer does

- **Controller:** Receives requests from users.
- **Service:** Handles validation and application logic.
- **Client:** Communicates with the Urja portal.
- **DTO:** Holds the request and response data.
- **Exception:** Handles errors in a common place.

## 5. How to Run the Application

### Requirements

- Java 21
- Maven
- IntelliJ IDEA or another Java IDE

### Step 1: Configure portal credentials

Set the following environment variables before running the application:

```text
URJA_PORTAL_EMAIL=operator@urja.local
URJA_PORTAL_PASSWORD=urja-ops-2026
```
### Step 2: Build the project

Open a terminal in the project root and run:

```bash
mvn clean install
```

### Step 3: Run the application

Run this command:

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

## 6. Available APIs

| Method | Endpoint | Description |
| GET | `/api/transformers?page=1` | Get transformer details |
| GET | `/api/meters/{meterId}/location` | Get meter location |
| GET | `/api/meters/{meterId}/energy` | Get meter energy readings |

### Example requests

**1. Get transformers**

```http
GET http://localhost:8081/api/transformers?page=1
```

**2. Get meter location**

```http
GET http://localhost:8081/api/meters/J100001/location
```

**3. Get meter energy readings**

```http
GET http://localhost:8081/api/meters/J100001/energy
```

I tested these endpoints using Postman.

## 7. Testing

I added unit tests for the service and controller layers.

I also created an integration test to check communication with the existing portal.

To run the tests:

```bash
mvn test
```

The portal integration test requires the two environment variables mentioned above.

## 8. API Documentation

I have included the OpenAPI specification in the project root.

File: `openapi.json`

It describes the available endpoints, request parameters and response structures.

## 9. Assumptions and Limitations

- I treated the existing portal as read-only.
- I used the portal's existing endpoints to retrieve information.
- I have not added a separate database to store the retrieved data.
- The application currently supports the three endpoints listed above.
- The application depends on the availability of the existing portal.

## 10. Future Improvements

If I get more time, I would like to:

- Add more useful endpoints from the portal.
- Improve session handling.
- Add caching to reduce repeated portal requests.
- Add more test cases for error scenarios.
- Improve logging and monitoring.