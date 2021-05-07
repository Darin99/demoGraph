package com.example.demo;

import com.example.demo.services.dataProccessServices.CsvPersonMapper;
import com.example.demo.services.dataProccessServices.interfaces.PersonMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
public class CsvPersonMapperClassTest {

    private static final File CSV_FILE = new File("/Users/I544901/IdeaProjects/demoGraph/src/main/resources/testFile.csv");

    @Test
    public void whenValidFileIsPassed_thenFileShouldBeParsed() {
        PersonMapper personMapper = new CsvPersonMapper(CSV_FILE);
        Assert.assertEquals(3, personMapper.mapPersons().size());
    }
}