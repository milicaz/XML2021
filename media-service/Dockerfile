FROM openjdk:8-jdk-alpine
LABEL maintainer="zlatanovic.ra150.2012@uns.ac.rs, puskarov.ra244.2020@uns.ac.rs"
EXPOSE 8400
WORKDIR /app
COPY ./target/media-service-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "media-service-0.0.1-SNAPSHOT.jar"]