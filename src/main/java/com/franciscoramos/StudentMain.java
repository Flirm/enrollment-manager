package com.franciscoramos;

import com.franciscoramos.exception.DisciplineAlreadyCompleteException;
import com.franciscoramos.exception.EntityNotFoundException;
import com.franciscoramos.exception.StudentEnrolledException;
import com.franciscoramos.model.Classroom;
import com.franciscoramos.model.Registry;
import com.franciscoramos.model.Student;
import com.franciscoramos.service.ClassroomService;
import com.franciscoramos.service.DisciplineService;
import com.franciscoramos.service.RegistryService;
import com.franciscoramos.service.StudentService;
import corejava.Console;

import java.util.ArrayList;
import java.util.List;

public class StudentMain
{
    private final StudentService studentService = new StudentService();
    private final DisciplineService disciplineService = new DisciplineService();
    private final ClassroomService classroomService = new ClassroomService();
    private final RegistryService registryService = new RegistryService();

    public void run()
    {
        String name;
        String email;
        Student student;

        boolean loop = true;
        while(loop)
        {
            System.out.println("=======================================================\n");
            System.out.println("O que deseja fazer?\n");
            System.out.println("1. Matricular Aluno na instituíção\n");
            System.out.println("2. Desmatricular Aluno da instituíção\n");
            System.out.println("3. Alterar Aluno\n");
            System.out.println("4. Inscrever Aluno em disciplina\n");
            System.out.println("5. Trancar disciplina \n");
            System.out.println("6. Listar todos os Alunos\n");
            System.out.println("7. Histórico do Aluno\n");
            System.out.println("8. Listar disciplinas sendo cursadas\n");
            System.out.println("9. Atribuir Nota a Disciplina\n");
            System.out.println("10. Voltar\n");
            int result = Console.readInt("Digite um número entre 1 e 9: ");

            switch(result)
            {
                case 1 ->{ //matricular
                    name = Console.readLine("Informe o nome do aluno: ");
                    String[] names = name.split(" ");
                    email = Character.toLowerCase(name.charAt(0)) + names[names.length - 1].toLowerCase() + "@id.uff.br";
                    student = new Student(name, email);
                    studentService.create(student);
                    System.out.println("\nAluno " + name + " matriculado com sucesso!");
                    System.out.println("Numero de Matricula: " + student.getId());
                    System.out.println("Email: " + email + "\n");
                }
                case 2 ->{ //desmatricular
                    int id = Console.readInt("Informe a matricula do aluno: ");
                    try
                    {
                        studentService.remove(id);
                        System.out.println("Aluno " + id + " desmatriculado com sucesso!\n");
                    } catch (EntityNotFoundException | StudentEnrolledException e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 3 ->{ //alterar
                    System.out.println("Alterando Aluno\n");
                }
                case 4 ->{ //inscrever em disciplina
                    int id = Console.readInt("Informe o id do aluno: ");
                    try
                    {
                        student = studentService.read(id);
                        id = Console.readInt("Informe o ID da disciplina: ");
                        ArrayList<Classroom> classrooms = disciplineService.read(id).getClassrooms();
                        System.out.println("Turmas Disponiveis: \n");
                        for(Classroom classroom : classrooms)
                            System.out.println(classroom);
                        id = Console.readInt("Informe o ID da turma que deseja: ");
                        Classroom classroom = classroomService.read(id);
                        Registry registry = new Registry(student, classroom);
                        registryService.create(registry);
                        student.getRegisteredClasses().put(registry.getId(), registry);
                        classroom.getRegisteredStudents().add(registry);
                        System.out.println("Aluno " + id + " inscrito na turma de " + classroom.getName() + " com sucesso!\n");
                        System.out.println("Numero de inscricao: " + registry.getId());
                    }catch(EntityNotFoundException e)
                    {
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 5 ->{ //trancar disciplina
                    int id = Console.readInt("Informe o ID do aluno: ");
                    try
                    {
                        student = studentService.read(id);
                        System.out.println("Disciplinas que podem ser trancadas: ");
                        for(Registry r : student.getRegisteredClasses().values().stream().filter((reg) -> reg.getGrade() == -1).toList())
                            System.out.println(r);
                        id = Console.readInt("Informe o ID da inscricao: ");
                        Registry registry = registryService.read(id);
                        student.getRegisteredClasses().remove(registry.getId());
                        registry.getClassroom().getRegisteredStudents().remove(registry);
                        registryService.remove(id);
                        System.out.println("Disciplina trancada com sucesso!\n");
                    }catch(EntityNotFoundException | DisciplineAlreadyCompleteException e)
                    {
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 6 ->{ //listar alunos
                    List<Student> students = studentService.readAll();
                    System.out.println("Alunos matriculados no sistema:\n");
                    for(Student studentInList : students)
                    {
                        System.out.println(studentInList);
                    }

                }
                case 7 ->{ //listar historico
                    int id = Console.readInt("Informe a matricula do aluno: ");
                    try
                    {
                        student = studentService.read(id);
                        System.out.println("Histórico do aluno: " + student.getId() + "\n");
                        for(Registry registry : student.getRegisteredClasses().values().stream().filter((reg) -> reg.getGrade() != -1).toList()) {
                                System.out.println(registry);
                        }
                    }catch(EntityNotFoundException e){
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 8 -> { //Listar disciplinas sendo cursadas
                    int id = Console.readInt("Informe a matricula do aluno: ");
                    try
                    {
                        student = studentService.read(id);
                        System.out.println("Disciplinas sendo cursadas do aluno: " + student.getId() + "\n");
                        for(Registry registry : student.getRegisteredClasses().values().stream().filter((reg) -> reg.getGrade() == -1).toList()) {
                            System.out.println(registry);
                        }
                    }catch(EntityNotFoundException e){
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 9 -> { //atribuir nota a uma disciplina
                    int id = Console.readInt("Informe a matricula do aluno: ");
                    try
                    {
                        student = studentService.read(id);
                        System.out.println("Inscricoes ainda sem nota atribuida: ");
                        for(Registry registry : student.getRegisteredClasses().values().stream().filter((reg) -> reg.getGrade() == -1).toList()) {
                            System.out.println(registry);
                        }
                        id = Console.readInt("Informe o ID da inscricao que deseja atribuir nota: ");
                        int newGrade = Console.readInt("Informe a nota do aluno: ");
                        registryService.updateGrade(id, newGrade);
                    }catch(EntityNotFoundException e){
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 10 -> loop = false;
                default -> System.out.println("Opção Inválida\n");
            }
        }
    }
}
