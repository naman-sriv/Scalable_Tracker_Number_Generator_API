# Scalable Tracker Number Generator API

A scalable, reactive RESTful API for generating unique tracking numbers for parcels. Built with Spring Boot, Project Reactor, and MongoDB, this service is designed for high throughput and reliability in logistics, e-commerce, and shipping platforms.

---

## Features

- **Unique Tracking Number Generation**: Leverages ULID-based IDs for sortable, globally unique tracking numbers.
- **Reactive API**: Built on Project Reactor for high scalability and non-blocking IO.
- **Customizable Metadata**: Associates tracking numbers with origin/destination, customer, and parcel attributes.
- **Persistence**: Uses MongoDB for storing and validating tracking numbers.
- **Robustness**: Retries on duplicate keys and ensures uniqueness.
- **Dockerized**: Easy to containerize and deploy.

---

## Architecture Overview

- **Spring Boot**: Framework for REST API and application lifecycle.
- **Project Reactor**: Reactive programming for non-blocking request handling.
- **MongoDB (Reactive)**: Stores tracking numbers and parcel metadata.
- **Docker**: Containerizes the service for portability and scaling.

---

## Directory Structure

- `TrackerNumber_Service/`
  - `src/main/java/com/learning/trackingnumber_service/`
    - `controller/` — REST API endpoints
    - `service/` — Business logic for tracking number generation
    - `repository/` — Reactive MongoDB repository
    - `dto/` — Data transfer objects (request/response, document model)
    - `config/` — Async and application configuration
  - `src/test/java/com/learning/trackingnumber_service/` — Unit tests
  - `Dockerfile` — Build the service container
  - `docker-compose.yml` — Compose file for local development/testing
  - `pom.xml` — Maven configuration
  - `HELP.md` — Developer notes and Spring Boot guide links

[View the full TrackerNumber_Service directory in GitHub.](https://github.com/naman-sriv/Scalable_Tracker_Number_Generator_API/tree/main/TrackerNumber_Service)

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- Docker (optional, for containerization)
- MongoDB (local or remote)

### Build and Run (Locally)

```bash
cd TrackerNumber_Service
mvn clean package
java -jar target/*.jar
```

Or using Docker:

```bash
docker build -t tracker-number-service .
docker run -p 8080:8080 tracker-number-service
```

Or with Docker Compose:

```bash
docker-compose up --build
```

### Configuration

All configuration (database, async pool, etc.) is managed via `application.properties` or environment variables. See `AsyncConfig.java` for async pool tuning.

---

## API Usage

### Endpoint

```
POST /api/tracking-number
```

#### Request Body

```json
{
  "origin_country_id": "IND",
  "destination_country_id": "USA",
  "createdAt": "2025-05-29T00:00:00Z",
  "weight": 2.5,
  "customerId": "uuid-string",
  "customerName": "John Doe",
  "customerSlug": "john-doe"
}
```

#### Successful Response

```json
{
  "trackingNumber": "01HXYZ...",
  "generatedAt": "2025-05-29T04:49:36Z"
}
```

- Tracking numbers are globally unique and sortable.
- Handles retries in case of collisions.

---

## Developer Guide

See [`HELP.md`](TrackerNumber_Service/HELP.md) for links to relevant Spring Boot and Maven documentation.

### Testing

Unit tests are provided for core services. Run:

```bash
mvn test
```

---

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

---

## Contributing

Pull requests and issues are welcome! Please open an issue to discuss your suggestions.

---

## Author

[Naman Srivastava](https://github.com/naman-sriv)
