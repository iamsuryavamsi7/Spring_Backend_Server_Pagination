# -------- Stage 1: Build the application --------
FROM eclipse-temurin:21-jdk as builder

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

# -------- Stage 2: Runtime with smaller JRE --------
FROM eclipse-temurin:21-jre

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app.jar"]