#
# Build 
#
FROM maven:3.6.3-jdk-8-openj9 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Server 
#
FROM openjdk:8
WORKDIR /app
COPY --from=build /home/app/target/buscacep.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "buscacep.jar"]