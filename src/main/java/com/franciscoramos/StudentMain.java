package com.franciscoramos;

import com.franciscoramos.exception.EntityNotFoundException;
import com.franciscoramos.exception.StudentEnrolledException;
import com.franciscoramos.model.Discipline;
import com.franciscoramos.model.Student;
import com.franciscoramos.service.StudentService;
import corejava.Console;

import java.util.List;

public class StudentMain
{
    private final StudentService studentService = new StudentService();

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
            System.out.println("8. Voltar\n");
            int result = Console.readInt("Digite um número entre 1 e 8: ");

            switch(result)
            {
                case 1 ->{ //matricular
                    name = Console.readLine("Informe o nome do aluno: ");
                    email = name.charAt(0) + name.split(" ")[1].toLowerCase() + "@id.uff.br";
                    student = new Student(name, email);
                    studentService.create(student);
                    System.out.println("Aluno " + name + " matriculado com sucesso!\n");
                    System.out.println("Numero de Matricula: " + student.getId() + "\n");
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
                    System.out.println("Inscrevendo Aluno\n");
                }
                case 5 ->{ //trancar disciplina
                    System.out.println("Trancando disciplina\n");
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
                        for(Discipline discipline : student.getCompletedDisciplines().keySet()) {
                            System.out.println("Nome: " + discipline + "\n");
                            System.out.println("Nota: " + student.getCompletedDisciplines().get(discipline) + "\n");
                        }
                    }catch(EntityNotFoundException e){
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 8 -> loop = false;
                default -> System.out.println("Opção Inválida\n");
            }
        }
    }
}
