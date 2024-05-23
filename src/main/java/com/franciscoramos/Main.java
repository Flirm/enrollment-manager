package com.franciscoramos;

import corejava.Console;

public class Main
{
    public static void main(String[] args)
    {
        StudentMain studentMain = new StudentMain();
        DisciplineMain disciplineMain = new DisciplineMain();
        ClassroomMain classroomMain = new ClassroomMain();

        boolean looping = true;
        while(looping)
        {
            System.out.println("=======================================================\n");
            System.out.println("O que deseja fazer?\n");
            System.out.println("1. Tratar Alunos\n");
            System.out.println("2. Tratar Disciplinas\n");
            System.out.println("3. Tratar Turmas\n");
            System.out.println("4. Sair\n");
            int result = Console.readInt("Digite um número entre 1 e 4: ");
            System.out.println();

            switch (result)
            {
                case 1 -> studentMain.run();
                case 2 -> disciplineMain.run();
                case 3 -> classroomMain.run();
                case 4 -> looping = false;
                default -> System.out.println("Opção Inválida\n");
            }
        }
    }
}
