package com.franciscoramos;

import com.franciscoramos.exception.ClassroomNotEmptyException;
import com.franciscoramos.exception.EntityNotFoundException;
import com.franciscoramos.model.Classroom;
import com.franciscoramos.model.Discipline;
import com.franciscoramos.model.Registry;
import com.franciscoramos.model.Teacher;
import com.franciscoramos.service.ClassroomService;
import com.franciscoramos.service.DisciplineService;
import com.franciscoramos.service.TeacherService;
import corejava.Console;

import java.util.List;

public class ClassroomMain
{
    private final ClassroomService classroomService = new ClassroomService();
    private final DisciplineService disciplineService = new DisciplineService();
    private final TeacherService teacherService = new TeacherService();

    public void run()
    {

        Classroom classroom;

        boolean loop = true;
        while(loop)
        {
            System.out.println("=======================================================\n");
            System.out.println("O que deseja fazer?\n");
            System.out.println("1. Cadastrar turma\n");
            System.out.println("2. Remover turma\n");
            System.out.println("3. Listar todas as turmas\n");
            System.out.println("4. Listar todas as turmas de uma disciplina\n");
            System.out.println("5. Alterar turma\n");
            System.out.println("6. Listar dados de uma turma\n");
            System.out.println("7. Voltar\n");
            int result = Console.readInt("Digite um número entre 1 e 7: ");

            switch(result)
            {
                case 1 ->{ //cadastrar
                    int disciplineId = Console.readInt("Informe o id da disciplina da turma: ");
                    int teacherId = Console.readInt("Informe o id do Professor: ");
                    String schoolTerm = Console.readLine("Informe o período da turma: ");
                    try{
                        Discipline discipline = disciplineService.read(disciplineId);
                        Teacher teacher = teacherService.read(teacherId);
                        classroom = new Classroom(discipline, schoolTerm, teacher);
                        classroomService.create(classroom);
                        System.out.println("Turma de numero " + classroom.getId() + " da disciplina " + discipline.getName()
                                + " no periodo " + schoolTerm + " criada com sucesso!\n");
                    }catch(EntityNotFoundException e)
                    {
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 2 ->{ // removendo
                    int id = Console.readInt("Informe o id da turma: ");
                    try
                    {
                        classroomService.remove(id);
                        System.out.println("Turma " + id + " removida com sucesso!\n");
                    }catch(EntityNotFoundException | ClassroomNotEmptyException e)
                    {
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 3 ->{ // listar
                    List<Classroom> classrooms = classroomService.readAll();
                    System.out.println("Turmas cadastradas no sistema:\n");
                    for(Classroom c: classrooms)
                        System.out.println(c);
                }
                case 4 ->{ // listar turmas da disciplina X
                    int id = Console.readInt("Informe o id da disciplina: ");
                    try
                    {
                        Discipline discipline = disciplineService.read(id);
                        List<Classroom> classrooms = classroomService.readAll();
                        for(Classroom c: classrooms)
                        {
                            if(c.getDiscipline().equals(discipline))
                                System.out.println(c);
                        }
                    }catch(EntityNotFoundException e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 5 ->{
                    System.out.println("Alterando turma\n");
                }
                case 6 ->{ //listar dados de uma turma
                    int id = Console.readInt("Informe o id da turma: ");
                    try{
                        classroom = classroomService.read(id);
                        System.out.println(classroom);
                        System.out.println("Alunos Inscritos:\n");
                        List<Registry> registries = classroomService.readAllStudents(id);
                        for(Registry r : registries)
                            System.out.println(r);
                    }catch(EntityNotFoundException e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 7 -> loop = false;
                default -> System.out.println("Opção Inválida\n");
            }
        }
    }
}
