package com.franciscoramos.dao.impl;

import com.franciscoramos.dao.RegistryDao;
import com.franciscoramos.model.Registry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RegistryDaoImpl implements RegistryDao
{
    private final LinkedHashMap<Integer, Registry> registriesMap = new LinkedHashMap<>(16);

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
}
