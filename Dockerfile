FROM openjdk:17-jdk-alpine

CMD ["./mvnw", "clean", "package"]

ARG JAR_FILE_PATH=target/*.war

COPY ${JAR_FILE} springbootjpaclone.war

EXPOSE 8280

ENTRYPOINT ["java","-jar","/springbootjpaclone.jar"]
