FROM openjdk:17

WORKDIR /app

EXPOSE 5000

COPY /target/microframework-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]