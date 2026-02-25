# Midas
Project repo for the JPMC Advanced Software Engineering Forage program

# Forage Midas – Spring Boot, Kafka, JPA Job Simulation

A Spring Boot service that processes financial transactions from Kafka, validates them, persists records to an H2 database, calls an external Incentive API, updates user balances, and exposes a REST endpoint to query balances.

## Features
- **Kafka-based ingestion**: consumes `Transaction` messages from a Kafka topic.
- **Validation rules**:
  - sender must exist
  - recipient must exist
  - sender must have sufficient balance
  - invalid transactions are ignored
- **Persistence**:
  - stores processed transactions as `TransactionRecord` (includes `amount` + `incentive`)
  - updates `UserRecord.balance` (float)
- **External service integration**:
  - calls Incentive API at `http://localhost:8080/incentive`
  - adds incentive to **recipient only** (sender is not charged for incentive)
- **REST API**:
  - `GET /balance?userId=<id>` → returns JSON `{"amount": <balance>}`
  - returns `{"amount": 0}` when user does not exist

---

## Tech Stack
- Java 17
- Spring Boot
- Spring Kafka
- Spring Data JPA
- H2 (in-memory)
- Maven (wrapper: `./mvnw`)

---

## Project Structure (high-level)
- `foundation/` → DTOs (`Transaction`, `Balance`, `Incentive`)
- `entity/` → JPA entities (`UserRecord`, `TransactionRecord`)
- `repository/` → Spring Data repositories
- `component/` → Kafka listener + Incentive client
- `controller/` → REST controller (`/balance`)
- `services/` → external Incentive API jar (`transaction-incentive-api.jar`)

---

## Configuration
Key properties (example):
- Kafka topic: `general.kafka-topic`
- REST server port: `server.port: 33400`
- Incentive API: `http://localhost:8080/incentive`

---

## How to Run

### 1) Start the Incentive API (required for Tasks 4–5)
In **Terminal A**:
```bash
cd services
java -jar transaction-incentive-api.jar

### 2) Run the Application Tests
In **Terminal B**:
```bash
./mvnw test

Run specific tasks:

```bash
./mvnw -Dtest=TaskTwoTests test
./mvnw -Dtest=TaskThreeTests test
./mvnw -Dtest=TaskFourTests test
./mvnw -Dtest=TaskFiveTests test