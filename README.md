# Scalable Tracker Number Generator API

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A **RESTful API** for generating unique tracking numbers for parcels. This project leverages Spring Boot (WebFlux), MongoDB (Reactive), and scalable unique ID generation using ULID.

---

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Clone the Repository](#clone-the-repository)
  - [Configuration](#configuration)
  - [Build & Run](#build--run)
- [API Usage](#api-usage)
  - [Endpoints](#endpoints)
  - [Sample Request/Response](#sample-requestresponse)
- [Testing](#testing)
- [Monitoring](#monitoring)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **RESTful API** to generate, fetch, and manage unique tracking numbers.
- **Stateless & Scalable**: Designed for cloud-native environments.
- **ULID**-based identifiers (Universally Unique Lexicographically Sortable Identifier).
- **Reactive stack** using Spring WebFlux and MongoDB Reactive.
- **Validation** of API inputs.
- **Monitoring** with Spring Boot Actuator and CloudWatch metrics.
- **Docker-ready** (add Dockerfile if needed).

---

## Architecture

- **Spring Boot (WebFlux)**: Asynchronous, non-blocking, and highly scalable.
- **MongoDB (Reactive)**: Stores tracking number records.
- **ULID**: Generates unique, sortable identifiers.
- **Micrometer + CloudWatch**: Application metrics and monitoring.

---

## Tech Stack

- Java 17
- Spring Boot 3.2.x (WebFlux, Actuator, Validation, Data MongoDB Reactive)
- MongoDB (Reactive)
- ULID Creator
- Micrometer (CloudWatch registry)
- Maven

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- MongoDB instance (local or cloud)
- (Optional) AWS credentials for CloudWatch metrics

### Clone the Repository

```bash
git clone https://github.com/naman-sriv/Scalable_Tracker_Number_Generator_API.git
cd Scalable_Tracker_Number_Generator_API/TrackerNumber_Service
```

### Configuration

Configure MongoDB and optional CloudWatch credentials in `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/trackerdb

# (Optional) CloudWatch
management.metrics.export.cloudwatch.namespace=TrackerAPI
management.metrics.export.cloudwatch.step=1m
cloud.aws.credentials.access-key=<YOUR_AWS_ACCESS_KEY>
cloud.aws.credentials.secret-key=<YOUR_AWS_SECRET_KEY>
cloud.aws.region.static=<YOUR_AWS_REGION>
```

### Build & Run

#### Using Maven

```bash
mvn clean install
mvn spring-boot:run
```

#### Or build a fat JAR

```bash
mvn clean package
java -jar target/TrackingNumber_Service-0.0.1-SNAPSHOT.jar
```

---

## API Usage

### Endpoints

| Method | Endpoint                   | Description                      |
|--------|----------------------------|----------------------------------|
| POST   | `/api/tracking-numbers`    | Generate a new tracking number   |
| GET    | `/api/tracking-numbers`    | List all tracking numbers        |
| GET    | `/api/tracking-numbers/{id}` | Get details for a tracking number|

*(Endpoints may vary; check your controller classes for exact paths.)*

### Sample Request/Response

#### Generate a Tracking Number

**Request:**

```http
POST /api/tracking-numbers
Content-Type: application/json

{
  "parcelId": "12345",
  "customerEmail": "customer@example.com"
}
```

**Response:**

```json
{
  "trackingNumber": "01HZYJ3F9Z0Z4T4C7AZXJ4V7CC",
  "createdAt": "2025-06-07T13:54:58Z"
}
```

#### Get All Tracking Numbers

```http
GET /api/tracking-numbers
```

---

## Testing

Run unit and integration tests using:

```bash
mvn test
```

---

## Monitoring

Spring Boot Actuator exposes health checks and metrics:

- Health: `GET /actuator/health`
- Metrics: `GET /actuator/metrics`
- CloudWatch integration (if configured)

---

## Contributing

Contributions are welcome! Please open issues and pull requests.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Contact

- [GitHub Repo](https://github.com/naman-sriv/Scalable_Tracker_Number_Generator_API)
- Maintainer: [naman-sriv](https://github.com/naman-sriv)

---

*Feel free to customize this README with further details, code samples, or architecture diagrams as your project evolves.*
