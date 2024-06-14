package com.franciscoramos.model;

import java.util.ArrayList;

public class Discipline
{
    private static int count = 0;
    private int id;
    private int workLoad;
    private String name;
    private ArrayList<Classroom> classrooms;

    public Discipline(String name, int workLoad)
    {
        this.id = count++;
        this.workLoad = workLoad;
        this.name = name;
    }

    public String toString()
    {
        return "Nome: " + this.name + "\nCarga Horária: " + this.workLoad + "\nId: " + this.id + "\n";
    }

    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}

    public int getWorkLoad() {return this.workLoad;}
    public void setWorkLoad(int workLoad) {this.workLoad = workLoad;}

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public ArrayList<Classroom> getClassrooms() {return this.classrooms;}
}
