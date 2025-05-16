FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/core-service-0.0.1-SNAPSHOT.jar /app/core-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "core-service.jar"]