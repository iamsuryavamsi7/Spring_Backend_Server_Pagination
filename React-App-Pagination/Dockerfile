# Step 1: Build the React app using Node
FROM node:22.1.0 AS builder

WORKDIR /app

# Copy package.json and install dependencies
COPY package*.json ./
RUN npm install

# Copy rest of the code and build it
COPY . .
RUN npm run build

# Step 2: Serve the build using 'serve'
FROM node:22.1.0

RUN npm install -g serve

WORKDIR /app

COPY --from=builder /app/dist ./dist

EXPOSE 443

CMD ["serve", "-s", "dist", "-l", "443", "--ssl-cert", "/etc/ssl/fullchain.pem", "--ssl-key", "/etc/ssl/privkey.pem"]