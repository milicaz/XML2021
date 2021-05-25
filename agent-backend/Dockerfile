FROM openjdk:8-jdk-alpine
LABEL maintainer="zlatanovic.ra150.2012@uns.ac.rs, puskarov.ra244.2020@uns.ac.rs"
EXPOSE 8600
WORKDIR /app
COPY ./target/agent-backend-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "agent-backend-0.0.1-SNAPSHOT.jar"]