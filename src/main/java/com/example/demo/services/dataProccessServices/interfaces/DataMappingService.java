package com.example.demo.services.dataProccessServices.interfaces;

import com.example.demo.dataModel.Person;

import java.io.IOException;
import java.util.List;

public interface DataMappingService {

    List<Person> mapData() throws IOException;
}