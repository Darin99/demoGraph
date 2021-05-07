package com.example.demo;

import com.example.demo.services.dataProccessServices.FileReader;
import com.example.demo.services.dataProccessServices.interfaces.DataReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
public class FileReaderClassTest {

    private DataReader<File> dataReader;

    private static final File JSON_FILE = new File("/Users/I544901/IdeaProjects/demoGraph/src/main/resources/testFile.json");
    private static final File CSV_FILE = new File("/Users/I544901/IdeaProjects/demoGraph/src/main/resources/testFile.csv");

    @Before
    public void createConstructor() {
        dataReader = new FileReader();
    }

    @Test
    public void whenValidJsonStringIsComparedWithJsonFile_thenFileShouldBeReaded() throws IOException {
        String jsonFile = " {    \"name\": \"Jerry\",    \"position\": \"Employee\",    \"company\": \"DHL\",    \"team\": \"DevOps\",    \"persons\": []  }";
        Assert.assertEquals(jsonFile, parseFileToString(JSON_FILE));
    }

    @Test(expected = AssertionError.class)
    public void whenInvalidJsonStringIsComparedWithJsonFile_thenExpectAssertionError() throws IOException {
        String jsonFile = " {    \"name\": \"Darin\",    \"position\": \"Employee\",    \"company\": \"Sap labs Bulgaria\",    \"team\": \"Clm ilm sof-dev\",    \"persons\": []  }";
        Assert.assertEquals(jsonFile, parseFileToString(JSON_FILE));
    }

    @Test
    public void whenValidCsvStringIsComparedWithCsvFile_thenFileShouldBeReaded() throws IOException {
        String csvFile = "name,position,companyName,teamName,personsKiril,Manager,SAP labs Bulgaria,HANA,\"Svetli,Ivan\"Hristina,HrRepresentative,SAP labs Bulgaria,hr,\"Tania,Denica\"Simeon,Employee,SAP labs Bulgaria,HANA,";
        Assert.assertEquals(csvFile, parseFileToString(CSV_FILE));
    }

    @Test(expected = AssertionError.class)
    public void whenInvalidCsvStringIsComparedWithCsvFile_thenExpectAssertionError() throws IOException {
        String csvFile = "name,position,companyName,teamName,personsDarin,Manager,DHL,HANA,\"Svetli,Ivan\"Veronika,HrRepresentative,MentorMate,HANA,\"Tania,Denica\"Simeon,Employee,SAP labs Bulgaria,HANA,";
        Assert.assertEquals(csvFile, parseFileToString(CSV_FILE));
    }

    private String parseFileToString(File file) throws IOException {
        StringBuilder builder = new StringBuilder();

        String line;

        BufferedReader reader = (BufferedReader) this.dataReader.read(file);

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }
}