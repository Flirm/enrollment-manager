package com.franciscoramos;

import com.franciscoramos.service.TeacherService;
import corejava.Console;

public class TeacherMain
{
    public static final TeacherService teacherService = new TeacherService();

    public void run()
    {
        boolean loop = true;
        while(loop)
        {
            System.out.println("=======================================================\n");
            System.out.println("O que deseja fazer?\n");
            System.out.println("1. Cadastrar Professor na instituíção\n");
            System.out.println("2. Descadastrar Professor da instituíção\n");
            System.out.println("3. Alterar Professor\n");
            System.out.println("4. Alocar Professor em Turma\n");
            System.out.println("5. Listar todos os Professores\n");
            System.out.println("6. Voltar\n");
            int result = Console.readInt("Digite um número entre 1 e 6: ");

            switch(result)
            {
                case 1 ->{
                    System.out.println("Cadastrando\n");
                }
                case 2 ->{
                    System.out.println("Descadastrando\n");
                }
                case 3 ->{
                    System.out.println("Alterando Professor\n");
                }
                case 4 ->{
                    System.out.println("Alocando Professor em Turma\n");
                }
                case 5 ->{
                    System.out.println("Listando Professores\n");
                }
                case 6 -> loop = false;
                default -> System.out.println("Opção Inválida\n");
            }
        }

    }
}
