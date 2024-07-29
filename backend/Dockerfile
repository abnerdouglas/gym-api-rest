FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/gym-api-rest.jar /app/gym-api-rest.jar
COPY backend/src/main/resources/application-docker.properties /config/
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.config.location=/config/application-docker.properties", "-jar", "/app/gym-api-rest.jar"]
