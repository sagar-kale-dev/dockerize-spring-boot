# Dockerize Spring Boot

## Using docker compose
This Docker Compose setup ensures:

- **Database data persistence** – The PostgreSQL data is stored in a Docker volume, so it remains intact across container restarts.
- **Application startup order** – The app service depends on the database service, ensuring the DB container starts first.
- **Dedicated network communication** – Both the app and database are connected to a custom Docker network, allowing secure and isolated communication between them.

#### docker-compose.yml 

```yaml
version: '3.9'  # Specify the Docker Compose version

services:
  # PostgreSQL database service
  docker-compose-app-db:
    image: postgres:18  # Use PostgreSQL version 18
    container_name: docker-compose-app-db  # Name of the container
    environment:
      POSTGRES_DB: usersDB          # Database name
      POSTGRES_USER: sagar          # Database username
      POSTGRES_PASSWORD: "sagar@123"  # Database password
    networks:
      - docker-compose-app-network  # Connect to custom network
    volumes:
      - docker-compose-app-data:/var/lib/postgresql  # Persist DB data

  # Application service
  docker-compose-app-service:
    image: sagar855/docker-compose-app:1.0  # Docker image for the app
    container_name: docker-compose-app-service  # Name of the app container
    ports:
      - "8090:8090"  # Map host port 8090 to container port 8090
    networks:
      - docker-compose-app-network  # Connect to same network as DB
    depends_on:
      - docker-compose-app-db  # Ensure DB starts before the app

# Define custom network
networks:
  docker-compose-app-network:

# Define persistent volume for PostgreSQL
volumes:
  docker-compose-app-data:
```
## Recommended Approach for Dev

```yaml
services:
  docker-compose-app-service:
    build: .
    image: docker-compose-app-img
    container_name: docker-compose-app-service
```

Builds Spring Boot app image from the local Dockerfile and tags it docker-compose-app-img.

#### Why `build: .` is Not Ideal in Production

* `build: .` tells Docker Compose to **build the image from source code on the fly**.

* In production, this can cause issues:

  * The server must have **all build tools** installed (e.g., Maven, JDK).

  * Image builds are slower and **not deterministic** unless carefully controlled.

  * It’s harder to **version/tag images** reliably for deployments.

## Recommended Approach for Production (Used in this App)

Instead of building on the server, **pre-build the image locally or in CI/CD** and push it to a **Docker registry** (Docker Hub, ECR, GCR, etc.).

### 1️⃣ Build and push the image

```bash
# Build the Docker image and tag it
# docker build -t your-dockerhub-user/linkedin-app-user-service:1.0 .

docker build -t sagar855/docker-compose-app:1.0 .

# Push the image to Docker Hub (or any registry)
# docker push your-dockerhub-user/linkedin-app-user-service:1.0

docker push sagar855/docker-compose-app:1.0
```

<img width="1352" height="594" alt="image" src="https://github.com/user-attachments/assets/15d3b17f-79d6-47f2-a7ef-c560d763d643" />

### 2️⃣ Use the pre-built image in Docker Compose

In `docker-compose.yml`, referenced the **tagged image** directly:

```yaml
services:
  app:
    image: sagar855/docker-compose-app:1.0
```

## App testing

<img width="1348" height="605" alt="image" src="https://github.com/user-attachments/assets/2c756b71-c30b-4a87-8140-ee6f1d0367f3" />

<img width="1364" height="644" alt="image" src="https://github.com/user-attachments/assets/2ea62290-7bba-4d0e-84bd-448c5206e886" />

