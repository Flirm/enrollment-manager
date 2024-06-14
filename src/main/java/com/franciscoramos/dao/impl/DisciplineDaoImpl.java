package com.franciscoramos.dao.impl;

import com.franciscoramos.dao.DisciplineDao;
import com.franciscoramos.model.Discipline;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DisciplineDaoImpl implements DisciplineDao
{
    private final LinkedHashMap<Integer, Discipline> disciplinesMap = new LinkedHashMap<>(16);

    public Discipline create(Integer key, Discipline value) {
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
}
