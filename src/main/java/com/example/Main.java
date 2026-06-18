package com.example;

import java.util.Scanner;

import com.example.models.Party;

public class Main {

    public static void main(String[] args) {
        Party party = new Party();

        Scanner scanner = new Scanner(System.in);
        int escolhaUsuario;

        menu: while (true) {
            System.out.println("========================================");
            System.out.println("      GERENCIADOR DE PARTY RPG");
            System.out.println("========================================");
            System.out.println();

            System.out.println("========== MENU DO MESTRE ==========");
            System.out.println();

            System.out.println("1  - Listar jogadores");
            System.out.println("2  - Cadastrar jogador");
            System.out.println("3  - Editar jogador");
            System.out.println("4  - Remover jogador");
            System.out.println();

            System.out.println("---------- XP ----------");
            System.out.println("5  - Dar XP para jogador");
            System.out.println("6  - Dividir XP entre a party");
            System.out.println("7  - Consultar XP de jogador");
            System.out.println();

            System.out.println("---------- OURO ----------");
            System.out.println("8  - Dar ouro para jogador");
            System.out.println("9  - Remover ouro de jogador");
            System.out.println("10 - Dividir ouro entre a party");
            System.out.println();

            System.out.println("---------- VIDA ----------");
            System.out.println("11 - Aplicar dano");
            System.out.println("12 - Curar jogador");
            System.out.println();

            System.out.println("---------- EQUIPAMENTOS ----------");
            System.out.println("13 - Adicionar equipamento");
            System.out.println("14 - Remover equipamento");
            System.out.println("15 - Listar equipamentos");
            System.out.println();

            System.out.println("---------- DADOS ----------");
            System.out.println("16 - Rolar d4");
            System.out.println("17 - Rolar d6");
            System.out.println("18 - Rolar d8");
            System.out.println("19 - Rolar d10");
            System.out.println("20 - Rolar d12");
            System.out.println("21 - Rolar d20");
            System.out.println("22 - Rolar d100");
            System.out.println("23 - Rolar dado personalizado");
            System.out.println();

            System.out.println("0  - Sair");
            System.out.println();

            System.out.print("Escolha uma opção: ");
            while (true) {
                try {
                    escolhaUsuario = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(e);
                    System.out.println("Insira uma opção válida.");
                }
            }

            switch (escolhaUsuario) {
                case 0 -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    break menu;
                }

                case 1 -> party.listarJogadores();

                case 2 -> party.cadastrarJogador(scanner);
            }
        }

    }
}
