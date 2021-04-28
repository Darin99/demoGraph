package com.example.demo.services.dataProccessServices;

import com.example.demo.dataModel.Person;
import com.example.demo.services.dataProccessServices.interfaces.DataReader;
import com.example.demo.services.dataProccessServices.interfaces.PersonMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvPersonMapper implements PersonMapper {

    private File file;

    private DataReader<File> dataReader;

    public CsvPersonMapper(File file) {
        this.file = file;
        this.dataReader = new FileReader();
    }

    @Override
    public List<Person> mapPersons() {

        List<Person> persons = new ArrayList<>();

        try {
            new CSVParser(this.dataReader.read(file), CSVFormat.DEFAULT.withHeader("name", "position", "companyName", "teamName", "persons")
                    .withFirstRecordAsHeader().withTrim()).getRecords()
                    .forEach(r -> persons.add(new Person().builder().name(r.get("name")).position(r.get("position")).company(r.get("companyName"))
                            .team(r.get("teamName")).persons(getPersons(r)).build()));
        } catch (IOException ignored) {
        }
        return persons;
    }

    private List<String> getPersons(CSVRecord record) {
        return Arrays.stream(record.get("persons").split(",")).filter(p -> !p.equalsIgnoreCase("")).collect(Collectors.toList());
    }
}