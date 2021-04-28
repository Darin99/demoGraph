package com.example.demo.services.dataProccessServices;

import com.example.demo.services.dataProccessServices.interfaces.PersonMapper;
import com.example.demo.services.dataProccessServices.interfaces.PersonMapperFactory;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class PersonMapperFactoryImpl implements PersonMapperFactory {

    @Override
    public PersonMapper getPersonMapper(File file) {

        if (file.getName().endsWith(".csv")) {
            return new CsvPersonMapper(file);
        } else if (file.getName().endsWith(".json")) {
            return new JsonPersonMapper(file);
        }
        return null;
    }
}