# Cloud-Native Food Delivery Platform

This repository contains a full-stack, microservices-based food delivery application built with Angular and Spring Boot, containerized with Docker, orchestrated using Kubernetes (Minikube), and automated with Jenkins CI/CD pipelines.

## Table of Contents

- [Architecture Overview](#architecture-overview)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Microservices](#microservices)
- [Frontend](#frontend)
- [Deployment](#deployment)
- [CI/CD Pipeline](#cicd-pipeline)
- [Local Development](#local-development)
- [Usage](#usage)
- [Troubleshooting](#troubleshooting)


---

## Architecture Overview

- **Frontend:** Angular SPA for restaurant browsing, food ordering, and user management.
- **Backend:** Multiple Spring Boot microservices for restaurant listing, food catalog, order processing, user info, and service discovery (Eureka).
- **Database:** MySQL and MongoDB for persistent storage.
- **Service Discovery:** Eureka for dynamic service registration and discovery.
- **Monitoring:** Grafana and Prometheus for metrics and dashboards.
- **API Documentation:** Swagger/OpenAPI for REST API documentation.
- **Containerization:** Docker for all services.
- **Orchestration:** Kubernetes manifests and Helm charts for deployment.
- **Ingress:** NGINX Ingress for routing and domain management.
- **CI/CD:** Jenkins pipelines for automated build, test, and deployment.

---

## Technologies Used

- **Frontend:** Angular, TypeScript, HTML, CSS
- **Backend:** Spring Boot, Java, Spring Data JPA, Spring Cloud Eureka
- **Databases:** MySQL, MongoDB
- **API Docs:** Swagger/OpenAPI
- **Containerization:** Docker
- **Orchestration:** Kubernetes, Minikube, Helm
- **Ingress:** NGINX Ingress Controller
- **Monitoring:** Prometheus, Grafana
- **CI/CD:** Jenkins
- **Other:** Maven, Lombok

---

## Project Structure

```
deployment-folder/
    aws/
        angular-manifest.yml
        eureka-manifest.yml
        food-catalogue-manifest.yml
        order-manifest.yml
        restaurant-manifest.yml
        user-manifest.yml
        ingress.yml
        configmap.yml
        deploy-minikube.sh
        ...
    Fast-deployment-runner.yml
    ...
food-delivery-app-FE/
    src/
    Dockerfile
    Jenkinsfile
    ...
restaurantlistingNew/
    restaurantlisting/
    Dockerfile
    ...
foodcatalog/
    foodcatalog/
    Dockerfile
    ...
order/
    order/
    Dockerfile
    ...
userinfo/
    userinfo/
    Dockerfile
    ...
eureka/
    eureka/
    Dockerfile
    ...
```

---

## Microservices

- **Restaurant Listing Service:** Manages restaurant data and exposes REST endpoints for listing and details.
- **Food Catalogue Service:** Handles food menu items for restaurants.
- **Order Service:** Processes customer orders and manages order history.
- **User Info Service:** Manages user registration, authentication, and profiles.
- **Eureka Service:** Service registry for dynamic discovery of microservices.

Each service is built with Spring Boot, uses RESTful APIs, and is containerized with Docker.

---

## Frontend

- **Angular SPA:** Provides user interface for browsing restaurants, viewing food catalogues, placing orders, and managing user accounts.
- **API Integration:** Communicates with backend microservices via REST APIs.
- **Responsive Design:** Mobile-friendly and desktop-ready layouts.

---

## Deployment

- **Docker:** All services and frontend are built as Docker images.
- **Kubernetes:** Manifests for Deployments, Services, ConfigMaps, Secrets, and Ingress.
- **Minikube:** Recommended for local development and testing.
- **Ingress:** NGINX Ingress routes traffic to services using custom domains (e.g., `food-delivery.local`).
- **Helm:** Optional Helm charts for templated deployments.

### Example Ingress Setup

```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: food-delivery-ingress
spec:
  rules:
    - host: food-delivery.local
      http:
        paths:
          - path: /
            pathType: Prefix
            backend:
              service:
                name: angular-service
                port:
                  number: 80
          - path: /restaurant
            pathType: Prefix
            backend:
              service:
                name: restaurant-service
                port:
                  number: 9091
          # ... other paths for microservices
```

---

## CI/CD Pipeline

- **Jenkins:** Pipelines defined in `Jenkinsfile` for each service and frontend.
- **Build Steps:** Compile code, run tests, build Docker images, push to registry.
- **Deploy Steps:** Apply Kubernetes manifests or Helm charts to Minikube.
- **GitOps:** Optionally update manifests in deployment repo for automated rollout.

---

## Local Development

### Prerequisites

- Docker
- Minikube
- kubectl
- Helm (optional)
- Node.js & npm (for Angular)
- Maven (for Java services)
- Jenkins (for CI/CD)

### Steps

1. **Start Minikube:**
   ```sh
   minikube start
   minikube addons enable ingress
   minikube tunnel
   ```

2. **Build and Push Docker Images:**
   - For each service and frontend, run Docker build and push commands.

3. **Apply Kubernetes Manifests:**
   ```sh
   kubectl apply -f deployment-folder/aws/
   ```

4. **Update Hosts File:**
   - Add Minikube IP mappings for custom domains:
     ```
     <minikube-ip> food-delivery.local eureka.local grafana.local
     ```

5. **Access Application:**
   - Frontend: http://food-delivery.local
   - Eureka Dashboard: http://eureka.local
   - Grafana: http://grafana.local
   - Prometheus: http://prometheus.local

---

## Usage

- **Browse Restaurants:** View available restaurants and their menus.
- **Place Orders:** Select food items and place orders.
- **User Management:** Register, login, and manage user profiles.
- **Monitor Services:** Use Grafana and Prometheus dashboards for metrics.
- **API Docs:** Access Swagger UI for API documentation.

---

## Troubleshooting

- **Pods Not Running:** Check logs with `kubectl logs <pod-name>`.
- **Ingress Not Routing:** Ensure ingress controller is running and hosts file is updated.
- **Database Connection Issues:** Verify MySQL and MongoDB services are healthy.
- **CORS Errors:** Ensure `@CrossOrigin` is set on Spring controllers.
- **Image Pull Errors:** Check Docker registry credentials and image tags.
- **CI/CD Failures:** Review Jenkins logs for build and deployment errors.



## Contact

For questions or support, please open an issue or contact udeshshashitha@gmail.com.
