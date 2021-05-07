package com.example.demo;

import com.example.demo.services.queryServices.QueryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
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

    @Before
    public void createConstructor() {
        queryBuilder = new QueryBuilder();
    }

    @Test
    public void givenCorrectValuesWhenBuildingQueryThenCorrec() {
        Assert.assertEquals("CREATE EDGE worksIn UPSERT FROM (SELECT FROM Employee WHERE name = 'Darin') TO (SELECT FROM Company WHERE name = 'Sap labs Bulgaria')",
                queryBuilder.withCreateClause().appendEdgeWord().appendEdge(EDGE).withUpsertClause().withFromClause().appendOpenBracket()
                        .withSelectClause().withFromClause().appendVertex(EMPLOYEE_VERTEX).withWhereClause().appendPropAndValue(PROPERTY, PROPERTY_VALUE)
                        .appendCloseBracket().appendToWord().appendOpenBracket().withSelectClause().withFromClause().appendVertex(COMPANY_VERTEX)
                        .withWhereClause().appendPropAndValue(PROPERTY, COMPANY_NAME).appendCloseBracket().build());
    }

    @Test(expected = AssertionError.class)
    public void givenCorrectValuesWhenBuildingQueryThenThrowsAssertionError() {
        Assert.assertEquals("CREATE EDGE worksIn UPSERT FROM (SELECT FROM Employee WHERE name = 'Darin') TO (SELECT FROM Company WHERE name = 'Sap labs Bulgaria')",
                queryBuilder.withCreateClause().appendEdgeWord().appendEdge(WRONG_EDGE).withUpsertClause().withFromClause().appendOpenBracket()
                        .withSelectClause().withFromClause().appendVertex(MANAGER_VERTEX).withWhereClause().appendPropAndValue(WRONG_PROPERTY, WRONG_PROPERTY_VALUE)
                        .appendCloseBracket().appendToWord().appendOpenBracket().withSelectClause().withFromClause().appendVertex(COMPANY_VERTEX)
                        .withWhereClause().appendPropAndValue(WRONG_PROPERTY, WRONG_PROPERTY_VALUE).appendCloseBracket().build());
    }

    @Test
    public void givenCorrectValuesWhenBuildingQueryThenCorrect() {
        Assert.assertEquals("UPDATE Employee SET name = 'Darin' UPSERT WHERE name = 'Darin'",
                queryBuilder.withUpdateClause().appendVertex(EMPLOYEE_VERTEX).withSetClause().appendPropAndValue(PROPERTY, PROPERTY_VALUE)
                        .withUpsertClause().withWhereClause().appendPropAndValue(PROPERTY, PROPERTY_VALUE).build());
    }

    @Test(expected = AssertionError.class)
    public void givenCorrectValuesWhenBuildingQueryThrowsAssertionError() {
        Assert.assertEquals("UPDATE Employee SET name = 'Darin' UPSERT WHERE name = 'Darin'",
                queryBuilder.withUpdateClause().appendVertex(MANAGER_VERTEX).withSetClause().appendPropAndValue(WRONG_PROPERTY, WRONG_PROPERTY_VALUE)
                        .withUpsertClause().withWhereClause().appendPropAndValue(WRONG_PROPERTY, WRONG_PROPERTY_VALUE).build());
    }
}