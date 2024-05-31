package com.franciscoramos.service;

import com.franciscoramos.dao.TeacherDao;
import com.franciscoramos.util.DaoFactory;

public class TeacherService
{
    public final TeacherDao teacherDao = DaoFactory.getDao(TeacherDao.class);
}
