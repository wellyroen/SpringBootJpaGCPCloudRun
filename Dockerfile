FROM openjdk:17-jdk-alpine
VOLUME /tmp
ENV SPRING_PROFILES_ACTIVE docker
CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE=target/*.war
COPY ./target/*.war app.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.war"]
