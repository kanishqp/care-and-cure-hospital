# Care and Cure Hospital Management System

Spring Boot + MySQL hospital management app, split into two apps:

- `backend/` - REST API (port 8080)
- `ui/` - Thymeleaf web UI (port 8081)

## Requirements
- JDK 17+
- MySQL 8

## Setup
1. In both `backend/src/main/resources` and `ui/src/main/resources`, copy `application.properties.example` to `application.properties` and fill in your values (DB password, mail, Razorpay keys).
2. Start the backend: `cd backend` then `.\mvnw.cmd spring-boot:run`
3. Start the UI: `cd ui` then `.\mvnw.cmd spring-boot:run`
4. Open http://localhost:8081/mainLoginPage

The database `Care_and_Cure` is created automatically.

## Modules
Doctors, Patients, Appointments, Billing, Payments, Admin reports.
