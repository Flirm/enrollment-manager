package com.franciscoramos.dao;

import java.util.List;
import java.util.Map;

public interface GenericDao<K, V>
{
    V create(K key, V value);
    V update(K key, V value);
    V remove(K key);
    V read(K key);
    List<V> readAll();
    Map<Integer, V> getMap();
    void setMap(Map<Integer, V> map);
    Integer getCounter();
    void setCounter(Integer counter);
}
