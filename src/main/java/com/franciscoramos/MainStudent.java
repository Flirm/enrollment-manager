package com.franciscoramos;

import corejava.Console;

public class MainStudent
{

    public void run()
    {


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
                case 1 ->{
                    System.out.println("Matriculando\n");
                }
                case 2 ->{
                    System.out.println("Desmatriculando\n");
                }
                case 3 ->{
                    System.out.println("Alterando Aluno\n");
                }
                case 4 ->{
                    System.out.println("Inscrevendo Aluno\n");
                }
                case 5 ->{
                    System.out.println("Trancando disciplina\n");
                }
                case 6 ->{
                    System.out.println("Listando alunos\n");
                }
                case 7 ->{
                    System.out.println("Listando histórico do aluno\n");
                }
                case 8 -> loop = false;
                default -> System.out.println("Opção Inválida\n");
            }
        }
    }
}
