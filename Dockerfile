FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["nohup","java", "-jar", "app.jar", "--spring.profiles.active=prod" , "&"]