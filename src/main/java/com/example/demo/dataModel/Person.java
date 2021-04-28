package com.example.demo.dataModel;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Person {

    @Expose
    private String name;

    @Expose
    private String position;

    @Expose
    private String company;

    @Expose
    private String team;

    @Expose
    private List<String> persons;

    public Person() {
    }

    private Person(PersonBuilder builder) {
        this.name = builder.name;
        this.position = builder.position;
        this.company = builder.company;
        this.team = builder.team;
        this.persons = builder.persons;
    }

    public static class PersonBuilder {
        private String name;
        private String position;
        private String company;
        private String team;
        private List<String> persons;

        public PersonBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder position(String position) {
            this.position = position;
            return this;
        }

        public PersonBuilder company(String company) {
            this.company = company;
            return this;
        }

        public PersonBuilder team(String team) {
            this.team = team;
            return this;
        }

        public PersonBuilder persons(List<String> persons) {
            this.persons = persons;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    public PersonBuilder builder() {
        return new PersonBuilder();
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getCompany() {
        return company;
    }

    public String getTeam() {
        return team;
    }

    public List<String> getPersons() {
        return persons;
    }
}