# Short - URL Shortener Application

> **Note:** This project is currently under development and not yet completed.

## Overview

Short is a full-stack URL shortener application that allows users to convert long URLs into short, shareable links.

## Project Structure

### Backend (`short-api`)
- **Technology Stack:** Spring Boot 4.0.0 with Java 21
- **Database:** MongoDB
- **Features:**
  - REST API for URL shortening
  - URL validation
  - URL storage and retrieval
  - Exception handling

### Frontend (`short-ui`)
- **Technology Stack:** Vue 3 with TypeScript
- **Styling:** Tailwind CSS 4
- **Build Tool:** Vite
- **Features:**
  - Modern UI for URL input
  - Responsive design
  - Integration with backend API

## Getting Started

### Backend Setup
```bash
cd short-api
./mvnw spring-boot:run
```

### Frontend Setup
```bash
cd short-ui
npm install
npm run dev
```

## Technologies Used

**Backend:**
- Spring Boot
- Spring Data MongoDB
- Lombok
- Maven

**Frontend:**
- Vue 3
- TypeScript
- Tailwind CSS
- Vite
- Lucide Icons

## Status

This project is currently in active development. Core features are being implemented.
