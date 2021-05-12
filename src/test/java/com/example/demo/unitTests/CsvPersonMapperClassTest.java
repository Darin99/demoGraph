package com.example.demo.unitTests;

import com.example.demo.services.dataProccessServices.CsvPersonMapper;
import com.example.demo.services.dataProccessServices.interfaces.PersonMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class CsvPersonMapperClassTest {

    private static final File CSV_FILE = new File("/Users/I544901/IdeaProjects/demoGraph/src/main/resources/testFile.csv");

    @Test
    public void whenValidFileIsPassed_thenFileShouldBeParsed() {
        PersonMapper personMapper = new CsvPersonMapper(CSV_FILE);
        Assertions.assertEquals(3, personMapper.mapPersons().size());
    }
}