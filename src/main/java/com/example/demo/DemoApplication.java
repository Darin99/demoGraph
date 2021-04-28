package com.example.demo;

import com.example.demo.dataModel.Person;
import com.example.demo.services.dataProccessServices.interfaces.DataMappingService;
import com.example.demo.services.queryServices.CreateEdgeQueryBuilder;
import com.example.demo.services.queryServices.UpdateQueryBuilder;
import com.example.demo.services.queryServices.interfaces.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final DataMappingService dataMappingService;
    private final QueryExecutor queryExecutor;

    @Autowired
    public DemoApplication(DataMappingService dataMappingService, QueryExecutor queryExecutor) {
        this.dataMappingService = dataMappingService;
        this.queryExecutor = queryExecutor;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws IOException {

        ExecutorService executorService = Executors.newFixedThreadPool(50);

        this.dataMappingService.mapData().forEach(person -> executorService.execute(() -> executeQueries(person)));

        executorService.shutdown();
    }

    private void executeQueries(Person person) {

        createVertex(person.getPosition(), person.getName());
        createVertex("Company", person.getCompany());
        createVertex("Team", person.getTeam());

        createEdge("worksIn", person.getPosition(), person.getName(), "Company", person.getCompany());
        createEdge("isPartOf", person.getPosition(), person.getName(), "Team", person.getTeam());
        createEdge("isIn", "Team", person.getTeam(), "Company", person.getCompany());

        person.getPersons().forEach(employee -> {

            createVertex("Employee", employee);
            createEdge("worksIn", "Employee", employee, "Company", person.getCompany());
            createEdge("isPartOf", "Employee", employee, "Team", person.getTeam());

            if (person.getPosition().equalsIgnoreCase("manager")) {
                createEdge("isManagedBy", "Employee", employee, person.getPosition(), person.getName());
            } else {
                createEdge("isRepresentedBy", "Employee", employee, person.getPosition(), person.getName());
            }
        });
    }

    private void createVertex(String classType, String classTypeProp) {
        String query = new UpdateQueryBuilder().withUpdateClause(classType).withSetClause("name", classTypeProp)
                .withWhereClause("name", classTypeProp).build();
        queryExecutor.executeQuery(query);
    }

    private void createEdge(String edge, String classType, String classTypeProp, String secondClassType, String secondClassTypeProp) {
        String query = new CreateEdgeQueryBuilder().withCreateClause(edge).withUpsertClause().withFromClause().appendOpenBracket().withSelectClause(classType)
                .withWhereClause("name", classTypeProp).appendCloseBracket().appendToWord().appendOpenBracket().withSelectClause(secondClassType)
                .withWhereClause("name", secondClassTypeProp).appendCloseBracket().build();
        queryExecutor.executeQuery(query);
    }
}