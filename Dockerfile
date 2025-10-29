FROM eclipse-temurin:21-jre-alpine
WORKDIR /app


ENV TZ=Europe/Berlin


COPY target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]