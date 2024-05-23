package com.franciscoramos.dao;

public interface GenericDao<K, V>
{
    V create(K key, V value);
    V update(K key, V value);
    V delete(K key);
    V get(K key);
}
