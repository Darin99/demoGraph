package com.example.demo.unitTests;

import com.example.demo.services.dataProccessServices.JsonPersonMapper;
import com.example.demo.services.dataProccessServices.interfaces.PersonMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class JsonPersonMapperClassTest {

    private static final File JSON_FILE = new File("/Users/I544901/IdeaProjects/demoGraph/src/main/resources/testFile.json");

    @Test
    public void whenValidFileIsPassed_thenFileShouldBeParsed() {
        PersonMapper personMapper = new JsonPersonMapper(JSON_FILE);
        Assertions.assertEquals(1, personMapper.mapPersons().size());
    }
}