package com.example.demo.services.dataProccessServices;

import com.example.demo.services.dataProccessServices.interfaces.DataReader;

import java.io.*;

public class FileReader implements DataReader<File> {

    @Override
    public Reader read(File file) throws IOException {
        return new BufferedReader(new java.io.FileReader(file));
    }
}