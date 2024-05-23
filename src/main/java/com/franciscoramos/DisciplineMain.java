package com.franciscoramos;

import corejava.Console;

public class DisciplineMain
{

    public void run()
    {


        boolean loop = true;
        while(loop)
        {
            System.out.println("=======================================================\n");
            System.out.println("O que deseja fazer?\n");
            System.out.println("1. Cadastrar disciplina\n");
            System.out.println("2. Remover disciplina\n");
            System.out.println("3. Listar todas as disciplinas\n");
            System.out.println("4. Alterar disciplina\n");
            System.out.println("5. Voltar\n");
            int result = Console.readInt("Digite um número entre 1 e 5: ");

            switch(result)
            {
                case 1 ->{
                    System.out.println("Cadastrando disciplina\n");
                }
                case 2 ->{
                    System.out.println("Removendo disciplina\n");
                }
                case 3 ->{
                    System.out.println("Listando disciplinas\n");
                }
                case 4 ->{
                    System.out.println("Alterando disciplina\n");
                }
                case 5 -> loop = false;
                default -> System.out.println("Opção Inválida\n");
            }
        }
    }
}
