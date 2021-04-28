package com.example.demo;

import com.example.demo.services.queryServices.UpdateQueryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UpdateQueryClassTest {

    private UpdateQueryBuilder updateQueryBuilder;

    private static final String CLASS_TYPE = "Employee";
    private static final String WRONG_CLASS_TYPE = "Manager";

    private static final String PROPERTY = "name";
    private static final String PROPERTY_VALUE = "Darin";
    private static final String WRONG_PROPERTY = "position";
    private static final String WRONG_PROPERTY_VALUE = "Tom";

    @Before
    public void createConstructor() {
        updateQueryBuilder = new UpdateQueryBuilder();
    }

    @Test
    public void givenCorrectClassWhenBuildingQueryWithUpdateClauseThenCorrect() {
        Assert.assertEquals("UPDATE " + CLASS_TYPE, updateQueryBuilder.withUpdateClause(CLASS_TYPE).build());
    }

    @Test(expected = AssertionError.class)
    public void givenWrongClassWhenBuildingQueryWithUpdateClauseThenThrowsAssertionError() {
        Assert.assertEquals("UPDATE " + WRONG_CLASS_TYPE, updateQueryBuilder.withUpdateClause(CLASS_TYPE).build());
    }

    @Test
    public void givenCorrectPropertyAndValueWhenBuildingQueryWithSetClauseThenCorrect() {
        Assert.assertEquals(" SET " + PROPERTY + " = '" + PROPERTY_VALUE + "'", updateQueryBuilder.withSetClause(PROPERTY, PROPERTY_VALUE).build());
    }

    @Test(expected = AssertionError.class)
    public void givenWrongPropertyAndValueWhenBuildingQueryWithSetClauseThenThrowsAssertionError() {
        Assert.assertEquals(" SET " + WRONG_PROPERTY + " = '" + WRONG_PROPERTY_VALUE + "'", updateQueryBuilder.withSetClause(PROPERTY, PROPERTY_VALUE).build());
    }

    @Test
    public void givenCorrectPropertyAndValueWhenBuildingQueryWithWhereClauseThenCorrect() {
        Assert.assertEquals(" UPSERT WHERE " + PROPERTY + " = '" + PROPERTY_VALUE + "'", updateQueryBuilder.withWhereClause(PROPERTY, PROPERTY_VALUE).build());
    }

    @Test(expected = AssertionError.class)
    public void givenWrongPropertyAndValueWhenBuildingQueryWithWhereClauseThenThrowsAssertionError() {
        Assert.assertEquals(" UPSERT WHERE " + WRONG_PROPERTY + " = '" + WRONG_PROPERTY_VALUE + "'", updateQueryBuilder.withWhereClause(PROPERTY, PROPERTY_VALUE).build());
    }

    @Test
    public void givenCorrectValuesWhenBuildingQueryThenCorrect() {
        Assert.assertEquals("UPDATE Employee SET name = 'Darin' UPSERT WHERE name = 'Darin'", updateQueryBuilder.withUpdateClause(CLASS_TYPE)
                .withSetClause(PROPERTY, PROPERTY_VALUE).withWhereClause(PROPERTY, PROPERTY_VALUE).build());
    }
}