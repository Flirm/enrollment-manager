package com.franciscoramos.dao.impl;

import com.franciscoramos.dao.TeacherDao;
import com.franciscoramos.model.Teacher;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao
{
    private final LinkedHashMap<Integer, Teacher> teacherMap = new LinkedHashMap<>(16);

    public Teacher create(Integer key, Teacher value) {
        return teacherMap.put(key, value);
    }

    public Teacher update(Integer key, Teacher value) {
        return teacherMap.put(key, value);
    }

    public Teacher remove(Integer key) {
        return teacherMap.remove(key);
    }

    public Teacher read(Integer key) {
        return teacherMap.get(key);
    }

    public List<Teacher> readAll() {
        return new ArrayList<>(teacherMap.values());
    }
}
