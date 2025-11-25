# Docker Frontend Connection Issue Report

## Problem Diagnosis
The frontend connects to `http://localhost:8080` instead of the configured `VITE_API_URL` because the environment variable is not being correctly passed during the **build process**.

### Technical Explanation
1. **Vite Build Process**: Your frontend is built using Vite. Vite replaces `import.meta.env.VITE_*` variables with their actual values at **build time** (when `npm run build` runs inside the Dockerfile).
2. **Docker Compose Behavior**: In your `docker-compose.yml`, you are passing `VITE_API_URL` under the `environment:` section. This makes the variable available to the **running container** (runtime), but NOT during the image build process.
3. **The Result**: When Docker builds the image and runs `npm run build`, the `VITE_API_URL` variable is empty. Consequently, the code falls back to the default value defined in `short-ui/src/api/axios.ts`:
   ```typescript
   baseURL: import.meta.env.VITE_API_URL || "http://localhost:8080",
   ```

## Solution

To fix this, you need to pass the variable as a **build argument** (ARG) instead of just a runtime environment variable.

### 1. Update `docker-compose.yml`
Add the `args` section under `build`:

```yaml
  frontend:
    build:
      context: ./short-ui
      args:
        VITE_API_URL: ${VITE_API_URL}  # <--- Add this
    container_name: short-ui
    # ... (rest of the file)
```

### 2. Update `short-ui/Dockerfile`
Receive the argument using `ARG` before using it:

```dockerfile
# Build stage
FROM node:22.17.0-alpine as build-stage

WORKDIR /app

# Copy package files
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy project files
COPY . .

# Define the build argument
ARG VITE_API_URL  # <--- Add this
# Set it as an environment variable so the build process can see it
ENV VITE_API_URL=$VITE_API_URL

# Build the Vue app for production
RUN npm run build

# ... (rest of the file)
```

### 3. Rebuild
After making these changes, you must rebuild the container to bake in the new value:
```bash
docker-compose up -d --build frontend
```
