FROM openjdk:17-jdk-alpine
VOLUME /tmp
ENV SPRING_PROFILES_ACTIVE docker
CMD ["mvn", "clean", "package"]
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} app.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.war"]
