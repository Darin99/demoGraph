package com.example.demo;

import com.example.demo.services.dataProccessServices.FileReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
public class FileReaderClassTest {

    private FileReader fileReader;

    private static final File FILE = new File("/Users/I544901/IdeaProjects/demoGraph/src/main/resources/Employee0.json");

    @Before
    public void createConstructor() {
        fileReader = new FileReader();
    }

    @Test
    public void givenCorrectFileWhenExecutingMethodThenCorrect() throws IOException {
        String file = " {    \"name\": \"Jerry\",    \"position\": \"Employee\",    \"company\": \"DHL\",    \"team\": \"DevOps\",    \"persons\": []  }";
        Assert.assertEquals(file, fileReader.readJsonFile(FILE));
    }

    @Test(expected = AssertionError.class)
    public void givenWrongFileWhenExecutingMethodThenThrowsAssertionError() throws IOException {
        String file = " {    \"name\": \"Darin\",    \"position\": \"Employee\",    \"company\": \"Sap labs Bulgaria\",    \"team\": \"Clm ilm sof-dev\",    \"persons\": []  }";
        Assert.assertEquals(file, fileReader.readJsonFile(FILE));
    }
}