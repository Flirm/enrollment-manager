package com.franciscoramos;

import com.franciscoramos.dao.*;
import com.franciscoramos.model.*;
import com.franciscoramos.util.DaoFactory;
import corejava.Console;

import java.io.*;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        StudentMain studentMain = new StudentMain();
        DisciplineMain disciplineMain = new DisciplineMain();
        ClassroomMain classroomMain = new ClassroomMain();
        TeacherMain teacherMain = new TeacherMain();

        recoverData();

        boolean looping = true;
        while(looping)
        {
            System.out.println("=======================================================\n");
            System.out.println("O que deseja fazer?\n");
            System.out.println("1. Tratar Alunos\n");
            System.out.println("2. Tratar Disciplinas\n");
            System.out.println("3. Tratar Turmas\n");
            System.out.println("4. Tratar Professores\n");
            System.out.println("5. Sair\n");
            int result = Console.readInt("Digite um número entre 1 e 5: ");
            System.out.println();

            switch (result)
            {
                case 1 -> studentMain.run();
                case 2 -> disciplineMain.run();
                case 3 -> classroomMain.run();
                case 4 -> teacherMain.run();
                case 5 -> {
                    looping = false;
                    saveData();
                }
                default -> System.out.println("Opção Inválida\n");
            }
        }
    }

    public static void recoverData()
    {
        try {
            StudentDao studentDao = DaoFactory.getDao(StudentDao.class);
            RegistryDao registryDao = DaoFactory.getDao(RegistryDao.class);
            ClassroomDao classroomDao = DaoFactory.getDao(ClassroomDao.class);
            TeacherDao teacherDao = DaoFactory.getDao(TeacherDao.class);
            DisciplineDao disciplineDao = DaoFactory.getDao(DisciplineDao.class);

            FileInputStream fis = new FileInputStream(new File("myObjects.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            Map<Integer, Student> studentMap = (Map<Integer, Student>) ois.readObject();
            studentDao.setMap(studentMap);
            Integer studentCounter = (Integer) ois.readObject();
            studentDao.setCounter(studentCounter);

            Map<Integer, Registry> registryMap = (Map<Integer, Registry>) ois.readObject();
            registryDao.setMap(registryMap);
            Integer registryCounter = (Integer) ois.readObject();
            registryDao.setCounter(registryCounter);

            Map<Integer, Classroom> classroomMap = (Map<Integer, Classroom>) ois.readObject();
            classroomDao.setMap(classroomMap);
            Integer classroomCounter = (Integer) ois.readObject();
            classroomDao.setCounter(classroomCounter);

            Map<Integer, Teacher> teacherMap = (Map<Integer, Teacher>) ois.readObject();
            teacherDao.setMap(teacherMap);
            Integer teacherCounter = (Integer) ois.readObject();
            teacherDao.setCounter(teacherCounter);

            Map<Integer, Discipline> disciplineMap = (Map<Integer, Discipline>) ois.readObject();
            disciplineDao.setMap(disciplineMap);
            Integer disciplineCounter = (Integer) ois.readObject();
            disciplineDao.setCounter(disciplineCounter);
        }catch(FileNotFoundException e){
            System.out.println("Arquivo myObjects.txt foi criado.");
        }catch(IOException e){
            throw new RuntimeException(e);
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public static void saveData()
    {
        StudentDao studentDao = DaoFactory.getDao(StudentDao.class);
        Map<Integer, Student> studentMap = studentDao.getMap();
        Integer studentCounter = studentDao.getCounter();

        RegistryDao registryDao = DaoFactory.getDao(RegistryDao.class);
        Map<Integer, Registry> registryMap = registryDao.getMap();
        Integer registryCounter = registryDao.getCounter();

        ClassroomDao classroomDao = DaoFactory.getDao(ClassroomDao.class);
        Map<Integer, Classroom> classroomMap = classroomDao.getMap();
        Integer classroomCounter = classroomDao.getCounter();

        TeacherDao teacherDao = DaoFactory.getDao(TeacherDao.class);
        Map<Integer, Teacher> teacherMap = teacherDao.getMap();
        Integer teacherCounter = teacherDao.getCounter();

        DisciplineDao disciplineDao = DaoFactory.getDao(DisciplineDao.class);
        Map<Integer, Discipline> disciplineMap = disciplineDao.getMap();
        Integer disciplineCounter = disciplineDao.getCounter();

        try{
            FileOutputStream fos = new FileOutputStream(new File("myObjects.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(studentMap);
            oos.writeObject(studentCounter);

            oos.writeObject(registryMap);
            oos.writeObject(registryCounter);

            oos.writeObject(classroomMap);
            oos.writeObject(classroomCounter);

            oos.writeObject(teacherMap);
            oos.writeObject(teacherCounter);

            oos.writeObject(disciplineMap);
            oos.writeObject(disciplineCounter);
        }catch(IOException e){
            throw new RuntimeException(e);
        }


    }
}
