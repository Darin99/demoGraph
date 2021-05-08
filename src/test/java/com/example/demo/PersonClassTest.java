package com.example.demo;

import com.example.demo.dataModel.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
public class PersonClassTest {

    private Person person;

    private static final String NAME = "Darin";
    private static final String WRONG_NAME = "Tom";

    private static final String POSITION = "Employee";
    private static final String WRONG_POSITION = "Manager";

    private static final String COMPANY = "Sap labs Bulgaria";
    private static final String WRONG_COMPANY = "MentorMate";

    private static final String TEAM = "CLM ilm Sof-dev";
    private static final String WRONG_TEAM = "MTE";

    private static final List<String> PERSONS = new ArrayList<>();
    private static final List<String> WRONG_PERSONS = Arrays.asList("Simeon", "George");

    @Before
    public void buildPerson() {
        person = new Person().builder().name(NAME).position(POSITION)
                .company(COMPANY).team(TEAM).persons(PERSONS).build();
    }

    @Test
    public void whenValidNameIsPassed_thenItShouldBeReturned() {
        Assert.assertEquals(NAME, person.getName());
    }

    @Test(expected = ComparisonFailure.class)
    public void whenInvalidNameIsPassed_thenExpectComparisonFailureError() {
        Assert.assertEquals(WRONG_NAME, person.getName());
    }

    @Test
    public void whenValidNameIsPassed_thenItShouldNotBeNull() {
        Assert.assertNotNull(person.getName());
    }

    @Test
    public void whenValidPositionIsPassed_thenItShouldBeReturned() {
        Assert.assertEquals(POSITION, person.getPosition());
    }

    @Test(expected = ComparisonFailure.class)
    public void whenInvalidPositionIsPassed_thenExpectComparisonFailureError() {
        Assert.assertEquals(WRONG_POSITION, person.getPosition());
    }

    @Test
    public void whenValidPositionIsPassed_thenItShouldNotBeNull() {
        Assert.assertNotNull(person.getPosition());
    }

    @Test
    public void whenValidCompanyIsPassed_thenItShouldBeReturned() {
        Assert.assertEquals(COMPANY, person.getCompany());
    }

    @Test(expected = ComparisonFailure.class)
    public void whenInvalidCompanyIsPassed_thenExpectComparisonFailureError() {
        Assert.assertEquals(WRONG_COMPANY, person.getCompany());
    }

    @Test
    public void whenValidCompanyIsPassed_thenItShouldNotBeNull() {
        Assert.assertNotNull(person.getCompany());
    }

    @Test
    public void whenValidTeamIsPassed_thenItShouldBeReturned() {
        Assert.assertEquals(TEAM, person.getTeam());
    }

    @Test(expected = ComparisonFailure.class)
    public void whenInvalidTeamIsPassed_thenExpectComparisonFailureError() {
        Assert.assertEquals(WRONG_TEAM, person.getTeam());
    }

    @Test
    public void whenValidTeamIsPassed_thenItShouldNotBeNull() {
        Assert.assertNotNull(person.getTeam());
    }

    @Test
    public void whenValidPersonsArePassed_thenTheyShouldBeReturned() {
        Assert.assertEquals(PERSONS, person.getPersons());
    }

    @Test(expected = AssertionError.class)
    public void whenInvalidPersonsArePassed_thenExpectAssertionError() {
        Assert.assertEquals(WRONG_PERSONS, person.getPersons());
    }

    @Test
    public void whenValidPersonsArePassed_thenTheyShouldNotBeNull() {
        Assert.assertNotNull(person.getPersons());
    }
}