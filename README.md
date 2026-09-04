# ecommerce-backend

Spring Boot Maven backend for the Ecommerce end-to-end project.

## Requirements

- Java 17+
- Maven
- PostgreSQL database repository running on localhost:5432

Start the database first:

```bash
docker compose up -d
```

Then run:

```bash
mvn clean test
mvn clean package
mvn spring-boot:run
```

API:
http://localhost:8080

Health:
http://localhost:8080/actuator/health

## Main APIs

```text
GET  /api/products
POST /api/products
PUT  /api/products/{id}
DELETE /api/products/{id}

GET  /api/customers

GET  /api/orders
POST /api/orders
PUT  /api/orders/{id}/status?value=SHIPPED

GET  /api/dashboard/summary
```

The database schema is managed by the separate `ecommerce-database` repository. Hibernate uses `validate`, so the backend verifies that the expected database schema exists instead of creating tables itself.
