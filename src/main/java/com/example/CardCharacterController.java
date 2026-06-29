package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CardCharacterController {

    @FXML
    private Label nomePersonagem;

    @FXML
    private Label nivelPersonagem;

    @FXML
    private Label classeRaca;

    @FXML
    private Label hpPoints;

    @FXML
    private Label xpPoints;

    @FXML
    private Label valorAtk;

    @FXML
    private Label valorDef;

    @FXML
    private Label valorOuro;

    @FXML
    private Button btnDano;

    @FXML
    private Button btnCura;

    @FXML
    private Button btnXp;

    @FXML
    private Button btnOuro;

    @FXML
    private Button btnMenosOuro;

    @FXML
    public void initialize() {
        System.out.println("Classe iniciada");

        btnOuro.setOnAction(event -> darOuro());
        btnMenosOuro.setOnAction(event -> tirarOuro());
    }

    public void criarPersonagem(
            String nome,
            int nivel,
            String classe,
            String raca,
            int vidaMax,
            int vidaAtual,
            int xpAtual,
            int xpProxNivel,
            int ouro,
            int ataque,
            int defesa) {
        nomePersonagem.setText(nome);
        nivelPersonagem.setText(String.valueOf(nivel));
        classeRaca.setText(String.format("%s - %s", String.valueOf(raca), String.valueOf(classe)));
        hpPoints.setText(String.format("%s / %s", String.valueOf(vidaAtual), String.valueOf(vidaMax)));
        xpPoints.setText(String.format("%s / %s", String.valueOf(xpAtual), String.valueOf(xpProxNivel)));
        valorAtk.setText(String.valueOf(ataque));
        valorDef.setText(String.valueOf(defesa));
        valorOuro.setText(String.valueOf(ouro));
    }

    private void darOuro() {
        int valor = Integer.parseInt(valorOuro.getText()) + 10;
        valorOuro.setText(String.valueOf(valor));
    }

    private void tirarOuro() {
        int valor = Integer.parseInt(valorOuro.getText()) - 10;
        valorOuro.setText(String.valueOf(valor));
    }

}
