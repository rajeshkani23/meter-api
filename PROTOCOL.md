# PROTOCOL.md

## 1. Introduction

In this assignment, I had to understand the existing Urja Meter Ops portal and build a Java API to access its data.

I first explored the portal to understand how it works and where the required data comes from. Then I used that information to build APIs using Java and Spring Boot.

My goal was to make the data available through simple API endpoints, so another application can use it without directly accessing the portal.

## 2. How I Explored the Portal

I started by opening the portal and understanding its login process.

After logging in, I checked the available pages and explored how the portal provides transformer and meter information.

I also used the browser's network tab to understand which API requests were made when I opened different pages.

This helped me identify the endpoints needed for my application.

## 3. Data I Found

During my exploration, I found the following information:

### Transformer Information

- The portal provides transformer details.
- The data is available page by page.
- Each record contains details such as transformer code, name, feeder code and capacity.

### Meter Location

- I found an endpoint to get a meter's location.
- It returns latitude and longitude for a given meter ID.

### Meter Energy

- I found an endpoint to get meter energy readings.
- It returns readings with timestamp, kWh, kVAh and voltage information.

## 4. How I Built the Application

I used Java 21 and Spring Boot to build the API.

I divided the application into different layers to keep the code easy to understand and maintain.

- **Controller:** Receives API requests and sends responses.
- **Service:** Handles the main logic and input validation.
- **Client:** Communicates with the existing Urja portal.
- **DTO:** Defines the data returned by my APIs.
- **Exception Handler:** Handles errors and returns a common error response.

I also added login handling in the client so that it can access the portal before requesting its data.

## 5. APIs I Created

I created the following endpoints:

| Method | Endpoint | Purpose |
| GET | `/api/transformers?page=1` | Get transformer details |
| GET | `/api/meters/{meterId}/location` | Get meter location |
| GET | `/api/meters/{meterId}/energy` | Get meter energy readings |

I tested these endpoints using Postman and checked that they returned the expected data.

## 6. Challenges I Faced

One of the main challenges was understanding how the portal handled login and session cookies.

At first, I needed to understand how the login request worked and how the portal allowed access to other endpoints.

I explored the network requests and tested them in Java until I was able to get the required data.

I also faced some issues while handling the portal responses and connecting them to my Spring Boot APIs. I fixed these issues by testing each part separately.

## 7. Assumptions and Limitations

- I treated the existing portal as read-only.
- I used the portal's existing endpoints to get the required information.
- I kept the numeric values in the same string format returned by the portal.
- The application currently supports the three APIs listed above.
- I have not added a separate database to store portal data.

## 8. What I Would Improve

If I had more time, I would like to:

- Add more APIs for other useful information available in the portal.
- Improve session handling and retry logic.
- Add caching to reduce repeated requests to the portal.
- Add more tests for different error scenarios.
- Improve logging so that problems can be identified more easily.

## 9. Final Notes

This assignment helped me understand how to explore an existing application, identify its APIs and build a separate service using Spring Boot.

I also got practical experience with HTTP requests, session cookies, JSON responses, exception handling and testing.

I focused on keeping the application simple, readable and easy to extend.