package com.codecool.web.model;

public class Employee extends User {

    private final String lastName;
    private final String firstName;

    public Employee(String id, String lastName, String firstName) {
        super(id);
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
