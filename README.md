# Consultório Backend API

This project is a Spring Boot application that provides a RESTful API for managing a dental clinic's appointments. It allows you to manage patients, dentists, and appointments.

## Technologies Used

* Java 17
* Spring Boot 3
* Maven
* PostgreSQL
* Spring Security (OAuth2/Opaque Token)

## Documentation

For detailed information about the changes, security implementation, and testing, please refer to the following documents:

* [**MODIFICACOES.md**](MODIFICACOES.md): A summary of all changes, new features, and improvements made to the project.
* [**SECURITY.md**](SECURITY.md): Detailed explanation of the OAuth 2.0 Resource Server implementation and security configuration.
* [**TESTING.md**](TESTING.md): Guide on how to run tests, specifically addressing security considerations in controller tests.
* [**API_ENDPOINTS.md**](API_ENDPOINTS.md): (If available) List of all available API endpoints.

## Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/seu-usuario/consultorio-backend.git
   ```

2. **Configure the database:**
   - Make sure you have PostgreSQL installed and running.
   - Create a database named `consultorio`.
   - Update the `application.properties` file in `src/main/resources` with your database credentials:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/consultorio
     spring.datasource.username=your-username
     spring.datasource.password=your-password
     ```

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

## Usage

The API provides the following endpoints:

### Patients

* `GET /api/pacientes`: Get all patients.
* `GET /api/pacientes/{id}`: Get a patient by ID.
* `POST /api/pacientes`: Create a new patient.
* `PUT /api/pacientes/{id}`: Update a patient.
* `DELETE /api/pacientes/{id}`: Delete a patient.

### Dentists

* `GET /api/dentistas`: Get all dentists.
* `GET /api/dentistas/{id}`: Get a dentist by ID.
* `POST /api/dentistas`: Create a new dentist.
* `PUT /api/dentistas/{id}`: Update a dentist.
* `DELETE /api/dentistas/{id}`: Delete a dentist.

### Appointments

* `POST /api/consultas/agendar`: Schedule a new appointment.
* `DELETE /api/consultas/{id}`: Cancel an appointment.
* `GET /api/consultas`: Get all appointments.
* `GET /api/consultas/{id}`: Get an appointment by ID.
* `GET /api/consultas/dentista/{dentistaId}?dia={dia}`: Get all appointments for a dentist on a specific day.
