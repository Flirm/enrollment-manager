package com.franciscoramos.model;

public class Classroom
{
    private static int count = 0;
    private int id;
    private String name;
    private String schoolTerm;
    private Discipline discipline;

    public Classroom(Discipline discipline, String schoolTerm)
    {
        this.id = Integer.parseInt(String.valueOf(count++) + String.valueOf(discipline.getId()) + schoolTerm);
        this.name = discipline.getName();
        this.schoolTerm = schoolTerm;
        this.discipline = discipline;
    }

    public String toString()
    {
        return "Nome: " + this.name + "\nPeríodo: " + this.schoolTerm + "\nId: " + this.id + "\n";
    }

    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public String getSchoolTerm() {return this.schoolTerm;}
    public void setSchoolTerm(String schoolTerm) {this.schoolTerm = schoolTerm;}

    public Discipline getDiscipline() {return this.discipline;}
    public void setDiscipline(Discipline discipline) {this.discipline = discipline;}
}
