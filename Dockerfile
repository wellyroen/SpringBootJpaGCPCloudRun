FROM openjdk:17-jdk-alpine

CMD ["./mvnw", "clean", "package"]

ARG JAR_FILE_PATH=./target/*.war

COPY ${JAR_FILE_PATH} app.war

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.war"]
