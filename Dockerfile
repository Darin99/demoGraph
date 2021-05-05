FROM adoptopenjdk:11-jre-hotspot
RUN mkdir -p /app
COPY target/*.jar /app/demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/demo-0.0.1-SNAPSHOT.jar"]