package com.franciscoramos.model;

import java.util.ArrayList;

public class Student
{
    private static int count = 0;
    private int id;
    private String name;
    private String email;
    private ArrayList<Discipline> completedDisciplines;
    private ArrayList<Classroom> classrooms;

    public Student(String name, String email)
    {
        this.id = Integer.parseInt("0" + count++);
        this.name = name;
        this.email = email;
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
}
