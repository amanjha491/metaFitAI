# 🏋️‍♂️ metaFitAI

### AI-powered fitness platform built on a scalable microservices architecture

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Kafka](https://img.shields.io/badge/Kafka-Event%20Streaming-blue.svg)](https://kafka.apache.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue.svg)](https://www.postgresql.org/)
[![MongoDB](https://img.shields.io/badge/MongoDB-Database-green.svg)](https://www.mongodb.com/)
[![AI](https://img.shields.io/badge/AI-Google%20Gemini%20|%20GROQ-yellow.svg)](https://deepmind.google/technologies/gemini/)

## 📖 Overview

metaFitAI is a scalable microservices backend built with Java Spring Boot (v3.5) and Spring Cloud (v2025). It uses event-driven communication with Kafka and integrates AI capabilities using Google Gemini and GROQ to generate dynamic fitness recommendations. The system encompasses centralized configuration, service discovery, and API gateway routing to support long-term maintainability.

## 🛠️ Tech Stack

- **Framework**: Java 21, Spring Boot, Spring WebFlux
- **Databases**: 
  - PostgreSQL (User management)
  - MongoDB (Activity tracking and AI metadata)
- **Message Broker**: Apache Kafka
- **Service Discovery**: Netflix Eureka
- **Configuration**: Spring Cloud Config Server
- **Gateway**: Spring Cloud Gateway
- **AI Integration**: Google Gemini / GROQ

## 🏗️ Architecture Design & Services

The platform consists of specialized, independent microservices:

1. **`configServer`**: Centralized configuration management for all underlying services.
2. **`eureka`**: Service registry and discovery.
3. **`gateway`**: API Gateway managing routing and acting as the external-facing entrypoint for all downstream services.
4. **`userService`**: Service backed by PostgreSQL natively responsible for user profile management.
5. **`activityService`**: Service backed by MongoDB for tracking physical activities/workouts, utilizing Kafka for asynchronous event propagation.
6. **`aiService`**: Service interacting with Google Gemini / GROQ to provide intelligent insights, utilizing MongoDB for data storage and Kafka to subscribe to user and activity events.

## ⚙️ Setup & Installation

### Prerequisites
- Java 21+
- PostgreSQL
- MongoDB
- Apache Kafka

### 1. Clone the repository
```bash
git clone https://github.com/amanjha491/metaFitAI.git
cd metaFitAI
```

### 2. Configure Infrastructure
Ensure you have the following infrastructure running locally:
- **Kafka** (Running locally on default ports)
- **PostgreSQL** (Create the database needed for `userService`)
- **MongoDB** (Create databases for `activityService` and `aiService`)

### 3. Build and Run Services
Each service can be built and run individually. Start the foundation services first before spinning up the domain microservices.

**Boot Order:**
1. `configServer`
2. `eureka`
3. `gateway`
4. `userService` / `activityService` / `aiService`

To build and run a specific service:
```bash
cd <service_name>
./mvnw clean install
./mvnw spring-boot:run
```

Ensure valid API Keys for Google Gemini or GROQ are available in your local configuration context prior to starting the `aiService`.

## 🔄 Core Workflow

1. **Client Interaction**: Clients invoke REST APIs through the unified `gateway` service endpoint.
2. **User Profiles**: Manage accounts natively with `userService`.
3. **Activity Capture**: Sync fitness activities to `activityService`.
4. **AI Generation**: Relying on data across different service contexts, the `aiService` leverages Google Gemini / GROQ to construct specific, personalized AI fitness analytics.
5. **Event Pipeline**: Interactions that span across bounds trigger messages pushed via Apache Kafka.
