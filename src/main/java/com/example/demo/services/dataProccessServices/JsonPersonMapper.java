package com.example.demo.services.dataProccessServices;

import com.example.demo.dataModel.Person;
import com.example.demo.services.dataProccessServices.interfaces.DataReader;
import com.example.demo.services.dataProccessServices.interfaces.PersonMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonPersonMapper implements PersonMapper {

    private File file;

    private DataReader<File> dataReader;

    private Gson gson;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonPersonMapper.class);

    public JsonPersonMapper(File file) {
        this.file = file;
        this.dataReader = new FileReader();
        this.gson = new Gson();
    }

    @Override
    public List<Person> mapPersons() {
        List<Person> persons = new ArrayList<>();
        try {
            String json = parseFileToString(file);
            persons.add(gson.fromJson(json, Person.class));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return persons;
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