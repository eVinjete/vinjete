FROM maven:3.6.3-openjdk-15 AS build
COPY ./ /app
WORKDIR /app

FROM openjdk:11-jre-buster
RUN mkdir /app
WORKDIR /app
COPY --from=build ./app/target/vinjete-1.0.0-SNAPSHOT.jar /app
EXPOSE 8082
CMD ["java", "-jar", "vinjete-1.0.0-SNAPSHOT.jar"]