package com.franciscoramos.dao.impl;

import com.franciscoramos.dao.ClassroomDao;
import com.franciscoramos.model.Classroom;
import com.franciscoramos.model.Registry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ClassroomDaoImpl implements ClassroomDao
{
    protected Map<Integer, Classroom> classroomsMap = new LinkedHashMap<>(16);
    private int counter;

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

    public Map<Integer, Classroom> getMap() {
        return this.classroomsMap;
    }

    public void setMap(Map<Integer, Classroom> map) {
        this.classroomsMap = map;
    }

    public Integer getCounter() {
        return this.counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public List<Registry> getAllStudentsFromClassroom(int id) {
        return classroomsMap.get(id).getRegisteredStudents();
    }
}
