FROM openjdk:8-jdk-alpine
LABEL maintainer="zlatanovic.ra150.2012@uns.ac.rs"
EXPOSE 8900
WORKDIR /app
COPY ./target/profile-service-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "profile-service-0.0.1-SNAPSHOT.jar"]