package com.franciscoramos.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Classroom implements Serializable
{
    private static int count = 0;
    private int id;
    private String schoolTerm;
    private Discipline discipline;
    private Teacher teacher;
    private final ArrayList<Registry> registeredStudents;

    public Classroom(Discipline discipline, String schoolTerm, Teacher teacher)
    {
        this.id = count++;
        this.schoolTerm = schoolTerm;
        this.discipline = discipline;
        this.teacher = teacher;
        this.registeredStudents = new ArrayList<>();
    }

    public String toString()
    {
        return "Nome: " + this.discipline.getName() + "\nProfessor: "+ this.teacher.getName() + "\nPeríodo: " + this.schoolTerm + "\nId: " + this.id + "\n";
    }

    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return this.discipline.getName();}
    public void setName(String name) {this.discipline.setName(name);}

    public String getSchoolTerm() {return this.schoolTerm;}
    public void setSchoolTerm(String schoolTerm) {this.schoolTerm = schoolTerm;}

    public Discipline getDiscipline() {return this.discipline;}
    public void setDiscipline(Discipline discipline) {this.discipline = discipline;}

    public Teacher getTeacher() {return this.teacher;}
    public void setTeacher(Teacher teacher) {this.teacher = teacher;}

    public ArrayList<Registry> getRegisteredStudents() {return this.registeredStudents;}

    public int getStudentCount() {return this.registeredStudents.size();}

    public int getApprovedStudentCount() {return this.registeredStudents.stream().filter(Registry::getEnoughPresence).filter((r) -> r.getGrade() >= 60).toList().size();}
}
