# AI Ticket Triage Copilot

This is a backend project built using Java Spring Boot that simulates how support teams handle customer tickets. The idea was to replicate a simple AI-based triage system that can automatically analyze incoming tickets and suggest category, priority, and status based on the content.
Instead of just CRUD APIs, I focused on adding some decision-making logic to make it feel closer to a real-world system.

What it does:

1. Allows creation of support tickets via REST APIs
2. Analyzes ticket title and description to auto-assign:
3. category (like BILLING, ACCESS, etc.)
4. priority (LOW / MEDIUM / HIGH)
5. suggested status
6. Fetch all tickets or a specific ticket by ID
7. Update ticket status (like OPEN → RESOLVED)
8. Validates request data and returns proper error responses
9. Stores everything in PostgreSQL

Tech Stack:

1. Java 21
2. Spring Boot
3. Spring Web (MVC)
4. Spring Data JPA
5. PostgreSQL
6. Jakarta Validation
7. Maven
8. Postman (for testing APIs)
9. Lombok

Project Structure:

I’ve followed a standard layered architecture to keep things clean and scalable:

controller  : handles API requests  
service     : business logic (including triage logic)  
repository  : database access  
entity      : database models  
dto         : request/response objects  
exception   : global error handling  

API Overview:

1. Create Ticket

POST /api/tickets
Creates a new support ticket and automatically assigns category and priority.

2. Get All Tickets

GET /api/tickets
Returns all tickets from the database.

3. Get Ticket By ID

GET /api/tickets/{id}
Fetch a specific ticket using its ID.

4. Analyze Ticket (Triage Simulation)

POST /api/triage/analyze
This endpoint doesn’t store anything — it just analyzes input and returns AI-like suggestions.

5. Update Ticket Status

PATCH /api/tickets/{id}/status
Update the status of an existing ticket.

6. Validation & Error Handling

I’ve added request validation and a global exception handler so that errors are returned in a structured format instead of raw stack traces. This is closer to how production APIs behave.

# Running the Project

1. Clone the repo
2. Open it in IntelliJ
3. Set up your PostgreSQL DB in application.properties
4. Run the application
5. Use Postman to test the APIs