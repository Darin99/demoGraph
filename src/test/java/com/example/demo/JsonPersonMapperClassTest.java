package com.example.demo;

import com.example.demo.services.dataProccessServices.JsonPersonMapper;
import com.example.demo.services.dataProccessServices.interfaces.PersonMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
public class JsonPersonMapperClassTest {

    private static final File JSON_FILE = new File("/Users/I544901/IdeaProjects/demoGraph/src/main/resources/testFile.json");

    @Test
    public void whenValidFileIsPassed_thenFileShouldBeParsed() {
        PersonMapper personMapper = new JsonPersonMapper(JSON_FILE);
        Assert.assertEquals(1, personMapper.mapPersons().size());
    }
}