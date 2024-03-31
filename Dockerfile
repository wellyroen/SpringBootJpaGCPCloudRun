FROM openjdk:17-jdk-alpine

COPY LICENSE .
COPY README.md .

COPY pom.xml .
COPY mvnw .
COPY .mvn ./.mvn

COPY src src

RUN /bin/sh mvnw clean package

RUN pwd
RUN ls

RUN ls -alt /target/*
RUN ls -alt /target/SpringBootJpa-0.0.1-SNAPSHOT.war.original
RUN ls -alt /target/SpringBootJpa-0.0.1-SNAPSHOT.war

ARG WAR_PATH /target/SpringBootJpa-0.0.1-SNAPSHOT.war
RUN cp ${WAR_PATH} /app.war

EXPOSE 8080

CMD ["java", "-jar", "/app.war"]
