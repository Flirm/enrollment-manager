package com.franciscoramos.model;


public class Registry
{
    private int grade;
    private String date;
    private Student student;
    private Classroom classroom;

    public Registry(Student student, Classroom classroom)
    {
        this.student = student;
        this.classroom = classroom;
        this.date = java.time.LocalDate.now().toString();
        this.grade = -1;
    }

    public int getGrade() {return this.grade;}
    public void setGrade(int grade) {this.grade = grade;}

    public String getDate() {return this.date;}
    public void setDate(String date) {this.date = date;}

    public Student getStudent() {return this.student;}
    public void setStudent(Student student) {this.student = student;}

    public Classroom getClassroom() {return this.classroom;}
    public void setClassroom(Classroom classroom) {this.classroom = classroom;}
}
