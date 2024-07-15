package com.franciscoramos.dao.impl;

import com.franciscoramos.dao.RegistryDao;
import com.franciscoramos.model.Registry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RegistryDaoImpl implements RegistryDao
{
    protected Map<Integer, Registry> registriesMap = new LinkedHashMap<>(16);
    private int counter;

    public Registry create(Integer key, Registry value) {
        return registriesMap.put(key, value);
    }

    public Registry update(Integer key, Registry value) {
        return registriesMap.put(key, value);
    }

    public Registry remove(Integer key) {
        return registriesMap.remove(key);
    }

    public Registry read(Integer key) {
        return registriesMap.get(key);
    }

    public List<Registry> readAll() {
        return new ArrayList<>(registriesMap.values());
    }

    public Map<Integer, Registry> getMap() {
        return this.registriesMap;
    }

    public void setMap(Map<Integer, Registry> map) {
        this.registriesMap = map;
    }

    public Integer getCounter() {
        return this.counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
