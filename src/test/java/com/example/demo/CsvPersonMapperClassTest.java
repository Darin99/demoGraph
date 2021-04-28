package com.example.demo;

import com.example.demo.dataModel.Person;
import com.example.demo.services.dataProccessServices.CsvPersonMapper;
import com.example.demo.services.dataProccessServices.interfaces.PersonMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
public class CsvPersonMapperClassTest {

    private PersonMapper personMapper;

    private static final File FILE = new File("/Users/I544901/IdeaProjects/demoGraph/src/main/resources/employees.csv");

    @Before
    public void buildConstructor() {
        personMapper = new CsvPersonMapper(FILE);
    }

    @Test
    public void givenCorrectJsonWhenExecutingMethodThenCorrect() {
        List<Person> persons = personMapper.mapPersons();
        Assert.assertEquals(3, persons.size());
    }

    @Test(expected = AssertionError.class)
    public void givenWrongJsonWhenExecutingMethodThenThrowsAssertionError() {
        List<Person> persons = personMapper.mapPersons();
        Assert.assertEquals(3, persons.size());
    }
}