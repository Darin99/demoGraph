package com.example.demo.services.dataProccessServices;

import com.example.demo.dataModel.Person;
import com.example.demo.services.dataProccessServices.interfaces.DataReader;
import com.example.demo.services.dataProccessServices.interfaces.PersonMapper;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonPersonMapper implements PersonMapper {

    private File file;

    private DataReader<File> dataReader;

    private Gson gson;

    public JsonPersonMapper(File file) {
        this.file = file;
        this.dataReader = new FileReader();
        this.gson = new Gson();
    }

    @Override
    public List<Person> mapPersons() {
        List<Person> persons = new ArrayList<>();
        try {
            String json = this.dataReader.readJsonFile(file);
            persons.add(gson.fromJson(json, Person.class));
        } catch (Exception ignored) {
        }
        return persons;
    }
}