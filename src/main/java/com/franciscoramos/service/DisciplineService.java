package com.franciscoramos.service;

import com.franciscoramos.dao.DisciplineDao;
import com.franciscoramos.util.DaoFactory;

public class DisciplineService
{
    private final DisciplineDao disciplineDao = DaoFactory.getDao(DisciplineDao.class);
}
