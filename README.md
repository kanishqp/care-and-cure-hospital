# Care and Cure Hospital Management System

A full-stack hospital management web application built with **Spring Boot**, **MySQL** and **Thymeleaf**. It is split into two apps: a REST API backend and a server-rendered web UI that consumes it.

## Features

- **Admin dashboard** with patient, appointment and revenue overview and a revenue chart (Chart.js)
- **Doctor management**: add, update, view by ID or specialization, active doctors, availability by day or time slot
- **Patient management**: register, update, view by doctor or status, medical history, profile
- **Appointment management**: book, reschedule, cancel, view by date, doctor or patient, upcoming appointments
- **Billing**: create, update and view bills
- **Payments**: transaction history, filter by date, mode or status, Razorpay online payment (test mode)
- **Reports**: doctor revenue, doctor availability, appointment reports, most frequently booked doctors, low-consultation doctors, no-show report, daily payment summary, payment method analysis, with PDF export
- **Email notifications** through SMTP
- **Validation and error handling** with custom exceptions and a global exception handler
- **Scheduled job** that updates patient status automatically

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.4 (Web, Data JPA, Validation, Mail) |
| View | Thymeleaf, HTML, CSS, JavaScript, Bootstrap |
| Database | MySQL 8 |
| Payments | Razorpay Java SDK |
| PDF | Apache PDFBox, iText |
| Build | Maven (wrapper included) |

## Architecture

```
Browser  ->  UI app (port 8081, Thymeleaf)  ->  REST API (port 8080)  ->  MySQL
```

The UI app calls the backend over HTTP using `RestTemplate`. Both apps use the `Care_and_Cure` database, which is created automatically on first run.

## Project Structure

```
care-and-cure-hospital/
├── backend/   Spring Boot REST API (controller, service, repository, entity, dto, validation, scheduler)
├── ui/        Spring Boot + Thymeleaf web UI (controllers, templates, static assets)
└── README.md
```

## Prerequisites

- JDK 17 or later
- MySQL 8 running locally
- Git (to clone)

## Setup and Run

1. Clone the repo:
   ```
   git clone https://github.com/kanishqp/care-and-cure-hospital.git
   cd care-and-cure-hospital
   ```
2. Configure both apps. In `backend/src/main/resources` and `ui/src/main/resources`, copy `application.properties.example` to `application.properties` and fill in:
   - `spring.datasource.password`: your MySQL root password
   - `spring.mail.username` and `spring.mail.password`: Gmail address and app password (backend only, needed for email)
   - `razorpay.key_id` and `razorpay.key_secret`: Razorpay test keys (backend only, needed for payments)
3. Start the backend (port 8080):
   ```
   cd backend
   .\mvnw.cmd spring-boot:run
   ```
4. Start the UI in a second terminal (port 8081):
   ```
   cd ui
   .\mvnw.cmd spring-boot:run
   ```
   On macOS or Linux use `./mvnw spring-boot:run`.
5. Open http://localhost:8081 and click **Login**, or go directly to http://localhost:8081/mainLoginPage.

Login is currently a demo: any username and password is accepted and redirects to the admin dashboard.

## Notes

- Start the backend before the UI.
- `application.properties` is git-ignored so your credentials are never committed.
- Free ports 8080 and 8081 before starting.

## Author

Kanishq Pantawane - [LinkedIn](https://linkedin.com/in/kanishq-pantawane-60431b24a)
