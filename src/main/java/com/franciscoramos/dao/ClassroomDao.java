package com.franciscoramos.dao;

import com.franciscoramos.model.Classroom;
import com.franciscoramos.model.Registry;

import java.util.List;

public interface ClassroomDao extends GenericDao<Integer, Classroom>
{
    public List<Registry> getAllStudentsFromClassroom(int id);
}
