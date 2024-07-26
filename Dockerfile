FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/gym-api-rest.jar /app/gym-api-rest.jar
EXPOSE 8080
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql-db:3307/gymjava
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=bibiner
ENTRYPOINT ["java", "-jar", "/app/gym-api-rest.jar"]
