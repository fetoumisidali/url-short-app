# Build stage
FROM node:22.17.0-alpine as build-stage

WORKDIR /app

# Copy package files
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy project files
COPY . .

# Build the Vue app for production
RUN npm run build

# Production stage - serve with simple HTTP server
FROM node:22.17.0-alpine as production-stage

WORKDIR /app

# Install serve (lightweight static file server)
RUN npm install -g serve

# Copy built files from build stage
COPY --from=build-stage /app/dist ./dist

# Expose port (will be mapped to unique port by docker-compose)
EXPOSE 3000

# Serve the static files
CMD ["serve", "-s", "dist", "-l", "3000"]