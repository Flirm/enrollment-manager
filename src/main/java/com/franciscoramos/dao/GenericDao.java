package com.franciscoramos.dao;

import java.util.List;

public interface GenericDao<K, V>
{
    V create(K key, V value);
    V update(K key, V value);
    V remove(K key);
    V read(K key);
    List<V> readAll();
}
