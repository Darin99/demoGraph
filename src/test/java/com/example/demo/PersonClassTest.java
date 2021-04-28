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
    private static final String POSITION = "Employee";
    private static final String COMPANY = "Sap labs Bulgaria";
    private static final String TEAM = "CLM ilm Sof-dev";
    private static final List<String> PERSONS = new ArrayList<>();

    @Before
    public void buildPerson() {
        person = new Person.PersonBuilder().name(NAME).position(POSITION)
                .company(COMPANY).team(TEAM).persons(PERSONS).build();
    }

    @Test
    public void givenCorrectNameWhenGettingItThenCorrect() {
        Assert.assertEquals(NAME, person.getName());
    }

    @Test(expected = ComparisonFailure.class)
    public void givenWrongNameWhenGettingItThenThrowsComparisonFailureError() {
        Assert.assertEquals("Tom", person.getName());
    }

    @Test(expected = AssertionError.class)
    public void givenNullNameWhenGettingItThenThrowsAssertionError() {
        Assert.assertNull(person.getName());
    }

    @Test
    public void givenCorrectPositionWhenGettingItThenCorrect() {
        Assert.assertEquals(POSITION, person.getPosition());
    }

    @Test(expected = ComparisonFailure.class)
    public void givenWrongPositionWhenGettingItThenThrowsComparisonFailureError() {
        Assert.assertEquals("Manager", person.getPosition());
    }

    @Test(expected = AssertionError.class)
    public void givenNullPositionWhenGettingItThenThrowsAssertionError() {
        Assert.assertNull(person.getPosition());
    }

    @Test
    public void givenCorrectCompanyWhenGettingItThenCorrect() {
        Assert.assertEquals(COMPANY, person.getCompany());
    }

    @Test(expected = ComparisonFailure.class)
    public void givenWrongCompanyWhenGettingItThenThrowsComparisonFailureError() {
        Assert.assertEquals("MentorMate", person.getCompany());
    }

    @Test(expected = AssertionError.class)
    public void givenNullCompanyWhenGettingItThenThrowsAssertionError() {
        Assert.assertNull(person.getCompany());
    }

    @Test
    public void givenCorrectTeamWhenGettingItThenCorrect() {
        Assert.assertEquals(TEAM, person.getTeam());
    }

    @Test(expected = ComparisonFailure.class)
    public void givenWrongTeamWhenGettingItThenThrowsComparisonFailureError() {
        Assert.assertEquals("MTE", person.getTeam());
    }

    @Test(expected = AssertionError.class)
    public void givenNullTeamWhenGettingItThenThrowsAssertionError() {
        Assert.assertNull(person.getTeam());
    }

    @Test
    public void givenCorrectPersonsWhenGettingItThenCorrect() {
        Assert.assertEquals(PERSONS, person.getPersons());
    }

    @Test(expected = AssertionError.class)
    public void givenWrongPersonsWhenGettingItThenThrowsComparisonFailureError() {
        Assert.assertEquals(Arrays.asList("Simeon", "George"), person.getPersons());
    }

    @Test(expected = AssertionError.class)
    public void givenNullPersonsWhenGettingItThenThrowsAssertionError() {
        Assert.assertNull(person.getPersons());
    }
}