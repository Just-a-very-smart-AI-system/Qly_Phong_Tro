# Stage 1: Build JAR
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run Application
FROM eclipse-temurin:21-jre
RUN apt-get update && apt-get install -y tzdata && rm -rf /var/lib/apt/lists/*
ENV TZ=Asia/Ho_Chi_Minh
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

#docker build -t quanlyphongtro:latest .
#docker tag quanlyphongtro:latest hoangtdv/quanlyphongtro:1.0.3
#docker push hoangtdv/quanlyphongtro:1.0.3