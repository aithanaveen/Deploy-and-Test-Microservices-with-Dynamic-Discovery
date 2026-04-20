# Deploy and Test Microservices with Dynamic Discovery

This project demonstrates a microservices architecture using **Spring Cloud Netflix Eureka** for Service Registry and Discovery. It eliminates hard-coded service URLs, allowing microservices to find each other dynamically.

## Architecture Overview

1.  **Service Registry (`service-registry`)**: A central Eureka Server where all microservices register themselves.
2.  **Service A (`service-a`)**: A producer microservice that registers with Eureka and provides a simple greeting API.
3.  **Service B (`service-b`)**: A consumer microservice that uses a Load-Balanced `RestTemplate` to dynamically discover and call `service-a`.

## Prerequisites

*   **Java 17 or higher**
*   **Maven** (optional, as the project includes the Maven Wrapper `mvnw`)

## How to Run

Follow these steps in order to ensure proper service registration:

### 1. Start the Service Registry
Open a terminal in the `service-registry` directory and run:
```bash
./mvnw spring-boot:run
```
Wait for the server to start on `http://localhost:8761`. You can view the Eureka Dashboard here.

### 2. Start Service A
In a new terminal, navigate to the `service-a` directory and run:
```bash
./mvnw spring-boot:run
```
It will start on port `8081` and register itself as `SERVICE-A`.

### 3. Start Service B
In another terminal, navigate to the `service-b` directory and run:
```bash
./mvnw spring-boot:run
```
It will start on port `8082` and register itself as `SERVICE-B`.

## Verifying Dynamic Discovery

Wait about 30 seconds for all heartbeats and registry fetches to complete.

1.  **Check Registry**: Visit `http://localhost:8761`. You should see `SERVICE-A` and `SERVICE-B` listed under "Instances currently registered with Eureka".
2.  **Test Communication**: Visit `http://localhost:8082/api/callery`. Service B will dynamically look up Service A and return:
    `Service B called Service A and got response: Hello from Service A!`

## Key Features

*   **No Hard-coded URLs**: Service B calls Service A using `http://service-a/api/hello`.
*   **Client-Side Load Balancing**: Uses `@LoadBalanced` RestTemplate for automatic resolution.
*   **Automatic Registration**: Microservices register on startup using `@EnableDiscoveryClient`.