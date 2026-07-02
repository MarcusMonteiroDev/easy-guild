package com.example.models;

import java.util.ArrayList;

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
    public void setJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    @Override
    public String toString() {
        return jogadores.isEmpty()
                ? "A party não possui jogadores."
                : jogadores.stream()
                        .map(Jogador::toString)
                        .reduce("=== PARTY ===\n\n",
                                (a, b) -> a + b + "\n--------------------\n");
    }

}