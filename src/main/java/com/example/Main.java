package com.example;

import java.io.File;

import com.example.models.Jogador;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        Jogador jogador = new Jogador();

        jogador.setNome("Arthas");
        jogador.setNivel(15);
        jogador.setClasse("GUERREIRO");
        jogador.setRaca("HUMANO");

        jogador.setVidaMax(100);
        jogador.setVidaAtual(85);

        jogador.setxpAtual(250);
        jogador.setxpProxNivel(300);
        jogador.setOuro(150);

        jogador.setAtaque(20);
        jogador.setDefesa(15);

        ObjectMapper mapper = new ObjectMapper();
        try {

        } catch (Exception e) {
            System.out.println("erro:");
            System.out.println(e);
        }

    }

}
