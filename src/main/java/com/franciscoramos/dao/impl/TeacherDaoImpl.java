package com.franciscoramos.dao.impl;

import com.franciscoramos.dao.TeacherDao;
import com.franciscoramos.model.Teacher;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TeacherDaoImpl implements TeacherDao
{
    protected Map<Integer, Teacher> teacherMap = new LinkedHashMap<>(16);
    private int counter;

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

    public Map<Integer, Teacher> getMap() {
        return this.teacherMap;
    }

    public void setMap(Map<Integer, Teacher> map) {
        this.teacherMap = map;
    }

    public Integer getCounter() {
        return this.counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
