#FROM openjdk:8-jre-alpine
FROM openjdk:11-jre-buster

RUN mkdir /app

WORKDIR /app

ADD ./target/vinjete-1.0.0-SNAPSHOT.jar /app
ADD ./resources /app/resources

EXPOSE 8082

CMD ["java", "-jar", "anpr-1.0.0-SNAPSHOT.jar"]