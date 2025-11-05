# metaFitAI
### AI-powered fitness platform built on microservices architecture — personalized health, optimized performance.


## Overview

This project showcases a scalable microservices backend built with Java Spring Boot. It utilizes event-driven communication with Kafka, secure authentication through Keycloak and OAuth2, and integrates AI capabilities using Google Gemini. The system is designed for cloud readiness with AWS support, centralized configuration, and service discovery for long-term scalability and maintainability.

## Tech Stack

- Java Spring Boot  
- Kafka (Event Streaming)  
- Keycloak (Authentication & OAuth2)  
- PostgreSQL (Database)  
- Eureka (Service Discovery)  
- Config Server (Centralized Configuration)  
- AWS (Cloud Infrastructure)  
- Google Gemini (AI Integration)

## Features

- Distributed microservices for User, Activity, and AI operations  
- Secure API Gateway with token-based authentication  
- Event-driven messaging via Kafka  
- AI integration through external Gemini API  
- Centralized configuration and dynamic service registration  
- OAuth2 support for secure access control  

## Architecture Diagram

The following diagram illustrates the core components and interaction flow between microservices, the message broker, and external integrations.

![ss](https://github.com/user-attachments/assets/7a45803c-688f-4824-b3f2-1e7bb2c9a9c4)


## Setup & Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/amanjha491/metaFitAI.git
   cd metaFitAI
   ```

2. Configure environment variables and Keycloak setup.

3. Run supporting infrastructure:
   - Kafka  
   - Eureka Server  
   - Config Server  
   - Keycloak  

4. Build and start services:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

5. Access the API via the Gateway using valid bearer tokens.

## Usage

- Authenticate through Keycloak on the Gateway.  
- Use User and Activity endpoints for main operations.  
- Call AI endpoints to leverage Google Gemini capabilities.  
- Monitor inter-service communication with Kafka and Eureka dashboards.  

***
