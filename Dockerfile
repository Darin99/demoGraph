FROM openjdk:11.0.10-jre-slim
RUN mkdir -p /app
RUN mvn clean install
COPY target/*.jar /app/demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/demo-0.0.1-SNAPSHOT.jar"]