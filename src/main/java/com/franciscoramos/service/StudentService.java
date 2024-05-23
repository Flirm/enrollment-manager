package com.franciscoramos.service;

import com.franciscoramos.dao.StudentDao;
import com.franciscoramos.util.DaoFactory;

public class StudentService
{
    private final StudentDao studentDao = DaoFactory.getDao(StudentDao.class);
}
