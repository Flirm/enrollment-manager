package com.franciscoramos.service;

import com.franciscoramos.dao.ClassroomDao;
import com.franciscoramos.exception.ClassroomNotEmptyException;
import com.franciscoramos.exception.EntityNotFoundException;
import com.franciscoramos.model.Classroom;
import com.franciscoramos.model.Registry;
import com.franciscoramos.util.DaoFactory;

import java.util.List;

public class ClassroomService
{
    private final ClassroomDao classroomDao = DaoFactory.getDao(ClassroomDao.class);

    public Classroom create(Classroom classroom)
    {
        classroom.setId(classroomDao.getCounter());
        return classroomDao.create(classroom.getId(), classroom);
    }

    public Classroom remove(int id)
    {
        Classroom classroom = read(id);

        if(classroom.getTeacher() == null && (classroom.getRegisteredStudents() == null || classroom.getRegisteredStudents().isEmpty()))
            classroomDao.remove(id);
        else throw new ClassroomNotEmptyException("Turma possui alunos e/ou professores associados e nao pode ser removida");

        return classroom;
    }

    public Classroom read(int id)
    {
        Classroom classroom = classroomDao.read(id);
        if(classroom == null) throw new EntityNotFoundException("Turma inexistente.\n");
        return classroom;
    }

    public List<Classroom> readAll()
    {
        return classroomDao.readAll();
    }

    public List<Registry> readAllStudents(int id)
    {
        return classroomDao.getAllStudentsFromClassroom(id);
    }
}
