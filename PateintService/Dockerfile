# ---- Stage 1: Build the JAR ----
FROM eclipse-temurin:25.0.2_10-jdk AS builder

WORKDIR /app

# Copy pom.xml first (so Maven dependencies are cached)
COPY mvn./ mvn
COPY mvnw pom.xml ./

# We fix Windows line endings (so Linux doesn't crash) and make the wrapper executable
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

RUN ./mvnw dependency:go-offline

# Copy source code and build
COPY src ./src
RUN ./mvn clean package -DskipTests

# ---- Stage 2: Run the JAR ----
FROM eclipse-temurin:25.0.2_10-jre-ubi10-minimal

WORKDIR /app

# Copy ONLY the built JAR from Stage 1
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 4001

ENTRYPOINT ["java", "-jar", "app.jar"]