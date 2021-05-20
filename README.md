# **HR System**

HR System is an ETL(Extract, Transform, Load) Spring application where we ingest information about HR's, Managers and
Employees in a graph oriented database.

## **Table of Contents**

* ### Description
* ### Technologies
* ### Prerequisites
* ### How to run the application
* ### Testing

## **Description**

Ingest information for HR system via an ETL (Extract, Transform, Load) Spring Application.

* EXTRACT - The data will be incoming from CSV and JSON files where CSV's will contain multiple entries of people per
  file and JSON's will contain one person per file.
  <br>
  <br>
* TRANSFORM - Here we have to differentiate a Person entry by its position into its graph representation.
  <br>
  <br>
* LOAD - In this layer we need to ingest with queries the transformed data in the graph database.

## **Technologies**

* Java 11
* Spring Boot 2.4.4
* Apache Maven 3.8.1
* Orientdb 3.1.9
* TestContainers - Orientdb 1.15.3
* TestContainers - JUnit-Jupiter 1.15.3
* Google Gson 2.8.5
* Apache Commons CSV 1.8

## **Prerequisites**

To be able to execute the application you need these things installed and running on your machine:
<br>

* [Apache maven](https://maven.apache.org "download apache maven") 3.8.1
  <br>
* [Orientdb](https://orientdb.org, "download orientdb") 3.1.9

* [docker](https://www.docker.com/get-started "download docker") (optional for integration testing only)

## **How to run the application**

1. Clone the repository on your machine using this command:
   <br>
   <br>
   `$ git clone https://github.tools.sap/I544901/ETL-app-demo.git`
   <br>
   <br>
2. Open the downloaded folder(the project) in your IDE.
   <br>
   <br>

3. Open the directory where you downloaded the orientdb client, then open the `bin` folder and double-click
   on `server.sh` file if you are using linux based OS or `server.bat` if you are using windows.
   <br>
   <br>

4. Open terminal at the project folder and write one of these commands to generate a jar file :
   <br>
   <br>
   `$ mvn clean install`
   `$ mvn clean package`
   <br>
   <br>
5. To start the application write in terminal at the project folder:
   <br>
   <br>
   `$ java -jar target/demo-0.0.1-SNAPSHOT.jar`
   <br>
   <br>
6. To see the result go to `http://localhost:2480/studio/index.html#/`, choose `graphDbDemo`, write in 
   the user field - `root` and in the password field - `hello` and click connect.

## **Testing**

To test the application write in terminal at the project folder:

* `$ mvn clean install -P integration-test` - to run only integration tests
  <br>
  <br>
* `$ mvn clean install -P unit-test` - to run only unit tests  
