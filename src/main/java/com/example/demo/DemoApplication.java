package com.example.demo;

import com.example.demo.services.dataProccessServices.interfaces.DataMappingService;
import com.example.demo.services.queryServices.interfaces.QueryCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final DataMappingService dataMappingService;
    private final QueryCreator queryCreator;

    @Autowired
    public DemoApplication(DataMappingService dataMappingService, QueryCreator queryCreator) {
        this.dataMappingService = dataMappingService;
        this.queryCreator = queryCreator;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws IOException {

        ExecutorService executorService = Executors.newFixedThreadPool(50);

        this.dataMappingService.mapData().forEach(person -> executorService.execute(() -> this.queryCreator.createQueries(person)));

        executorService.shutdown();
    }
}