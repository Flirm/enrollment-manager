package com.franciscoramos;

import com.franciscoramos.service.ClassroomService;
import corejava.Console;

public class ClassroomMain
{
    private final ClassroomService classroomService = new ClassroomService();

    public void run()
    {

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
                case 1 ->{
                    System.out.println("Cadastrando turma\n");
                }
                case 2 ->{
                    System.out.println("Removendo turma\n");
                }
                case 3 ->{
                    System.out.println("Listando todas as turmas\n");
                }
                case 4 ->{
                    System.out.println("Listando turmas da disciplina\n");
                }
                case 5 ->{
                    System.out.println("Alterando turma\n");
                }
                case 6 ->{
                    System.out.println("Listando dados da turma\n");
                }
                case 7 -> loop = false;
                default -> System.out.println("Opção Inválida\n");
            }
        }
    }
}
