package com.example.demo;

import com.example.demo.services.queryServices.CreateEdgeQueryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CreateEdgeQueryClassTest {

    private CreateEdgeQueryBuilder createEdgeQueryBuilder;

    private static final String OPEN_BRACKET = "(";
    private static final String CLOSING_BRACKET = ")";
    private static final String TO_WORD = "TO";

    private static final String EDGE = "worksIn";
    private static final String WRONG_EDGE = "isPartOf";

    private static final String CLASS_TYPE = "Employee";
    private static final String WRONG_CLASS_TYPE = "Manager";

    private static final String PROPERTY = "name";
    private static final String PROPERTY_VALUE = "Darin";
    private static final String WRONG_PROPERTY = "position";
    private static final String WRONG_PROPERTY_VALUE = "Tom";

    private static final String COMPANY = "Company";
    private static final String COMPANY_NAME = "Sap labs Bulgaria";

    @Before
    public void createConstructor() {
        createEdgeQueryBuilder = new CreateEdgeQueryBuilder();
    }

    @Test
    public void givenCorrectEdgeWhenBuildingQueryWithCreateClauseThenCorrect() {
        Assert.assertEquals("CREATE EDGE " + EDGE + " ", createEdgeQueryBuilder.withCreateClause(EDGE).build());
    }

    @Test(expected = AssertionError.class)
    public void givenWrongEdgeWhenBuildingQueryWithCreateClauseThenThrowsAssertionError() {
        Assert.assertEquals("CREATE EDGE " + WRONG_EDGE + " ", createEdgeQueryBuilder.withCreateClause(EDGE).build());
    }

    @Test
    public void whenBuildingQueryWithUpsertClauseThenCorrect() {
        Assert.assertEquals("UPSERT FROM ", createEdgeQueryBuilder.withUpsertClause().build());
    }

    public void whenBuildingQueryWithUpsertClauseThenThrowsAssertionError() {
        Assert.assertEquals("SELECT FROM ", createEdgeQueryBuilder.withUpsertClause().build());
    }

    @Test
    public void givenCorrectOpenBracketWhenBuildingQueryAppendOpenBracketThenCorrect() {
        Assert.assertEquals(OPEN_BRACKET, createEdgeQueryBuilder.appendOpenBracket().build());
    }

    @Test(expected = AssertionError.class)
    public void givenWrongCloseBracketWhenBuildingQueryAppendOpenBracketThenThrowsAssertionError() {
        Assert.assertEquals(CLOSING_BRACKET, createEdgeQueryBuilder.appendOpenBracket().build());
    }

    @Test
    public void givenCorrectCloseBracketWhenBuildingQueryAppendCloseBracketThenCorrect() {
        Assert.assertEquals(CLOSING_BRACKET, createEdgeQueryBuilder.appendCloseBracket().build());
    }

    @Test(expected = AssertionError.class)
    public void givenWrongOpenBracketWhenBuildingQueryAppendCloseBracketThenThrowsAssertionError() {
        Assert.assertEquals(OPEN_BRACKET, createEdgeQueryBuilder.appendCloseBracket().build());
    }

    @Test
    public void whenBuildingQueryAppendToWordThenCorrect() {
        Assert.assertEquals(" " + TO_WORD + " ", createEdgeQueryBuilder.appendToWord().build());
    }

    @Test(expected = AssertionError.class)
    public void whenBuildingQueryAppendToWordThenThrowsAssertionError() {
        Assert.assertEquals(" " + OPEN_BRACKET + " ", createEdgeQueryBuilder.appendToWord().build());
    }

    @Test
    public void givenCorrectClassWhenBuildingQueryWithSelectClauseThenCorrect() {
        Assert.assertEquals("SELECT FROM " + CLASS_TYPE + " ", createEdgeQueryBuilder.withSelectClause(CLASS_TYPE).build());
    }

    @Test(expected = AssertionError.class)
    public void givenWrongClassWhenBuildingQueryWithSelectClauseThenThrowsAssertionError() {
        Assert.assertEquals("SELECT FROM " + WRONG_CLASS_TYPE + " ", createEdgeQueryBuilder.withSelectClause(CLASS_TYPE).build());
    }

    @Test
    public void givenCorrectPropertyAndValueWhenBuildingQueryWithWhereClauseThenCorrect() {
        Assert.assertEquals("WHERE " + PROPERTY + " = '" + PROPERTY_VALUE + "'",
                createEdgeQueryBuilder.withWhereClause(PROPERTY, PROPERTY_VALUE).build());
    }

    @Test(expected = AssertionError.class)
    public void givenWrongPropertyAndValueWhenBuildingQueryWithWhereClauseThenThrowsAssertionError() {
        Assert.assertEquals("WHERE " + WRONG_PROPERTY + " = '" + WRONG_PROPERTY_VALUE + "'",
                createEdgeQueryBuilder.withWhereClause(PROPERTY, PROPERTY_VALUE).build());
    }

    @Test
    public void givenCorrectValuesWhenBuildingQueryThenCorrect() {
        Assert.assertEquals("CREATE EDGE worksIn UPSERT FROM (SELECT FROM Employee WHERE name = 'Darin') TO (SELECT FROM Company WHERE name = 'Sap labs Bulgaria')",
                createEdgeQueryBuilder.withCreateClause(EDGE).withUpsertClause()
                        .appendOpenBracket().withSelectClause(CLASS_TYPE).withWhereClause(PROPERTY, PROPERTY_VALUE).appendCloseBracket()
                        .appendToWord()
                        .appendOpenBracket().withSelectClause(COMPANY).withWhereClause(PROPERTY, COMPANY_NAME).appendCloseBracket()
                        .build());
    }
}