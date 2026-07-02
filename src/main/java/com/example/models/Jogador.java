package com.example.models;

import java.util.ArrayList;

import com.example.enums.Classes;
import com.example.enums.Equipamentos;
import com.example.enums.Idiomas;
import com.example.enums.Racas;

public class Jogador {

    private String nome;
    private int nivel;
    private String classe;
    private String raca;
    private int vidaMax;
    private int vidaAtual;
    private int xpProxNivel;
    private int xpAtual;
    private int ouro;
    private int ataque;
    private int defesa;
    // private ArrayList<String> idiomas;
    // private ArrayList<Equipamento> equipamentos;

    // getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        if (verificaExistencia(classe, Classes.class))
            this.classe = classe;
        else
            throw new IllegalArgumentException("A classe informada não existe");
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        if (verificaExistencia(raca, Racas.class))
            this.raca = raca;
        else
            throw new IllegalArgumentException("A raça informada não existe");
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public void setVidaMax(int vidaMax) {
        verificaIntervalo(vidaMax);
        this.vidaMax = vidaMax;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public void setVidaAtual(int vidaAtual) {
        verificaIntervalo(vidaAtual);
        this.vidaAtual = vidaAtual;
    }

    public int getxpProxNivel() {
        return xpProxNivel;
    }

    public void setxpProxNivel(int xpProxNivel) {
        this.xpProxNivel = xpProxNivel;
    }

    public int getxpAtual() {
        return xpAtual;
    }

    public void setxpAtual(int xpAtual) {
        this.xpAtual = xpAtual;
    }

    public int getOuro() {
        return ouro;
    }

    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
    /*
     * public String getIdiomas() {
     * return String.join(", ", idiomas);
     * }
     * 
     * public void setIdiomas(ArrayList<String> idiomas) {
     * for (String idioma : idiomas) {
     * if (!verificaExistencia(idioma, Idiomas.class))
     * throw new IllegalArgumentException("Um dos idiomas informados não existe.");
     * }
     * this.idiomas = idiomas;
     * }
     */
    // metodos privados

    // verifica se um valor esta presente em um enum
    private <T extends Enum<T>> boolean verificaExistencia(String nome, Class<T> enumClass) {
        try {
            Enum.valueOf(enumClass, nome);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void verificaIntervalo(int valor) {
        if (valor > 100 || valor < 0)
            throw new IllegalArgumentException("Valor inválido para o parâmetro inserido");
    }

    // metodos publicos
    @Override
    public String toString() {
        return """
                Nome: %s
                Nível: %d
                Classe: %s
                Raça: %s
                Vida: %d/%d
                XP: %d/%d
                Ouro: %d
                Ataque: %d
                Defesa: %d
                """.formatted(
                nome,
                nivel,
                classe,
                raca,
                vidaAtual,
                vidaMax,
                xpAtual,
                xpProxNivel,
                ouro,
                ataque,
                defesa);
    }
}
