
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

### Liquibase Schema
Liquibase chosen for movie and customer data persistence
Ensures data survives application restarts, maintains data integrity, and scales better than an in-memory HashMap, and simplifies future database schema changes.


