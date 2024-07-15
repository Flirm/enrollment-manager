package com.franciscoramos.model;


import java.io.Serializable;

public class Registry implements Serializable
{
    private int grade;
    private String date;
    private String schoolTerm;
    private Student student;
    private Classroom classroom;
    private int id;
    private boolean enoughPresence;

    public Registry(Student student, Classroom classroom, String schoolTerm)
    {
        this.student = student;
        this.classroom = classroom;
        this.date = java.time.LocalDate.now().toString();
        this.grade = -1;
        this.schoolTerm = schoolTerm;
        this.enoughPresence = true;
    }

    public String toString() {
        String s = "Numero de Inscricao: " + this.id + "\nDisciplina: " + this.classroom.getName() + "\nData da Inscricao: " + this.date
                + "\nPeríodo: " + this.schoolTerm + "\n";
        if(this.grade != -1)
            s += "Nota: " + this.grade + "\n" + "Presença: " + (this.enoughPresence ? "Suficiente" : "Insuficiente" + "\n");
        return s;
    }

    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}

    public int getGrade() {return this.grade;}
    public void setGrade(int grade) {this.grade = grade;}

    public String getDate() {return this.date;}
    public void setDate(String date) {this.date = date;}

    public Student getStudent() {return this.student;}
    public void setStudent(Student student) {this.student = student;}

    public Classroom getClassroom() {return this.classroom;}
    public void setClassroom(Classroom classroom) {this.classroom = classroom;}

    public String getSchoolTerm() {return this.schoolTerm;}
    public void setSchoolTerm(String schoolTerm) {this.schoolTerm = schoolTerm;}

    public boolean getEnoughPresence() {return this.enoughPresence;}
    public void setEnoughPresence(boolean enoughPresence) {this.enoughPresence = enoughPresence;}
}
