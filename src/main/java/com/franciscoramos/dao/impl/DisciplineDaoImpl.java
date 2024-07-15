package com.franciscoramos.dao.impl;

import com.franciscoramos.dao.DisciplineDao;
import com.franciscoramos.model.Discipline;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DisciplineDaoImpl implements DisciplineDao
{
    protected Map<Integer, Discipline> disciplinesMap = new LinkedHashMap<>(16);
    private int counter = 0;

    public Discipline create(Integer key, Discipline value) {
        this.setCounter(key + 1);
        return disciplinesMap.put(key, value);
    }

    public Discipline update(Integer key, Discipline value) {
        return disciplinesMap.put(key, value);
    }

    public Discipline remove(Integer key) {
        return disciplinesMap.remove(key);
    }

    public Discipline read(Integer key) {
        return disciplinesMap.get(key);
    }

    public List<Discipline> readAll() {
        return new ArrayList<>(disciplinesMap.values());
    }

    public Map<Integer, Discipline> getMap() {
        return this.disciplinesMap;
    }

    public void setMap(Map<Integer, Discipline> map) {
        this.disciplinesMap = map;
    }

    public Integer getCounter() {
        return this.counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
