FROM adoptopenjdk:11-jre-hotspot
RUN mkdir -p /app
RUN mvn clean install
COPY target/*.jar /app/demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/demo-0.0.1-SNAPSHOT.jar"]