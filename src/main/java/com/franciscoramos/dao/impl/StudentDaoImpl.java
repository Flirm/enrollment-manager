package com.franciscoramos.dao.impl;

import com.franciscoramos.dao.StudentDao;
import com.franciscoramos.model.Student;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StudentDaoImpl implements StudentDao
{
    protected Map<Integer, Student> studentsMap = new LinkedHashMap<>(16);
    private int counter;

    public Student create(Integer key, Student value) {
        return studentsMap.put(key, value);
    }

    public Student update(Integer key, Student value) {
        return studentsMap.put(key, value);
    }

    public Student remove(Integer key) {
        return studentsMap.remove(key);
    }

    public Student read(Integer key) {
        return studentsMap.get(key);
    }

    public List<Student> readAll() {
        return new ArrayList<>(studentsMap.values());
    }

    public Map<Integer, Student> getMap() {
        return this.studentsMap;
    }

    public void setMap(Map<Integer, Student> map) {
        this.studentsMap = map;
    }

    public Integer getCounter() {
        return this.counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

}
