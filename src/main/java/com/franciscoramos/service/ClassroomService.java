package com.franciscoramos.service;

import com.franciscoramos.dao.ClassroomDao;
import com.franciscoramos.util.DaoFactory;

public class ClassroomService
{
    private final ClassroomDao classroomDao = DaoFactory.getDao(ClassroomDao.class);
}
