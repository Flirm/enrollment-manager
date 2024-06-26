package com.franciscoramos;

import com.franciscoramos.exception.EntityNotFoundException;
import com.franciscoramos.exception.TeacherWithClassroomsException;
import com.franciscoramos.model.Classroom;
import com.franciscoramos.model.Teacher;
import com.franciscoramos.service.ClassroomService;
import com.franciscoramos.service.TeacherService;
import corejava.Console;

import java.util.List;

public class TeacherMain
{
    public static final TeacherService teacherService = new TeacherService();
    public static final ClassroomService classroomService = new ClassroomService();

    public void run()
    {
        String name;
        String email;
        Teacher teacher;

        boolean loop = true;
        while(loop)
        {
            System.out.println("=======================================================\n");
            System.out.println("O que deseja fazer?\n");
            System.out.println("1. Cadastrar Professor na instituíção\n");
            System.out.println("2. Descadastrar Professor da instituíção\n");
            System.out.println("3. Alocar professor em uma Turma\n");
            System.out.println("4. Listar turmas de um Professor\n");
            System.out.println("5. Listar todos os Professores\n");
            System.out.println("6. Voltar\n");
            int result = Console.readInt("Digite um número entre 1 e 6: ");

            switch(result)
            {
                case 1 ->{ //cadastrar
                    name = Console.readLine("Informe o nome do professor: ");
                    String[] names = name.split(" ");
                    email = Character.toLowerCase(name.charAt(0)) + names[names.length - 1].toLowerCase() + "@id.prof.uff.br";
                    teacher = new Teacher(name, email);
                    teacherService.create(teacher);
                    System.out.println("\nProfessor " + name + " cadastrado com sucesso!");
                    System.out.println("Numero de inscrição: " + teacher.getId());
                    System.out.println("Email: " + email + "\n");
                }
                case 2 ->{ //descadastrar
                    int id = Console.readInt("Informe o numero de inscrição do professor: ");
                    try
                    {
                        teacherService.remove(id);
                        System.out.println("Professor " + id + " removido com sucesso!\n");
                    }catch(EntityNotFoundException | TeacherWithClassroomsException e){
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 3 ->{ //Alocar professor em uma Turma
                    int id = Console.readInt("Informe o ID do professor: ");
                    try
                    {
                        teacher = teacherService.read(id);
                        id = Console.readInt("Informe o ID da turma: ");
                        Classroom classroom = classroomService.read(id);
                        classroom.getTeacher().getClassrooms().remove(classroom);
                        classroom.setTeacher(teacher);
                        teacher.getClassrooms().add(classroom);
                        System.out.println("Professor " + teacher.getName() + " alocado na turma " + classroom.getName() + " com sucesso!\n");
                    }catch(EntityNotFoundException e){
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 4 ->{ // listando turmas de um professor
                    int id = Console.readInt("Informe o ID do professor: ");
                    try
                    {
                        teacher = teacherService.read(id);
                        System.out.println("Turmas lecionadas por " + teacher.getName() + ":\n");
                        for(Classroom c : teacher.getClassrooms())
                        {
                            System.out.println(c);
                        }
                    }catch(EntityNotFoundException e){
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 5 ->{ //listando profs
                    List<Teacher> teachers = teacherService.readAll();
                    System.out.println("Professores cadastrados no sistema:\n");
                    for(Teacher t : teachers)
                        System.out.println(t);
                }
                case 6 -> loop = false;
                default -> System.out.println("Opção Inválida\n");
            }
        }

    }
}
