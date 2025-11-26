# Short - URL Shortener Application

**Live Preview:** [https://1325231.xyz](https://1325231.xyz)

## Overview

Short is a robust, full-stack URL shortener application designed to convert long URLs into concise, shareable links. Built with performance and scalability in mind, it leverages a modern tech stack featuring Spring Boot for the backend and Vue 3 for the frontend.

## Features

*   **URL Shortening:** Instantly generate short links for long URLs.
*   **Redirection:** Fast and reliable redirection from short links to original URLs.
*   **Validation:** Robust URL validation to ensure link integrity.
*   **Containerization:** Fully dockerized for easy deployment.
*   **Modern UI:** Responsive and clean user interface built with Tailwind CSS 4.

## Technology Stack

### Backend (`short-api`)
*   **Framework:** Spring Boot 3.5.8
*   **Language:** Java 21
*   **Database:** MongoDB
*   **Build Tool:** Maven
*   **Key Dependencies:**
    *   Spring Data MongoDB
    *   Spring Boot Starter Web
    *   Spring Boot Starter Validation
    *   Lombok

### Frontend (`short-ui`)
*   **Framework:** Vue 3
*   **Language:** TypeScript
*   **Styling:** Tailwind CSS 4
*   **UI Library:** Nuxt UI
*   **State Management:** Pinia
*   **Data Fetching:** TanStack Query
*   **Build Tool:** Vite
*   **Icons:** Lucide Vue Next

## API Documentation

### Create Short URL
Creates a new short URL for the provided long URL.

*   **Endpoint:** `POST /api/v1/shorter`
*   **Content-Type:** `application/json`
*   **Request Body:**
    ```json
    {
      "url": "https://example.com/very/long/url/path"
    }
    ```
*   **Response (201 Created):**
    ```json
    {
      "shortUrl": "http://localhost:8081/api/v1/shorter/a1b2c"
    }
    ```

### Access Short URL
Redirects to the original URL.

*   **Endpoint:** `GET /api/v1/shorter/{shortId}`
*   **Path Parameters:**
    *   `shortId`: The unique identifier of the short URL.
*   **Response:** `302 Found` (Redirects to original URL)

## Getting Started

### Prerequisites
*   Docker & Docker Compose
*   Java 21 (for local backend dev)
*   Node.js & npm (for local frontend dev)

### Running with Docker Compose (Recommended)
The easiest way to run the application is using Docker Compose, which sets up MongoDB, the Backend, and the Frontend.

1.  Clone the repository.
2.  Navigate to the project root.
3.  Run the following command:
    ```bash
    docker-compose up -d --build
    ```
4.  Access the application:
    *   **Frontend:** `http://localhost:3000`
    *   **Backend API:** `http://localhost:8081`

### Local Development Setup

#### Backend
1.  Navigate to the `short-api` directory.
2.  Ensure MongoDB is running locally or update configuration.
3.  Run the application:
    ```bash
    ./mvnw spring-boot:run
    ```

#### Frontend
1.  Navigate to the `short-ui` directory.
2.  Install dependencies:
    ```bash
    npm install
    ```
3.  Start the development server:
    ```bash
    npm run dev
    ```
