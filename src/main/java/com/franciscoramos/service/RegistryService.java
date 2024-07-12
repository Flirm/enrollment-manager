package com.franciscoramos.service;

import com.franciscoramos.dao.RegistryDao;
import com.franciscoramos.exception.*;
import com.franciscoramos.model.Classroom;
import com.franciscoramos.model.Registry;
import com.franciscoramos.util.DaoFactory;

import java.util.List;
import java.util.Objects;


public class RegistryService
{
    private final RegistryDao registryDao = DaoFactory.getDao(RegistryDao.class);

    public Registry create(Registry registry, List<Classroom> classrooms)
    {
        if(!classrooms.contains(registry.getClassroom())){
            throw new InvalidClassroomEnrollException("Incrição não pode ser realizada pois a turma não atende aos parâmetros inseridos.\n");
        }
        registryDao.create(registry.getId(), registry);
        return registry;
    }

    public Registry remove(int id)
    {
        Registry registry = read(id);
        if(registry.getGrade() != -1)
            throw new DisciplineAlreadyCompleteException("Disciplina da inscricao ja foi concluida e nao pode ser removida.\n");
        registryDao.remove(id);
        return registry;
    }

    public Registry updateGrade(int id, int newGrade)
    {
        if(newGrade < 0 || newGrade > 100) throw new InvalidGradeException("Nota atribuida nao esta no intervalo valido [0, 100]\n");
        Registry registry = read(id);
        registry.setGrade(newGrade);
        return registryDao.update(id, registry);
    }


    public Registry read(int id)
    {
        Registry registry = registryDao.read(id);
        if(registry == null)
            throw new EntityNotFoundException("Inscricao Inexistente.\n");
        return registry;
    }

    public List<Registry> readAll()
    {
        return registryDao.readAll();
    }
}
