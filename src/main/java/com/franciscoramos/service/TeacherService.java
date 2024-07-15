package com.franciscoramos.service;

import com.franciscoramos.dao.TeacherDao;
import com.franciscoramos.exception.EntityNotFoundException;
import com.franciscoramos.exception.TeacherWithClassroomsException;
import com.franciscoramos.model.Teacher;
import com.franciscoramos.util.DaoFactory;

import java.util.List;

public class TeacherService
{
    public final TeacherDao teacherDao = DaoFactory.getDao(TeacherDao.class);

    public Teacher create(Teacher teacher)
    {
        teacher.setId(teacherDao.getCounter());
        return teacherDao.create(teacher.getId(), teacher);
    }

    public Teacher remove(int id)
    {
        Teacher teacher = read(id);
        if(teacher.getClassrooms() == null || teacher.getClassrooms().isEmpty())
            teacherDao.remove(id);
        else
            throw new TeacherWithClassroomsException("Professor esta inscrito em turmas e nao pode ser removido");
        return teacher;
    }

    public Teacher read(int id)
    {
        Teacher teacher = teacherDao.read(id);
        if(teacher == null) throw new EntityNotFoundException("Professor Inexistente.\n");
        return teacher;
    }

    public List<Teacher> readAll()
    {
        return teacherDao.readAll();
    }
}
