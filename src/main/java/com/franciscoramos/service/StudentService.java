package com.franciscoramos.service;

import com.franciscoramos.dao.StudentDao;
import com.franciscoramos.exception.EntityNotFoundException;
import com.franciscoramos.exception.StudentEnrolledException;
import com.franciscoramos.model.Student;
import com.franciscoramos.util.DaoFactory;

import java.util.List;

public class StudentService
{
    private final StudentDao studentDao = DaoFactory.getDao(StudentDao.class);

    public Student create(Student student)
    {
        studentDao.create(student.getId(), student);
        return student;
    }

    public Student remove(int id)
    {
        Student student = read(id);

        if(student == null)
            throw new EntityNotFoundException("Aluno inexistente.\n");

        if(student.getRegisteredClasses().isEmpty())
            studentDao.remove(id);
        else
            throw new StudentEnrolledException("Este aluno esta matriculado em disciplinas e nao pode ser removido");

        return student;
    }

    public Student read(int id)
    {
        Student student = studentDao.read(id);
        if(student == null)
            throw new EntityNotFoundException("Aluno inexistente.\n");
        return student;
    }

    public List<Student> readAll()
    {
        List<Student> students = studentDao.readAll();
        return students;
    }
}
