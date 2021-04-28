package com.example.demo.services.dataProccessServices;

import com.example.demo.services.dataProccessServices.interfaces.DataReader;

import java.io.*;

public class FileReader implements DataReader<File> {

    @Override
    public Reader read(File file) throws IOException {
        return new BufferedReader(new java.io.FileReader(file));
    }

    @Override
    public String readJsonFile(File file) throws IOException {
        StringBuilder builder = new StringBuilder();

        String line;

        BufferedReader reader = (BufferedReader) read(file);

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }
}