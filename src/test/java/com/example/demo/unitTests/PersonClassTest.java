package com.example.demo.unitTests;

import com.example.demo.dataModel.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @BeforeEach
    public void buildPerson() {
        person = new Person().builder().name(NAME).position(POSITION)
                .company(COMPANY).team(TEAM).persons(PERSONS).build();
    }

    @Test
    public void whenValidNameIsPassed_thenItShouldBeReturned() {
        Assertions.assertEquals(NAME, person.getName());
    }

    @Test
    public void whenInvalidNameIsPassed_thenExpectAssertionError() {
        Assertions.assertThrows(AssertionError.class, () -> Assertions.assertEquals(WRONG_NAME, person.getName()));
    }

    @Test
    public void whenValidNameIsPassed_thenItShouldNotBeNull() {
        Assertions.assertNotNull(person.getName());
    }

    @Test
    public void whenValidPositionIsPassed_thenItShouldBeReturned() {
        Assertions.assertEquals(POSITION, person.getPosition());
    }

    @Test
    public void whenInvalidPositionIsPassed_thenExpectAssertionError() {
        Assertions.assertThrows(AssertionError.class, () -> Assertions.assertEquals(WRONG_POSITION, person.getPosition()));
    }

    @Test
    public void whenValidPositionIsPassed_thenItShouldNotBeNull() {
        Assertions.assertNotNull(person.getPosition());
    }

    @Test
    public void whenValidCompanyIsPassed_thenItShouldBeReturned() {
        Assertions.assertEquals(COMPANY, person.getCompany());
    }

    @Test
    public void whenInvalidCompanyIsPassed_thenExpectAssertionError() {
        Assertions.assertThrows(AssertionError.class, () -> Assertions.assertEquals(WRONG_COMPANY, person.getCompany()));
    }

    @Test
    public void whenValidCompanyIsPassed_thenItShouldNotBeNull() {
        Assertions.assertNotNull(person.getCompany());
    }

    @Test
    public void whenValidTeamIsPassed_thenItShouldBeReturned() {
        Assertions.assertEquals(TEAM, person.getTeam());
    }

    @Test
    public void whenInvalidTeamIsPassed_thenExpectAssertionError() {
        Assertions.assertThrows(AssertionError.class, () -> Assertions.assertEquals(WRONG_TEAM, person.getTeam()));
    }

    @Test
    public void whenValidTeamIsPassed_thenItShouldNotBeNull() {
        Assertions.assertNotNull(person.getTeam());
    }

    @Test
    public void whenValidPersonsArePassed_thenTheyShouldBeReturned() {
        Assertions.assertEquals(PERSONS, person.getPersons());
    }

    @Test
    public void whenInvalidPersonsArePassed_thenExpectAssertionError() {
        Assertions.assertThrows(AssertionError.class, () -> Assertions.assertEquals(WRONG_PERSONS, person.getPersons()));
    }

    @Test
    public void whenValidPersonsArePassed_thenTheyShouldNotBeNull() {
        Assertions.assertNotNull(person.getPersons());
    }
}