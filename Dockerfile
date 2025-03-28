# Builder stage
FROM openjdk:17-jdk-alpine as builder
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

# Final stage
FROM openjdk:17-jdk-alpine
WORKDIR /application
COPY --from=builder /application/application.jar /application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]
EXPOSE 8080
