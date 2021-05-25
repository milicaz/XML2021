FROM openjdk:8-jdk-alpine
LABEL maintainer="zlatanovic.ra150.2012@uns.ac.rs"
EXPOSE 8700
WORKDIR /app
COPY ./target/admin-service-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "admin-service-0.0.1-SNAPSHOT.jar"]