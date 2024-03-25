FROM openjdk:17-jdk-alpine

CMD ["./mvnw", "clean", "package"]

ARG WAR_FILE=target/*.war

COPY ${WAR_FILE} springbootjpaclone.war

EXPOSE 8080

ENTRYPOINT ["java","-jar","/springbootjpaclone.jar"]
