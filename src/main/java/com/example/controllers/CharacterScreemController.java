package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @FXML
    public void initialize() {
        instance = this;

        for (Jogador jogador : PartyState.getParty()) {
            adicionarJogador(jogador);
        }
    }

    public static CharacterScreemController getInstance() {
        return instance;
    }

    public void adicionarJogador(Jogador jogador) {
        VBox novoCard = criarCard(jogador);
        galeriaAventureiros.getChildren().add(novoCard);
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
    private void newPlayer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/NewPlayerScreem.fxml"));
        
        Parent root = loader.load();

        Stage popup = new Stage();

        popup.initModality(Modality.APPLICATION_MODAL);

        popup.setScene(new Scene(root));

        popup.showAndWait();
    }

}
