# Dockerfile for a Node.js frontend application
FROM node:24-slim

WORKDIR /app    

RUN apt-get update && apt-get install -y --no-install-recommends \
    build-essential && \
    rm -rf /var/lib/apt/lists/*

COPY ./frontend/package*.json ./
RUN npm install

COPY ./frontend /app


