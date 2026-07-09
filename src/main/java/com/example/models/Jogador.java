package com.example.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.enums.Classes;
import com.example.enums.Equipamentos;
import com.example.enums.Idiomas;
import com.example.enums.Racas;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Jogador {
    private final String ID = UUID.randomUUID().toString();

    private final StringProperty nome = new SimpleStringProperty(this, "nome", "Personagem");
    private final IntegerProperty nivel = new SimpleIntegerProperty(this, "nivel", 1);
    private final StringProperty classe = new SimpleStringProperty(this, "classe", "Classe");
    private final StringProperty raca = new SimpleStringProperty(this, "raca", "Raça");
    private final IntegerProperty vidaMax = new SimpleIntegerProperty(this, "vidaMax", 100);
    private final IntegerProperty vidaAtual = new SimpleIntegerProperty(this, "vidaAtual", 100);
    private final BooleanProperty jogadorVivo = new SimpleBooleanProperty(this, "jogadorVivo", true);
    private final IntegerProperty xpProxNivel = new SimpleIntegerProperty(this, "xpProxNivel", 100);
    private final IntegerProperty xpAtual = new SimpleIntegerProperty(this, "xpAtual", 100);
    private final IntegerProperty ouro = new SimpleIntegerProperty(this, "ouro", 0);
    private final IntegerProperty ataque = new SimpleIntegerProperty(this, "ataque", 10);
    private final IntegerProperty defesa = new SimpleIntegerProperty(this, "defesa", 10);
    private final ListProperty<Idiomas> idiomas = new SimpleListProperty<>(this, "idiomas",
            FXCollections.observableArrayList());
    private final ListProperty<Equipamentos> equipamentos = new SimpleListProperty<>(this, "equipamentos",
            FXCollections.observableArrayList());

    // construtor
    public Jogador() {
        this.vidaAtual.addListener((observable, valorAntigo, valorNovo) -> {
            atualizarEstadoJogador(valorNovo.intValue());
        });
    }

    // getters e setters
    public String getID() {
        return ID;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty nomProperty() {
        return nome;
    }

    public int getNivel() {
        return nivel.get();
    }

    public void setNivel(int nivel) {
        this.nivel.set(nivel);
    }

    public IntegerProperty nivelProperty() {
        return nivel;
    }

    public String getClasse() {
        return classe.get();
    }

    public void setClasse(String classe) {
        if (verificaExistencia(classe, Classes.class))
            this.classe.set(classe);
        else
            throw new IllegalArgumentException("A classe informada não existe");
    }

    public StringProperty classeProperty() {
        return classe;
    }

    public String getRaca() {
        return raca.get();
    }

    public void setRaca(String raca) {
        if (verificaExistencia(raca, Racas.class))
            this.raca.set(raca);
        else
            throw new IllegalArgumentException("A raça informada não existe");
    }

    public StringProperty racaProperty() {
        return raca;
    }

    public int getVidaMax() {
        return vidaMax.get();
    }

    public void setVidaMax(int vidaMax) {
        verificaIntervalo(vidaMax);
        this.vidaMax.set(vidaMax);
    }

    public IntegerProperty vidaMaxProperty() {
        return vidaMax;
    }

    public int getVidaAtual() {
        return vidaAtual.get();
    }

    public void setVidaAtual(int vidaAtual) {
        verificaIntervalo(vidaAtual);
        this.vidaAtual.set(vidaAtual);
    }

    public IntegerProperty vidaAtualProperty() {
        return vidaAtual;
    }

    public boolean getJogadorVivo() {
        return jogadorVivo.get();
    }

    public void setJogadorVivo(boolean valor) {
        this.jogadorVivo.set(valor);
    }

    public BooleanProperty jogadorVivoProperty() {
        return jogadorVivo;
    }

    public int getxpProxNivel() {
        return xpProxNivel.get();
    }

    public void setxpProxNivel(int xpProxNivel) {
        this.xpProxNivel.set(xpProxNivel);
    }

    public IntegerProperty xpProxNivelProperty() {
        return xpProxNivel;
    }

    public int getxpAtual() {
        return xpAtual.get();
    }

    public void setxpAtual(int xpAtual) {
        this.xpAtual.set(xpAtual);
    }

    public IntegerProperty xpAtualProperty() {
        return xpAtual;
    }

    public int getOuro() {
        return ouro.get();
    }

    public void setOuro(int valor) {
        this.ouro.set(valor);
    }

    public IntegerProperty ouroProperty() {
        return ouro;
    }

    public int getAtaque() {
        return ataque.get();
    }

    public void setAtaque(int ataque) {
        this.ataque.set(ataque);
    }

    public IntegerProperty ataqueProperty() {
        return ataque;
    }

    public int getDefesa() {
        return defesa.get();
    }

    public void setDefesa(int defesa) {
        this.defesa.set(defesa);
    }

    public IntegerProperty defesaProperty() {
        return defesa;
    }

    public ObservableList<Idiomas> getIdiomas() {
        return idiomas.get();
    }

    public void setIdiomas(List<Idiomas> idiomas) {
        this.idiomas.setAll(idiomas);
    }

    public ListProperty<Idiomas> idiomasProperty() {
        return idiomas;
    }

    public ObservableList<Equipamentos> getEquipamentos() {
        return equipamentos.get();
    }

    public void setEquipamentos(List<Equipamentos> equipamentos) {
        this.equipamentos.setAll(equipamentos);
    }

    public ListProperty<Equipamentos> equipamentosProperty() {
        return equipamentos;
    }

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

    private void atualizarEstadoJogador(int valorNovo) {
        if (valorNovo <= 0) {
            jogadorVivo.set(false);
            System.out.println("O jogador morreu");
        }

    }

    // metodos publicos
    @Override
    public String toString() {
        return """
                ID: %s
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
                getID(),
                getNome(),
                getNivel(),
                getClasse(),
                getRaca(),
                getVidaAtual(),
                getVidaMax(),
                getxpAtual(),
                getxpProxNivel(),
                getOuro(),
                getAtaque(),
                getDefesa());
    }
}
