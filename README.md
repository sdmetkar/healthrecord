# Health Record Application

A Spring Boot REST API for managing patient health records with visit tracking and diagnosis management.

## Features

- Patient management (CRUD operations)
- Doctor management
- Visit scheduling and tracking
- Diagnosis recording
- Patient cohort selection
- Advanced search and filtering

## Tech Stack

- Java 17
- Spring Boot 3.5.4
- Spring Data JPA
- MySQL 8.0
- MapStruct for mapping
- Swagger/OpenAPI documentation
- Maven

## Quick Start

### Prerequisites

- Java 17+
- MySQL 8.0
- Maven 3.6+

### Setup

1. Clone the repository
2. Configure MySQL database in `application.properties`
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### API Documentation

Access Swagger UI at: `http://localhost:8080/swagger-ui/index.html`

## Database Configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/healthrecord
spring.datasource.username=root
spring.datasource.password=your_password
```

## Docker

Build and run with Docker:

```bash
mvn clean package -DskipTests
docker build -t healthrecord .
docker run -p 8080:8080 healthrecord
But above commands expect mysql to be running 
docker run --name healthrecord_db -e MYSQL_ROOT_PASSWORD=yours -e MYSQL_DATABASE=yours -p 3306:3306 -d mysql:8

Use self sufficient compose file with mysql, prometheus and grafana in built
docker compose -f .\docker-compose.monitoring.yml up --build  

You will need to pass DB_USERNAME=
DB_PASSWORD=
MYSQL_ROOT_PASSWORD=
MYSQL_DATABASE=healthrecord

from env variables
```

## API Endpoints

### Patients

- `GET /api/patients` - List all patients
- `GET /api/patients/{id}` - Get patient by ID
- `POST /api/patients` - Create patient
- `PUT /api/patients/{id}` - Update patient
- `DELETE /api/patients/{id}` - Delete patient
- `GET /api/patients/search?name={name}` - Search patients by name
- `POST /api/patients/search/criteria` - Search patients by criteria

### Doctors

- `GET /api/doctors` - List all doctors
- `GET /api/doctors/{id}` - Get doctor by ID
- `POST /api/doctors` - Create doctor
- `PUT /api/doctors/{id}` - Update doctor
- `DELETE /api/doctors/{id}` - Delete doctor
- `GET /api/doctors/search?name={name}` - Search doctors by name

### Visits

- `GET /api/visits` - List all visits
- `GET /api/visits/{id}` - Get visit by ID
- `POST /api/visits` - Create visit (requires patientId, doctorId, date)
- `PUT /api/visits/{id}` - Update visit
- `DELETE /api/visits/{id}` - Delete visit
- `GET /api/visits/patient/{patientId}` - Get visits by patient
- `GET /api/visits/doctor/{doctorId}` - Get visits by doctor
- `GET /api/visits/search` - Search visits with filters

### Diagnoses

- `GET /api/diagnoses` - List all diagnoses
- `GET /api/diagnoses/{id}` - Get diagnosis by ID
- `POST /api/diagnoses` - Create diagnosis
- `PUT /api/diagnoses/{id}` - Update diagnosis
- `DELETE /api/diagnoses/{id}` - Delete diagnosis
- `GET /api/diagnoses/visit/{visitId}` - Get diagnoses by visit

### Cohorts

- `GET /api/cohorts` - List all cohorts
- `GET /api/cohorts/{id}` - Get cohort by ID
- `POST /api/cohorts` - Create cohort
- `PUT /api/cohorts/{id}` - Update cohort
- `DELETE /api/cohorts/{id}` - Delete cohort
- `GET /api/cohorts/{id}/patients` - Get patients for cohort

## Testing

Run tests:

```bash
mvn test
```