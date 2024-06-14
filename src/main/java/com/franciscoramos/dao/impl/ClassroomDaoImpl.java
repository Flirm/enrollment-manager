package com.franciscoramos.dao.impl;

import com.franciscoramos.dao.ClassroomDao;
import com.franciscoramos.model.Classroom;
import com.franciscoramos.model.Registry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ClassroomDaoImpl implements ClassroomDao
{
    private final LinkedHashMap<Integer, Classroom> classroomsMap = new LinkedHashMap<>(16);

    public Classroom create(Integer key, Classroom value) {
        return classroomsMap.put(key, value);
    }

    public Classroom update(Integer key, Classroom value) {
        return classroomsMap.put(key, value);
    }

    public Classroom remove(Integer key) {
        return classroomsMap.remove(key);
    }

    public Classroom read(Integer key) {
        return classroomsMap.get(key);
    }

    public List<Classroom> readAll() {
        return new ArrayList<>(classroomsMap.values());
    }

    public List<Registry> getAllStudentsFromClassroom(int id) {
        return classroomsMap.get(id).getRegisteredStudents();
    }
}
