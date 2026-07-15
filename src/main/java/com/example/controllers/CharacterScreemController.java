package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import com.example.PartyState;
import com.example.models.Jogador;

public class CharacterScreemController {

    @FXML
    private FlowPane galeriaAventureiros;

    public static CharacterScreemController instance;

    // Inicializador
    @FXML
    public void initialize() {
        instance = this;

        for (Jogador jogador : PartyState.getParty()) {
            adicionarJogador(jogador);
        }
    }

    // Getter
    public static CharacterScreemController getInstance() {
        return instance;
    }

    // Métodos privados
    private void alertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Não foi possível registrar o valor");
        alert.setContentText("Insira um valor válido");
        alert.showAndWait();
    }

    private void alertaErroSemJogadores() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Não foi possível registrar o valor");
        alert.setContentText("Não existem jogadores no grupo.");
        alert.showAndWait();
    }

    private VBox criarCard(Jogador jogador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/CharacterCard.fxml"));
            VBox card = loader.load();

            CardCharacterController controller = loader.getController();
            controller.criarPersonagem(jogador);

            return card;
        } catch (IOException e) {
            e.printStackTrace();
            return new VBox(); // Retorna um VBox vazio em caso de erro
        }
    }

    @FXML
    private void dividirOuro() throws IOException {
        if (PartyState.getParty().size() == 0) {
            alertaErroSemJogadores();
            return;
        }

        QuantidadePopUpController popUpController = CardCharacterController.abrirPopUp();
        int valor = popUpController.getValor();

        PartyState.dividirOuroParty(valor);
        PartyState.salvarParty();
    }

    @FXML
    private void dividirXp() throws IOException {
        if (PartyState.getParty().size() == 0) {
            alertaErroSemJogadores();
            return;
        }

        QuantidadePopUpController popUpController;
        Integer valor;
        while (true) {
            popUpController = CardCharacterController.abrirPopUp();
            valor = popUpController.getValor();
            if (valor == null) {
                break;
            } else if (valor < 0) {
                alertaErro();
                popUpController.setValor(0);
            } else {
                PartyState.dividirXpParty(valor);
                break;
            }
        }
    }

    @FXML
    private void newPlayer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/NewPlayerScreem.fxml"));

        Parent root = loader.load();

        Stage popup = new Stage();

        popup.initModality(Modality.APPLICATION_MODAL);

        popup.setScene(new Scene(root, 400, 600));

        popup.setMinWidth(480);
        popup.setMinHeight(600);
        popup.setResizable(false);
        popup.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/img/easy-guild.png")));

        popup.showAndWait();
    }

    // Métodos públicos
    public void adicionarJogador(Jogador jogador) {
        VBox novoCard = criarCard(jogador);
        galeriaAventureiros.getChildren().add(novoCard);
    }
}
