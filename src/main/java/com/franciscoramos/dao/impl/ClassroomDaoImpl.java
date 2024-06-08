package com.franciscoramos.dao.impl;

import com.franciscoramos.dao.ClassroomDao;
import com.franciscoramos.model.Classroom;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ClassroomDaoImpl implements ClassroomDao
{
    private final LinkedHashMap<Integer, Classroom> classroomsMap = new LinkedHashMap<>(16);

    public Classroom create(Integer key, Classroom value) {
        return null;
    }

    public Classroom update(Integer key, Classroom value) {
        return null;
    }

    public Classroom remove(Integer key) {
        return null;
    }

    public Classroom read(Integer key) {
        return null;
    }

    public List<Classroom> readAll() {
        return new ArrayList<>(classroomsMap.values());
    }
}
