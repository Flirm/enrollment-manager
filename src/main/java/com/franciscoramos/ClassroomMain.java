package com.franciscoramos;

import com.franciscoramos.exception.ClassroomNotEmptyException;
import com.franciscoramos.exception.DisciplineAlreadyCompleteException;
import com.franciscoramos.exception.DisciplineWithClassroomsException;
import com.franciscoramos.exception.EntityNotFoundException;
import com.franciscoramos.model.Classroom;
import com.franciscoramos.model.Discipline;
import com.franciscoramos.model.Registry;
import com.franciscoramos.model.Teacher;
import com.franciscoramos.service.ClassroomService;
import com.franciscoramos.service.DisciplineService;
import com.franciscoramos.service.RegistryService;
import com.franciscoramos.service.TeacherService;
import corejava.Console;

import java.util.ArrayList;
import java.util.List;

public class ClassroomMain
{
    private final ClassroomService classroomService = new ClassroomService();
    private final DisciplineService disciplineService = new DisciplineService();
    private final TeacherService teacherService = new TeacherService();
    private final RegistryService registryService = new RegistryService();

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
            System.out.println("5. Listar dados de uma turma\n");
            System.out.println("6. Taxa de aprovação do período por turma\n");
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
                        teacher.getClassrooms().add(classroom);
                        discipline.getClassrooms().add(classroom);
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
                        int r = Console.readInt("Deseja remover todos os alunos e professor associado? (1.Sim Outro.Nao): ");
                        if(r == 1)
                        {
                            try
                            {
                                classroom = classroomService.read(id);
                                Teacher teacher = classroom.getTeacher();
                                teacher.getClassrooms().remove(classroom);
                                classroom.setTeacher(null);
                                ArrayList<Registry> registeredStudents = classroom.getRegisteredStudents();
                                for(Registry registry : registeredStudents)
                                {
                                    registryService.remove(registry.getId());
                                    registry.getStudent().getRegisteredClasses().remove(registry.getId());
                                }
                                classroom.getRegisteredStudents().removeAll(registeredStudents);
                                classroomService.remove(id);
                                System.out.println("Turma " + id + " removida com sucesso!\n");
                            }catch(EntityNotFoundException | DisciplineAlreadyCompleteException e1)
                            {
                                System.out.println(e1.getMessage() + "\n");
                            }

                        }
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
                case 5 ->{ //listar dados de uma turma
                    int id = Console.readInt("Informe o id da turma: ");
                    try{
                        classroom = classroomService.read(id);
                        System.out.println(classroom);
                        System.out.println("Alunos Inscritos:\n");
                        List<Registry> registries = classroomService.readAllStudents(id);
                        for(Registry r : registries)
                            System.out.println(r.getStudent().getName() + "\nNota: " + r.getGrade() + "\nPresença: " + (r.getEnoughPresence() ? "Suficiente" : "Insuficiente" + "\n"));
                    }catch(EntityNotFoundException e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 6 ->{ //taxa de aprovacao por turma em um periodo
                    String term = Console.readLine("Informe o período desejado: ");
                    try{
                        List<Classroom> classrooms = classroomService.readAll().stream().filter((c) -> c.getSchoolTerm().equals(term)).toList();
                        for(Classroom c : classrooms){
                            System.out.println("Turma: ");
                            System.out.println(c);
                            System.out.println("Taxa de aprovação: " + ((float)c.getApprovedStudentCount()/c.getStudentCount())*100 + "%\n");
                        }
                    }catch(EntityNotFoundException e){
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 7 -> loop = false;
                default -> System.out.println("Opção Inválida\n");
            }
        }
    }
}
