package com.example.demo.services.dataProccessServices.interfaces;

import java.io.IOException;
import java.io.Reader;

public interface DataReader<T> {

    Reader read(T dataSource) throws IOException;
}