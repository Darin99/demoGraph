package com.example.demo.services.queryServices;

import com.example.demo.dataModel.Person;
import com.example.demo.services.queryServices.interfaces.QueryCreator;
import com.example.demo.services.queryServices.interfaces.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryCreatorImpl implements QueryCreator {

    private final QueryExecutor queryExecutor;

    @Autowired
    public QueryCreatorImpl(QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    @Override
    public void createQueries(Person person) {

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

    private void createVertex(String vertex, String valueOfProp) {
        String query = new QueryBuilder().withUpdateClause().appendVertex(vertex).withSetClause().appendPropAndValue("name", valueOfProp)
                .withUpsertClause().withWhereClause().appendPropAndValue("name", valueOfProp).build();
        this.queryExecutor.executeQuery(query);
    }

    private void createEdge(String edge, String vertex, String valueOfProp, String secondVertex, String secondValueOfProp) {
        String query = new QueryBuilder().withCreateClause().appendEdgeWord().appendEdge(edge).withUpsertClause().withFromClause()
                .appendOpenBracket().withSelectClause().withFromClause().appendVertex(vertex).withWhereClause().appendPropAndValue("name", valueOfProp)
                .appendCloseBracket().appendToWord().appendOpenBracket().withSelectClause().withFromClause().appendVertex(secondVertex)
                .withWhereClause().appendPropAndValue("name", secondValueOfProp).appendCloseBracket().build();
        this.queryExecutor.executeQuery(query);
    }
}