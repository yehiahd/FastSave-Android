package com.appizona.yehiahd.fastsaveexample;

public class Person {

    private int id;
    private String name;
    private boolean isEmployee;

    public int getId() {
        return id;
    }

    public Person setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public Person setEmployee(boolean employee) {
        isEmployee = employee;
        return this;
    }

    @Override
    public String toString() {
        return "id = " + id + " name = " + name + " isEmployee = " + isEmployee;
    }
}
