package com.franciscoramos.model;

import java.util.LinkedHashMap;

public class Student
{
    private static int count = 0;
    private int id;
    private String name;
    private String email;
    private final LinkedHashMap<Integer, Registry> registeredClasses;

    public Student(String name, String email)
    {
        this.id = count++;
        this.name = name;
        this.email = email;
        this.registeredClasses = new LinkedHashMap<>();
    }

    public String toString()
    {
        return "Nome: " + this.name + "\nEmail: " + this.email + "\nMatrícula: " + this.id + "\n";
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public LinkedHashMap<Integer ,Registry> getRegisteredClasses() {return registeredClasses;}
}
