
# Movie Rental Service 

## Introduction

The Movie Rental Service is designed to manage movie rentals information through a scalable and interoperable platform. Built on Spring Boot with RESTful interface, designed to facilitates seamless integration into larger application. This service utilizes Spring Data to store movies data in a database, initially configured with H2 in-memory database support. However, it is adaptable to other relational database management systems (RDBMS) such as MySQL or PostgreSQL.

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

### Strategy Pattern for Rent and Bonus Points Calculation

This project utilizes the Strategy Pattern to implement flexible rent and bonus point calculations for different movies types identified with MovieCode. This allows for:
- Define separate calculation strategies for each movie type.
- Easy addition of new movie codes with custom strategies in the future.

We have two functional interfaces for rent and bonus calculation 
- RentalStrategy
- BonusPointsStrategy

#### Adding New Movie Codes:

In the future, when you introduce a new movie code, you can simply:
- Add a new code to the MovieCode enum.
- Create a corresponding implementation of functional interface RentalStrategy for new movie code with rent logic.
- Add new functional interface implementation in RentCalculator->rentalStrategies enum map. RentCalculator will start computing the rent for new movie code.


### Liquibase Schema
Liquibase chosen for movie and customer data persistence
Ensures data survives application restarts, maintains data integrity, and scales better than an in-memory HashMap, and simplifies future database schema changes.




