package com.example.demo.services.dataProccessServices;

import com.example.demo.dataModel.Person;
import com.example.demo.services.dataProccessServices.interfaces.DataMappingService;
import com.example.demo.services.dataProccessServices.interfaces.PersonMapper;
import com.example.demo.services.dataProccessServices.interfaces.PersonMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DataMappingServiceImpl implements DataMappingService {

    private static final String PATH = "/Users/I544901/Desktop/files";

    private final PersonMapperFactory factory;

    @Autowired
    public DataMappingServiceImpl(PersonMapperFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Person> mapData() {
        return Arrays.stream(Objects.requireNonNull(new File(PATH).listFiles())).map(factory::getPersonMapper).filter(Objects::nonNull)
                .map(PersonMapper::mapPersons).flatMap(Collection::stream).collect(Collectors.toList());
    }
}