FROM openjdk:17-jdk-alpine
CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} app.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.war"]
