FROM openjdk:8-jdk-alpine
EXPOSE 8085
ADD target/docker-emailapi.jar app.jar
ENTRYPOINT [ "java", "-jar" , "/app.jar" ]