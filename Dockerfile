FROM maven:3.8.1-jdk-11
RUN mkdir -p /app
WORKDIR /app
COPY pom.xml /app
RUN mvn clean install
COPY target/demo-*.jar /app/demo.jar
ENTRYPOINT ["java","-jar","/app/demo.jar"]