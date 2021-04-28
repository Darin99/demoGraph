package com.example.demo.services.dataProccessServices.interfaces;

import java.io.File;

public interface PersonMapperFactory {

    PersonMapper getPersonMapper(File file);
}