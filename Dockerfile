FROM openjdk:17-jdk-alpine

CMD ["./mvnw", "clean", "package"]

ARG JAR_FILE_PATH=target/*.war

COPY ${JAR_FILE_PATH} springbootjpaclone.war

ENV PORT=${PORT:-8080}
EXPOSE 8080

ENTRYPOINT ["java","-jar","/springbootjpaclone.jar"]
