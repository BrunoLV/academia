FROM openjdk:8
WORKDIR /app
COPY ./target/alunos.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "alunos.jar"]