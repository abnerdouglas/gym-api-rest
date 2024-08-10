FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/gym-api-rest.jar /app/gym-api-rest.jar
COPY src/main/resources/application-docker.properties /config/
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java","-Dspring.config.location=/config/application-docker.properties", "-jar", "/app/gym-api-rest.jar"]
