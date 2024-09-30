FROM eclipse-temurin:17.0.12_7-jre-ubi9-minimal
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]