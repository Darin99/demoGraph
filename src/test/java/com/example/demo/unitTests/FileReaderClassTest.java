package com.example.demo.unitTests;

import com.example.demo.services.dataProccessServices.FileReader;
import com.example.demo.services.dataProccessServices.interfaces.DataReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileReaderClassTest {

    private DataReader<File> dataReader;

    private static final File JSON_FILE = new File("/Users/I544901/IdeaProjects/demoGraph/src/main/resources/testFile.json");
    private static final File CSV_FILE = new File("/Users/I544901/IdeaProjects/demoGraph/src/main/resources/testFile.csv");

    @BeforeEach
    public void createConstructor() {
        dataReader = new FileReader();
    }

    @Test
    public void whenValidJsonStringIsComparedWithJsonFile_thenFileShouldBeReaded() throws IOException {
        String jsonFile = " {    \"name\": \"Jerry\",    \"position\": \"Employee\",    \"company\": \"DHL\",    \"team\": \"DevOps\",    \"persons\": []  }";
        Assertions.assertEquals(jsonFile, parseFileToString(JSON_FILE));
    }

    @Test
    public void whenInvalidJsonStringIsComparedWithJsonFile_thenExpectAssertionError() {
        String jsonFile = " {    \"name\": \"Darin\",    \"position\": \"Employee\",    \"company\": \"Sap labs Bulgaria\",    \"team\": \"Clm ilm sof-dev\",    \"persons\": []  }";
        Assertions.assertThrows(AssertionError.class, () -> Assertions.assertEquals(jsonFile, parseFileToString(JSON_FILE)));
    }

    @Test
    public void whenValidCsvStringIsComparedWithCsvFile_thenFileShouldBeReaded() throws IOException {
        String csvFile = "name,position,companyName,teamName,personsKiril,Manager,SAP labs Bulgaria,HANA,\"Svetli,Ivan\"Hristina,HrRepresentative,SAP labs Bulgaria,hr,\"Tania,Denica\"Simeon,Employee,SAP labs Bulgaria,HANA,";
        Assertions.assertEquals(csvFile, parseFileToString(CSV_FILE));
    }

    @Test
    public void whenInvalidCsvStringIsComparedWithCsvFile_thenExpectAssertionError() {
        String csvFile = "name,position,companyName,teamName,personsDarin,Manager,DHL,HANA,\"Svetli,Ivan\"Veronika,HrRepresentative,MentorMate,HANA,\"Tania,Denica\"Simeon,Employee,SAP labs Bulgaria,HANA,";
        Assertions.assertThrows(AssertionError.class, () -> Assertions.assertEquals(csvFile, parseFileToString(CSV_FILE)));
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