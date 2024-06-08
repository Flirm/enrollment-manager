package com.franciscoramos.dao.impl;

import com.franciscoramos.dao.StudentDao;
import com.franciscoramos.model.Student;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class StudentDaoImpl implements StudentDao
{
    private final LinkedHashMap<Integer, Student> studentsMap = new LinkedHashMap<>(16);

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

}
