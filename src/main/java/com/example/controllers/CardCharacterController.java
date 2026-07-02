package com.example.controllers;

import java.io.IOException;
import com.example.models.Jogador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private Label hpMax;

    @FXML
    private Label xpPoints;

    @FXML
    private Label xpNextLvl;

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
        System.out.println("Card de personagem criado");

    }

    public void criarPersonagem(Jogador jogador) {
        nomePersonagem.setText(jogador.getNome());
        nivelPersonagem.setText("Lvl " + String.valueOf(jogador.getNivel()));
        classeRaca.setText(String.format("%s - %s", jogador.getRaca(), jogador.getClasse()));
        hpPoints.setText(String.valueOf(jogador.getVidaAtual()));
        hpMax.setText(String.valueOf(jogador.getVidaMax()));
        xpPoints.setText(String.valueOf(jogador.getxpAtual()));
        xpNextLvl.setText(String.valueOf(jogador.getxpProxNivel()));
        valorAtk.setText(String.valueOf(jogador.getAtaque()));
        valorDef.setText(String.valueOf(jogador.getDefesa()));
        valorOuro.setText(String.valueOf(jogador.getOuro()));
    }

    @FXML
    private void darOuro() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();

        valorOuro.setText(String.valueOf(Integer.parseInt(valorOuro.getText()) + popUpController.getValor()));
    }

    @FXML
    private void tirarOuro() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();

        valorOuro.setText(String.valueOf(Integer.parseInt(valorOuro.getText()) - popUpController.getValor()));
    }

    @FXML
    private void darXp() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();

        int xpMax = Integer.parseInt(xpNextLvl.getText());
        int xpAtual = Integer.parseInt(xpPoints.getText());
        int xpDado = popUpController.getValor();

        if (xpDado + xpAtual >= xpMax) {
            // Aumenta o nível
            int nivelAtual = Integer.parseInt(nivelPersonagem.getText().replaceAll("[a-zA-Z]", "").trim());
            nivelPersonagem.setText("Lvl " + ++nivelAtual);

            // Insere o xp restante para o próximo nível
            int xpRestante = (xpDado + xpAtual) - xpMax;
            xpPoints.setText(String.valueOf(xpRestante));
        } else {
            xpPoints.setText(String.valueOf(xpAtual + xpDado));
        }
    }

    @FXML
    private void aplicarDano() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();

        int vidaAtual = Integer.parseInt(hpPoints.getText());
        int dano = popUpController.getValor();

        if (dano <= vidaAtual)
            hpPoints.setText(String.valueOf(vidaAtual - dano));
        else
            hpPoints.setText(String.valueOf(0));

    }

    @FXML
    private void aplicarCura() throws IOException {
        QuantidadePopUpController popUpController = abrirPopUp();

        int vidaAtual = Integer.parseInt(hpPoints.getText());
        int cura = popUpController.getValor();

        if (cura >= Integer.parseInt(hpMax.getText()))
            hpPoints.setText(hpMax.getText());
        else
            hpPoints.setText(String.valueOf(vidaAtual + cura));

    }

    private QuantidadePopUpController abrirPopUp() throws IOException {
        // Cria a classe responsável por carregar os arquivos fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/QuantidadePopUp.fxml"));
        // Carrega o fxml
        Parent root = loader.load();
        // Carrega o controller associado ao fxml carregado
        QuantidadePopUpController quantidadePopUpController = loader.getController();

        Stage popup = new Stage();

        popup.initModality(Modality.APPLICATION_MODAL);

        popup.setScene(new Scene(root));

        popup.showAndWait();

        return quantidadePopUpController;
    }

}
