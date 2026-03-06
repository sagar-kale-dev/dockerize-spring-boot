# Dockerize Spring Boot

## Using only docker file

A Dockerfile is used to define how to build a single container image for an application (e.g., a Spring Boot app), specifying the base image, dependencies, and startup command, and it is typically run using Docker commands like docker build and docker run. 

```mermaid
flowchart TD
    subgraph Single_Container_Workflow
        A1[Dockerfile] --> B1[docker build]
        B1 --> C1[docker run]
        C1 --> D1[Spring Boot App Running]
    end
```

## Using docker compose

In contrast, Docker Compose is used to define and run multiple containers together (such as an app, database, and cache) using a docker-compose.yml file, allowing you to start the entire multi-service application stack with a single command like docker compose up.

```mermaid
flowchart TD
    subgraph Multi_Container_Workflow
        A2[docker-compose.yml] --> B2[docker compose up --build]
        B2 --> C2[Spring Boot App Container]
        B2 --> D2[MySQL Container]
        B2 --> E2[Redis Container]
    end
```
