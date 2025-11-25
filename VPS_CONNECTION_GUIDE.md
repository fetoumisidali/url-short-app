# VPS Deployment Guide: Connecting Frontend & Backend with .env

This guide explains the necessary code and configuration changes to make your Short URL application deployable on a VPS using environment variables.

## 1. Frontend Changes (`short-ui`)

Since the frontend is a static application built with Vite, environment variables must be injected **during the build process**.

### A. Update `src/api/axios.ts`

Modify the `baseURL` to use a Vite environment variable instead of a hardcoded string.

**File:** `short-ui/src/api/axios.ts`

```typescript
import axios, {type AxiosInstance } from "axios";

export const axiosInstance: AxiosInstance = axios.create({
  // Use VITE_API_URL if available, otherwise fallback to localhost for local dev
  baseURL: import.meta.env.VITE_API_URL || "http://localhost:8080", 
  timeout: 5000,
  headers: {
    "Content-Type": "application/json",
  },
});
```

### B. Update `Dockerfile`

You need to declare the `VITE_API_URL` as a build argument (`ARG`) and verify it is available during the build step.

**File:** `short-ui/Dockerfile`

Add the `ARG` and `ENV` lines before `npm run build`.

```dockerfile
# ... (previous lines)

# Copy project files
COPY . .

# Accept the build argument
ARG VITE_API_URL
# Make it available as an environment variable during the build
ENV VITE_API_URL=$VITE_API_URL

# Build the Vue app for production
RUN npm run build

# ... (rest of the file)
```

## 2. Docker Compose Changes

Update `docker-compose.yml` to pass the environment variables from your `.env` file to the containers.

**File:** `docker-compose.yml`

1.  **Frontend Service**: Add an `args` section under `build` to pass the API URL.
2.  **Backend Service**: Ensure it pulls necessary variables.

```yaml
services:
  # ... mongodb service ...

  backend:
    # ...
    environment:
      # Ensure these match your backend application-prod.yaml
      - MONGO_URI=mongodb://${MONGO_INITDB_ROOT_USERNAME}:${MONGO_INITDB_ROOT_PASSWORD}@mongodb:27017/${MONGO_INITDB_DATABASE}?authSource=admin
      - MONGO_USER=${MONGO_INITDB_ROOT_USERNAME}
      - MONGO_PASSWORD=${MONGO_INITDB_ROOT_PASSWORD}
      - MONGO_DATABASE=${MONGO_INITDB_DATABASE}
      - ACTIVE_PROFILE=${ACTIVE_PROFILE} # e.g., 'prod'
      - APP_URL=${APP_URL}               # Backend public URL (e.g., http://api.example.com)
      - FRONT_URL=${FRONT_URL}           # Frontend public URL (e.g., http://example.com)
      - URL_SUFFIX=${URL_SUFFIX}
    # ...

  frontend:
    build:
      context: ./short-ui
      args:
        - VITE_API_URL=${VITE_API_URL} # Passes .env var to Dockerfile
    container_name: short-ui
    restart: unless-stopped
    depends_on:
      - backend
    ports:
      - "3000:3000"
    networks:
      - short-network

# ...
```

## 3. The `.env` File

Create a `.env` file in the root directory (same level as `docker-compose.yml`) on your VPS.

**File:** `.env`

```ini
# MongoDB Credentials
MONGO_INITDB_ROOT_USERNAME=admin
MONGO_INITDB_ROOT_PASSWORD=securepassword123
MONGO_INITDB_DATABASE=short_db

# Backend Configuration
ACTIVE_PROFILE=prod
URL_SUFFIX=u
# The public URL where your Backend is accessible
APP_URL=http://<VPS_IP_OR_DOMAIN>:8081
# The public URL where your Frontend is accessible (for CORS/Redirects)
FRONT_URL=http://<VPS_IP_OR_DOMAIN>:3000

# Frontend Configuration (Used at Build Time)
# MUST match the APP_URL above (or the public address of the API)
VITE_API_URL=http://<VPS_IP_OR_DOMAIN>:8081
```

## 4. Deployment Steps

1.  **Push Changes**: Push the code changes (axios.ts, Dockerfile, docker-compose.yml) to your git repository.
2.  **Pull on VPS**: Pull the code on your VPS.
3.  **Create .env**: Create/Edit the `.env` file with your actual VPS IP or Domain.
4.  **Build & Run**:
    ```bash
    docker-compose down
    docker-compose up -d --build
    ```

The `--build` flag is crucial to rebuild the frontend image with the new environment variables.
