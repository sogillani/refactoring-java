
# Movie Rental Service 

## Introduction

The Movie Rental Service is designed to manage movie rentals and customer information through a scalable and interoperable platform. Built on Spring Boot with RESTful interface, designed to facilitates seamless integration into larger application. This service utilizes Spring Data to store customer, movie, and rental data in a database, initially configured with H2 in-memory database support. However, it is adaptable to other relational database management systems (RDBMS) such as MySQL or PostgreSQL.

### Requirements

- JDK: Version 22 or later
- Maven: Version 3.9.7

#### Build & Run the project

```bash
mvn clean install

java -jar target/movierental-0.0.1-SNAPSHOT.jar
```
### OpenAPI Specification
The OpenAPI specification is available at:
```
src/main/resources/static/openapi.yaml
```
Use OpenAPI Spec for E2E Testing and Documentation of API

#### Manual Testing
- Import to Postman
  - Open Postman.
  - Go to "File" -> "Import".
  - Select the OpenAPI specification file openapi.yaml.
  - Postman will generate collections and requests based on the spec.

### Liquibase Schema
Liquibase chosen for movie and customer data persistence
Ensures data survives application restarts, maintains data integrity, and scales better than an in-memory HashMap, and simplifies future database schema changes.




