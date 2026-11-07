# Project Notes

## 1. My Understanding of the Assignment

I understood that the main requirement was to build a separate API service that could retrieve information from the existing Urja Meter Ops portal.

I first explored the portal and checked how it provides transformer and meter information.

Based on my understanding, I decided to use Java and Spring Boot to build the service.

## 2. Assumptions I Made

- I treated the existing portal as read-only.
- I used the portal's existing endpoints to retrieve information.
- I kept the response fields in the same format returned by the portal.
- I used environment variables to configure portal credentials.
- I assumed the portal would be available when my application makes requests.

## 3. Design Decisions

I divided the application into separate layers.

- I used a client class to communicate with the existing portal.
- I used service classes to handle validation and application logic.
- I used controllers to expose REST endpoints.
- I used DTO classes to represent response data.
- I added a global exception handler to return consistent error responses.

I chose this structure because it keeps the code organised and makes individual parts easier to test.

## 4. Challenges I Faced

The main challenge was understanding the portal's login process and how it maintains a session.

I explored the network requests to understand how login worked and how the portal returned data.

I also had to handle the portal's JSON responses and convert them into Java objects.

I worked through these issues by testing the requests separately before connecting them to my Spring Boot application.

## 5. Work I Could Not Complete

I focused on completing the main requirements of the assignment first.

I was able to implement the following:
- Transformer details with pagination.
- Meter location API.
- Meter energy readings API.
- Portal login and session handling.
- Input validation and error handling.
- Unit tests and portal integration tests.

I did not implement the following optional improvements:

- **Caching:** I did not add caching for portal responses. Each request currently fetches data from the portal.
- **Local database or index:** I did not store portal data locally.
- **Network hierarchy:** I did not build additional APIs to explore relationships between network elements.
- **Additional portal APIs:** I focused on the three main data types instead of exploring and implementing every available endpoint.

I decided to focus on making the implemented APIs work correctly before adding more features.

## 6. What I Would Improve With One More Day

If I had one more day, I would focus on:

1. Exploring more portal endpoints and adding useful APIs.
2. Improving session handling and error recovery.
3. Adding caching to reduce repeated requests.
4. Adding more integration and error-handling tests.
5. Improving logging and application monitoring.

## 7. What I Learned

This assignment gave me practical experience in exploring an existing application and building a separate API around it.

I learned more about HTTP requests, login sessions, cookies, JSON parsing, Spring Boot architecture and integration testing.

I also understood the importance of keeping code organised and documenting the decisions made during development.