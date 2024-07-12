package com.franciscoramos;

import com.franciscoramos.exception.DisciplineWithClassroomsException;
import com.franciscoramos.exception.EntityNotFoundException;
import com.franciscoramos.model.Discipline;
import com.franciscoramos.service.DisciplineService;
import corejava.Console;

import java.util.List;

public class DisciplineMain
{
    private final DisciplineService disciplineService = new DisciplineService();

    public void run()
    {
        String name;
        Discipline discipline;


        boolean loop = true;
        while(loop)
        {
            System.out.println("=======================================================\n");
            System.out.println("O que deseja fazer?\n");
            System.out.println("1. Cadastrar disciplina\n");
            System.out.println("2. Remover disciplina\n");
            System.out.println("3. Listar todas as disciplinas\n");
            System.out.println("4. Alterar carga horária da disciplina\n");
            System.out.println("5. Voltar\n");
            int result = Console.readInt("Digite um número entre 1 e 5: ");

            switch(result)
            {
                case 1 ->{ //Criando disciplina nova
                    name = Console.readLine("Informe o nome da disciplina: ");
                    int workLoad = Console.readInt("Informe a carga horária: ");
                    discipline = new Discipline(name, workLoad);
                    int haveRequisite = Console.readInt("A disciplina tem Pré-Requisitos? (1.Sim 0.Não): ");
                    if(haveRequisite == 1){
                        Discipline requisite;
                        while(haveRequisite != -1){
                            haveRequisite = Console.readInt("Informe o ID da disciplina que é Pré-Requisito (-1 para sair): ");
                            try {
                                requisite = disciplineService.read(haveRequisite);
                                discipline.getPreRequisites().add(requisite);
                            }
                            catch(EntityNotFoundException e) {System.out.println(e.getMessage() + "\n");}
                        }
                    }
                    disciplineService.create(discipline);
                    System.out.println("Disciplina " + name + " de " + workLoad + " horas com ID " + discipline.getId() + " criada com sucesso\n");
                }
                case 2 ->{ //Remover
                    int id = Console.readInt("Informe o id da disciplina: ");
                    try{
                        disciplineService.remove(id);
                        System.out.println("Disciplina " + id + " removida com sucesso\n");
                    }catch(EntityNotFoundException | DisciplineWithClassroomsException e){
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 3 ->{ //listando
                    List<Discipline> disciplines = disciplineService.readAll();
                    System.out.println("Disciplinas cadastradas no sistema:\n");
                    for(Discipline disciplineInList : disciplines)
                    {
                        System.out.println(disciplineInList);
                    }
                }
                case 4 ->{ //alterar
                    int id = Console.readInt("Informe o id da disciplina: ");
                    try
                    {
                        int workLoad = Console.readInt("Digite a nova carga horaria: ");
                        disciplineService.update(id, workLoad);
                        System.out.println("Nova carga horaria registrada.\n");
                    }catch(EntityNotFoundException e)
                    {
                        System.out.println(e.getMessage() + "\n");
                    }
                }
                case 5 -> loop = false;
                default -> System.out.println("Opção Inválida\n");
            }
        }
    }
}
