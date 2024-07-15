package com.franciscoramos.service;

import com.franciscoramos.dao.RegistryDao;
import com.franciscoramos.exception.*;
import com.franciscoramos.model.Classroom;
import com.franciscoramos.model.Discipline;
import com.franciscoramos.model.Registry;
import com.franciscoramos.util.DaoFactory;

import java.util.HashSet;
import java.util.List;


public class RegistryService
{
    private final RegistryDao registryDao = DaoFactory.getDao(RegistryDao.class);

    public Registry create(Registry registry, List<Classroom> classrooms)
    {
        registry.setId(registryDao.getCounter());
        if(!classrooms.contains(registry.getClassroom())){
            throw new InvalidClassroomEnrollException("Incrição não pode ser realizada pois a turma não atende aos parâmetros inseridos.\n");
        }
        List<Discipline> preReqDisciplines = registry.getClassroom().getDiscipline().getPreRequisites();
        List<Discipline> completedDisciplines = registry.getStudent().getRegisteredClasses().values().stream().filter((reg) -> reg.getGrade() >= 60).filter(Registry::getEnoughPresence).map(Registry::getClassroom).map(Classroom::getDiscipline).toList();

        if(registry.getStudent().getRegisteredClasses().values().stream().filter((reg) -> reg.getGrade() != -1).map(Registry::getClassroom).map(Classroom::getDiscipline).toList().contains(registry.getClassroom().getDiscipline())) {
            throw new InvalidClassroomEnrollException("Inscrição não pode ser ralizada pois o aluno já completou ou está cursando esta disciplina\n");
        }

        if(!new HashSet<>(completedDisciplines).containsAll(preReqDisciplines)){
            throw new InvalidClassroomEnrollException("Incrição não pode ser realizada pois aluno não possuí todos os pré-requisitos.\n");
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

    public Registry updateGrade(int id, int newGrade, boolean presence)
    {
        if(newGrade < 0 || newGrade > 100) throw new InvalidGradeException("Nota atribuida nao esta no intervalo valido [0, 100]\n");
        Registry registry = read(id);
        registry.setGrade(newGrade);
        registry.setEnoughPresence(presence);
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
