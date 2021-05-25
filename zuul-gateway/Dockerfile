FROM openjdk:8-jdk-alpine
LABEL maintainer="zlatanovic.ra150.2012@uns.ac.rs, puskarov.ra244.2020@uns.ac.rs"
EXPOSE 8762
WORKDIR /app
COPY ./target/zuul-gateway-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "zuul-gateway-0.0.1-SNAPSHOT.jar"]