package com.example.demo.unitTests;

import com.example.demo.services.queryServices.QueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueryBuilderClassTest {

    private QueryBuilder queryBuilder;

    private static final String EDGE = "worksIn";
    private static final String WRONG_EDGE = "isPartOf";

    private static final String EMPLOYEE_VERTEX = "Employee";
    private static final String MANAGER_VERTEX = "Manager";

    private static final String PROPERTY = "name";
    private static final String PROPERTY_VALUE = "Darin";
    private static final String WRONG_PROPERTY = "position";
    private static final String WRONG_PROPERTY_VALUE = "Tom";

    private static final String COMPANY_VERTEX = "Company";
    private static final String COMPANY_NAME = "Sap labs Bulgaria";

    @BeforeEach
    public void createConstructor() {
        queryBuilder = new QueryBuilder();
    }

    @Test
    public void whenValidDataIsPassed_thenValidEdgeQueryShouldBeReturned() {
        Assertions.assertEquals("CREATE EDGE worksIn UPSERT FROM (SELECT FROM Employee WHERE name = 'Darin') TO (SELECT FROM Company WHERE name = 'Sap labs Bulgaria')",
                queryBuilder.withCreateClause().appendEdgeWord().appendEdge(EDGE).withUpsertClause().withFromClause().appendOpenBracket()
                        .withSelectClause().withFromClause().appendVertex(EMPLOYEE_VERTEX).withWhereClause().appendPropAndValue(PROPERTY, PROPERTY_VALUE)
                        .appendCloseBracket().appendToWord().appendOpenBracket().withSelectClause().withFromClause().appendVertex(COMPANY_VERTEX)
                        .withWhereClause().appendPropAndValue(PROPERTY, COMPANY_NAME).appendCloseBracket().build());
    }

    @Test
    public void whenInvalidDataIsPassedToEdgeQuery_thenExpectAssertionError() {
        Assertions.assertThrows(AssertionError.class, () -> Assertions.assertEquals("CREATE EDGE worksIn UPSERT FROM (SELECT FROM Employee WHERE name = 'Darin') TO (SELECT FROM Company WHERE name = 'Sap labs Bulgaria')",
                queryBuilder.withCreateClause().appendEdgeWord().appendEdge(WRONG_EDGE).withUpsertClause().withFromClause().appendOpenBracket()
                        .withSelectClause().withFromClause().appendVertex(MANAGER_VERTEX).withWhereClause().appendPropAndValue(WRONG_PROPERTY, WRONG_PROPERTY_VALUE)
                        .appendCloseBracket().appendToWord().appendOpenBracket().withSelectClause().withFromClause().appendVertex(COMPANY_VERTEX)
                        .withWhereClause().appendPropAndValue(WRONG_PROPERTY, WRONG_PROPERTY_VALUE).appendCloseBracket().build()));
    }

    @Test
    public void whenValidDataIsPassed_thenValidVertexQueryShouldBeReturned() {
        Assertions.assertEquals("UPDATE Employee SET name = 'Darin' UPSERT WHERE name = 'Darin'",
                queryBuilder.withUpdateClause().appendVertex(EMPLOYEE_VERTEX).withSetClause().appendPropAndValue(PROPERTY, PROPERTY_VALUE)
                        .withUpsertClause().withWhereClause().appendPropAndValue(PROPERTY, PROPERTY_VALUE).build());
    }

    @Test
    public void whenInvalidDataIsPassedToVertexQuery_thenExpectAssertionError() {
        Assertions.assertThrows(AssertionError.class, () -> Assertions.assertEquals("UPDATE Employee SET name = 'Darin' UPSERT WHERE name = 'Darin'",
                queryBuilder.withUpdateClause().appendVertex(MANAGER_VERTEX).withSetClause().appendPropAndValue(WRONG_PROPERTY, WRONG_PROPERTY_VALUE)
                        .withUpsertClause().withWhereClause().appendPropAndValue(WRONG_PROPERTY, WRONG_PROPERTY_VALUE).build()));
    }
}