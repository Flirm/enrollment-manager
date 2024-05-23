package com.franciscoramos.util;

import org.reflections.Reflections;


import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DaoFactory
{
    private static final Map<Class<?>, Object> map = new HashMap<>();

    public static <T> T getDao(Class<T> type)
    {
        Object obj = map.get(type);
        if(obj == null)
        {
            Reflections reflections = new Reflections("com.franciscoramos.dao.impl");
            Set<Class<? extends T>> classes = reflections.getSubTypesOf(type);
            if(classes.size() != 1)
            {
                throw new RuntimeException("Deve haver uma e apenas uma classe que implementa a interface " + type.getName());
            }
            Class<? extends T> clazz = classes.iterator().next();
            try
            {
                obj = clazz.getDeclaredConstructor().newInstance();
                map.put(type, obj);
            } catch(InstantiationException |
                    IllegalAccessException |
                    InvocationTargetException |
                    NoSuchMethodException e){
                throw new RuntimeException(e);
            }
        }
        return (T) obj;
    }
}
