package com.franciscoramos.service;

import com.franciscoramos.dao.DisciplineDao;
import com.franciscoramos.exception.DisciplineIsPreRequisiteException;
import com.franciscoramos.exception.DisciplineWithClassroomsException;
import com.franciscoramos.exception.EntityNotFoundException;
import com.franciscoramos.model.Discipline;
import com.franciscoramos.util.DaoFactory;

import java.util.List;

public class DisciplineService
{
    private final DisciplineDao disciplineDao = DaoFactory.getDao(DisciplineDao.class);

    public Discipline create(Discipline discipline)
    {
        discipline.setId(disciplineDao.getCounter());
        return disciplineDao.create(discipline.getId(), discipline);
    }

    public Discipline update(int id, int workLoad)
    {
        Discipline discipline = read(id);
        if(discipline == null) throw new EntityNotFoundException("Disciplina inexistente.\n");
        discipline.setWorkLoad(workLoad);
        return disciplineDao.update(id, discipline);
    }

    public Discipline remove(int id)
    {
        Discipline discipline = read(id);

        List<Discipline> disciplines = disciplineDao.readAll().stream().filter((d) -> d.getPreRequisites().contains(discipline)).toList();
        if(!disciplines.isEmpty()) throw new DisciplineIsPreRequisiteException("Esta disciplina é pre-requisito de outrem e não pode ser removida");

        if(discipline.getClassrooms() == null || discipline.getClassrooms().isEmpty())
            disciplineDao.remove(id);
        else
            throw new DisciplineWithClassroomsException("Esta disciplina possuí turmas e nao pode ser removida");

        return discipline;
    }

    public Discipline read(int id)
    {
        Discipline discipline = disciplineDao.read(id);
        if(discipline == null) throw new EntityNotFoundException("Disciplina inexistente.\n");
        return discipline;
    }

    public List<Discipline> readAll()
    {
        return disciplineDao.readAll();
    }
}
