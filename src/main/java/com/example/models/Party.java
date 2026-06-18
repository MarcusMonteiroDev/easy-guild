package com.example.models;

import java.util.ArrayList;
import java.util.Scanner;

public class Party {
    private ArrayList<Jogador> jogadores = new ArrayList<>();

    // getters e setters
    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    // metodos privados

    // metodos publicos
    public void listarJogadores() {
        if (jogadores.isEmpty()) {
            System.out.println("Não há jogadores cadastrados.");
            return;
        }

        System.out.println("Jogadores da equipe:\n");

        System.out.printf(
                "%-15s %-15s %-12s %-10s %-10s %-8s %-8s %-8s %-8s %-25s %-30s%n",
                "Nome",
                "Classe",
                "Raça",
                "VidaMax",
                "VidaAtual",
                "XP",
                "Ouro",
                "Ataque",
                "Defesa",
                "Idiomas",
                "Equipamentos");

        for (Jogador jogador : jogadores) {

            String idiomas = String.join(", ", jogador.getIdiomas());
            String equipamentos = String.join(", ", jogador.getEquipamentos());

            System.out.printf(
                    "%-15s %-15s %-12s %-10d %-10d %-8d %-8d %-8d %-8d %-25s %-30s%n",
                    jogador.getNome(),
                    jogador.getClasse(),
                    jogador.getRaca(),
                    jogador.getVidaMax(),
                    jogador.getVidaAtual(),
                    jogador.getXp(),
                    jogador.getOuro(),
                    jogador.getAtaque(),
                    jogador.getDefesa(),
                    idiomas,
                    equipamentos);
        }
    }

    public void cadastrarJogador(Scanner scanner) {

        Jogador jogador = new Jogador();

        System.out.println("Insira os dados do jogador.");

        System.out.print("Nome: ");
        jogador.setNome(scanner.nextLine());

        System.out.print("Classe: ");
        jogador.setClasse(scanner.nextLine());

        System.out.print("Raça: ");
        jogador.setRaca(scanner.nextLine());

        System.out.print("Vida Máxima: ");
        jogador.setVidaMax(scanner.nextInt());

        System.out.print("Vida Atual: ");
        jogador.setVidaAtual(scanner.nextInt());

        System.out.print("XP: ");
        jogador.setXp(scanner.nextInt());

        System.out.print("Ouro: ");
        jogador.setOuro(scanner.nextInt());

        System.out.print("Ataque: ");
        jogador.setAtaque(scanner.nextInt());

        System.out.print("Defesa: ");
        jogador.setDefesa(scanner.nextInt());

        scanner.nextLine(); // consome o ENTER deixado pelo último nextInt()

        jogadores.add(jogador);

    }
}